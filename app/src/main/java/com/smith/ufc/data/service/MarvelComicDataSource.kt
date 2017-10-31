package com.smith.ufc.data.service

import com.smith.ufc.data.models.MarvelData
import com.smith.ufc.data.models.MarvelResponse
import com.smith.ufc.data.models.verbose.MarvelComicList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Charlton on 10/31/17.
 */

interface MarvelComicDataSource {


    @get:GET("v1/public/comics?orderBy=-modified&limit=42")
    val comics: Observable<MarvelResponse<MarvelData<MarvelComicList>>>

    @GET("v1/public/comics?orderBy=-modified&limit=42")
    fun getComics(@Query("titleStartsWith") name: String): Observable<MarvelResponse<MarvelData<MarvelComicList>>>


    @GET("v1/public/comics/{id}")
    fun getComic(@Path("id") id: Int): Observable<MarvelResponse<MarvelData<MarvelComicList>>>


    @GET("v1/public/characters/{id}/comics")
    fun getCharacterComics(@Path("id") id: Int): Observable<MarvelResponse<MarvelData<MarvelComicList>>>


}
