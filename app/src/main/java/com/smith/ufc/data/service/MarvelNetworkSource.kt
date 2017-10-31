package com.smith.ufc.data.service

import android.os.Environment
import android.util.Log
import com.smith.ufc.BuildConfig
import com.smith.ufc.data.models.MarvelData
import com.smith.ufc.data.models.MarvelResponse
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import com.smith.ufc.data.models.verbose.MarvelComicList
import com.smith.ufc.helpers.Util
import io.reactivex.Observable
import io.reactivex.Scheduler
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by Charlton on 10/25/17.
 */

class MarvelNetworkSource(val background: Scheduler, val main: Scheduler) : MarvelDataSource {

    private val comicAPI: MarvelComicDataSource

    private val characterAPI: MarvelCharacterDataSource

    private val URL = "https://gateway.marvel.com/"

    init {
        val cacheSize = 20 * 1024 * 1024 // 20 MB
        val cache = Cache(Environment.getDownloadCacheDirectory(), cacheSize.toLong())
        val interceptor = HttpLoggingInterceptor { message -> Log.e(HttpLoggingInterceptor::class.java.simpleName, message) }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                    val ts = Calendar.getInstance().timeInMillis
                    val url = original.url().newBuilder()
                            .addQueryParameter("ts", ts.toString())
                            .addQueryParameter("apikey", BuildConfig.PUB_KEY)
                            .addQueryParameter("hash", Util.MD5(ts.toString() + BuildConfig.PRIV_KEY + BuildConfig.PUB_KEY))
                            .build()
                    request.method(original.method(), original.body())
                    request.url(url)
                    return@addInterceptor chain.proceed(request.build())
                }
                .addInterceptor(interceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        this.comicAPI = retrofit.create(MarvelComicDataSource::class.java)
        this.characterAPI = retrofit.create(MarvelCharacterDataSource::class.java)

    }

    override fun getCharacterComics(id: Int): Observable<MarvelResponse<MarvelData<MarvelComicList>>> = this.comicAPI.getCharacterComics(id).subscribeOn(background).observeOn(main)

    override fun getComicCharacters(id: Int): Observable<MarvelResponse<MarvelData<MarvelCharacterList>>> = this.characterAPI.getComicCharacters(id).subscribeOn(background).observeOn(main)

    override val characters: Observable<MarvelResponse<MarvelData<MarvelCharacterList>>> get() = this.characterAPI.characters.subscribeOn(background).observeOn(main)

    override val comics: Observable<MarvelResponse<MarvelData<MarvelComicList>>> get() = this.comicAPI.comics.subscribeOn(background).observeOn(main)

    override fun getCharacters(name: String): Observable<MarvelResponse<MarvelData<MarvelCharacterList>>> =
            this.characterAPI.getCharacters(name).subscribeOn(background).observeOn(main)

    override fun getComics(name: String): Observable<MarvelResponse<MarvelData<MarvelComicList>>> =
            this.comicAPI.getComics(name).subscribeOn(background).observeOn(main)

    override fun getCharacter(id: Int): Observable<MarvelResponse<MarvelData<MarvelCharacterList>>> =
            this.characterAPI.getCharacter(id).subscribeOn(background).observeOn(main)

    override fun getComic(id: Int): Observable<MarvelResponse<MarvelData<MarvelComicList>>> = this.comicAPI.getComic(id).subscribeOn(background).observeOn(main)

}


