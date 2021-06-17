package com.danielefavaro.movies.main.di.module

import com.danielefavaro.movies.base.util.UtilsTest
import com.danielefavaro.movies.data.service.MoviesService
import dagger.Module
import dagger.Provides
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private val REQUEST_MOVIES = Regex("movie-app/movies$")
private val REQUEST_MOVIES_AGGREGATE = Regex("movie-app/movies/limit/\\d+/offset/\\d+$")
private val REQUEST_MOVIE = Regex("movie-app/movies/\\d+$")

private val RESPONSE_MOVIES = "getMovies"
private val RESPONSE_MOVIES_AGGREGATE = "getMoviesAggregate"
private val RESPONSE_MOVIE = "getMovie"

@Module
class NetworkModuleTest {

    @Singleton
    @Provides
    fun providesMoviesRetrofit(retrofit: Retrofit) = retrofit.create(MoviesService::class.java)

    @Singleton
    @Provides
    fun providesMockWebServer() = MockWebServer()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, mockWebServer: MockWebServer) =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url(("/")))
            .build()

    @Singleton
    @Provides
    fun providesHttpClient() = OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                // custom logic
                uri.contains(REQUEST_MOVIES) -> UtilsTest.getJsonFromFile("$RESPONSE_MOVIES.json")
                uri.contains(REQUEST_MOVIES_AGGREGATE) -> UtilsTest.getJsonFromFile("$RESPONSE_MOVIES_AGGREGATE.json")
                uri.contains(REQUEST_MOVIE) -> UtilsTest.getJsonFromFile("$RESPONSE_MOVIE.json")
                else -> ""
            }

            Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message(responseString)
                .addHeader("content-type", "application/json")
                .body(responseString.toResponseBody("application/json; charset=utf-8".toMediaType()))
                .build()
        }
        .build()

}