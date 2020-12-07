package com.example.countryexplorer

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DaggerModule {

    @Provides
    @Singleton
    fun providesDao(@ApplicationContext context: Context): CountryDao {
        val database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "country_database"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        return database.countryDao()
    }

    @Singleton
    @Provides
    fun providesAPI() : ApiService {
        return ApiProvider.createService(ApiService::class.java)
    }

    @Provides
    fun providesRepository(apiService:ApiService, dao: CountryDao) : Repository {
        return RepositoryImp(apiService,dao)
    }
}