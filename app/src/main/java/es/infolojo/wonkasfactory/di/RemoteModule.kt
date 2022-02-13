package es.infolojo.wonkasfactory.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.infolojo.wonkasfactory.data.remote.RemoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    private const val BASE_WONKAS_USERS = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/"

    @Singleton
    @Provides fun provideRemoteService(): RemoteService {
        val retrofit = Retrofit.Builder().baseUrl(BASE_WONKAS_USERS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RemoteService::class.java)
    }
}