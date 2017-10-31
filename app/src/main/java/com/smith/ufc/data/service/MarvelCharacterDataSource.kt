package com.smith.ufc.data.service

import com.smith.ufc.data.models.MarvelData
import com.smith.ufc.data.models.MarvelResponse
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Charlton on 10/31/17.
 */

interface MarvelCharacterDataSource {


    @get:GET("v1/public/characters?orderBy=-modified&limit=42")
    val characters: Observable<MarvelResponse<MarvelData<MarvelCharacterList>>>


    @GET("v1/public/characters?orderBy=-modified&limit=42")
    fun getCharacters(@Query("nameStartsWith") name: String): Observable<MarvelResponse<MarvelData<MarvelCharacterList>>>


    @GET("v1/public/characters/{id}")
    fun getCharacter(@Path("id") id: Int): Observable<MarvelResponse<MarvelData<MarvelCharacterList>>>


    @GET("v1/public/comics/{id}/characters")
    fun getComicCharacters(@Path("id") id: Int): Observable<MarvelResponse<MarvelData<MarvelCharacterList>>>


}
