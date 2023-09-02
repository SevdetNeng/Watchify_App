package com.sevdetneng.watchify.repository

import com.sevdetneng.watchify.data.ApiResponse
import com.sevdetneng.watchify.model.ListResponse
import com.sevdetneng.watchify.model.Movie
import com.sevdetneng.watchify.model.MovieImages
import com.sevdetneng.watchify.network.TmdbApi
import com.sevdetneng.watchify.utils.Constants.API_KEY
import javax.inject.Inject

class ApiRepository @Inject constructor(val api : TmdbApi) {
    suspend fun getTrendingMovies(page : Int) : ApiResponse<ListResponse>{
        return try{
            ApiResponse.Loading(true)
            val movieList = api.getTrendingMovies(API_KEY,page)
            if(!movieList.results.isNullOrEmpty()){
                ApiResponse.Loading(false)
            }
            ApiResponse.Success(movieList)
        }catch (e : Exception){
            ApiResponse.Error(e.message.toString())
        }
    }
    suspend fun getTopRatedMovies(page : Int) : ApiResponse<ListResponse>{
        return try{
            ApiResponse.Loading(true)
            val movieList = api.getTopRatedMovies(API_KEY,page)
            if(!movieList.results.isNullOrEmpty()){
                ApiResponse.Loading(false)
            }
            ApiResponse.Success(movieList)
        }catch (e : Exception){
            ApiResponse.Error(e.message.toString())
        }
    }
    suspend fun getNowPlayingMovies(page : Int) : ApiResponse<ListResponse>{
        return try{
            ApiResponse.Loading(true)
            val movieList = api.getNowPlayingMovies(API_KEY,page)
            if(!movieList.results.isNullOrEmpty()){
                ApiResponse.Loading(false)
            }
            ApiResponse.Success(movieList)
        }catch (e : Exception){
            ApiResponse.Error(e.message.toString())
        }
    }

    suspend fun getMovieById(id : Int) : ApiResponse<Movie> {
        return try{
            ApiResponse.Loading(true)
            val movie = api.getMovieById(id,API_KEY)
            if(movie.id!=null){
                ApiResponse.Loading(false)
            }
            ApiResponse.Success(movie)
        }catch (e : Exception){
            ApiResponse.Error(e.message.toString())
        }
    }

    suspend fun searchMovie(query : String) : ApiResponse<ListResponse>{
        return try{
            ApiResponse.Loading(true)
            var movieList = api.searchMovie(API_KEY,query)
            if(movieList.results!=null){
                movieList = movieList.copy(results = movieList.results!!.sortedByDescending {
                    it.popularity
                })
            }
            if(!movieList.results.isNullOrEmpty()){
                ApiResponse.Loading(false)
            }
            ApiResponse.Success(movieList)
        }catch (e : Exception){
            ApiResponse.Error(e.message.toString())
        }
    }

    suspend fun getMovieImages(id : Int) : ApiResponse<MovieImages> {
        return try{
            ApiResponse.Loading(true)
            val images = api.getMovieImages(id, API_KEY)
            if(images.backdrops!=null){
                ApiResponse.Loading(false)
            }
            ApiResponse.Success(images)
        }catch (e : Exception){
            ApiResponse.Error(e.message.toString())
        }
    }

}