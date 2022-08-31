package com.splyzateams.app.di

import com.splyzateams.app.common.Constrants
import com.splyzateams.app.data.remote.SplyzaTeamsApi
import com.splyzateams.app.data.repository.TeamsRepositoryImpl
import com.splyzateams.app.domain.repository.TeamsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSplyzaTeamsApi():SplyzaTeamsApi{
        return Retrofit.Builder()
            .baseUrl(Constrants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SplyzaTeamsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideTeamsRepository(api: SplyzaTeamsApi):TeamsRepository{
        return TeamsRepositoryImpl(api)
    }

}