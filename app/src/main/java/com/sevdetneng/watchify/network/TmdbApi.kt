package com.sevdetneng.watchify.network

import com.sevdetneng.watchify.model.ListResponse
import com.sevdetneng.watchify.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface TmdbApi {
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("api_key") apiKey : String) : ListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey : String) : ListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey : String) : ListResponse

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id : Int,@Query("api_key") apiKey : String) : Movie
}
