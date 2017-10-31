package com.smith.ufc.comic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.smith.ufc.R
import com.smith.ufc.character.CharacterContract
import com.smith.ufc.character.MarvelCharacterPresenter
import com.smith.ufc.data.base.BaseActivity
import com.smith.ufc.data.models.MarvelComic
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import com.smith.ufc.ui.adapters.CharacterRecyclerAdapter
import com.smith.ufc.ui.views.GridRecyclerView
import java.util.*


class ComicActivity : BaseActivity(), CharacterContract.View {


    private val mMarvelCharacterPresenter: CharacterContract.Presenter? = MarvelCharacterPresenter(this)
    private val mCharacterAdapter: CharacterRecyclerAdapter? = CharacterRecyclerAdapter(CharacterViewHolderAdapter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)
        var comic = getComic()
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.title = comic.title
        supportActionBar!!.subtitle = String.format(Locale.US, "Comic Details")

        findViewById<GridRecyclerView>(R.id.recycler_view).adapter = mCharacterAdapter
        findViewById<GridRecyclerView>(R.id.recycler_view).isNestedScrollingEnabled = false
        mMarvelCharacterPresenter?.getComicCharacters(comic.id)
        setComicDetails(comic)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            this.onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setComicDetails(comic: MarvelComic) {
        findViewById<AppCompatTextView>(R.id.comic_card_title).text = String.format(Locale.US, "Title: %s", comic.title)
        findViewById<AppCompatTextView>(R.id.comic_card_desc).text = comic.description
        findViewById<AppCompatTextView>(R.id.comic_card_isbn).text = String.format(Locale.US, "ISBN: %s", comic.isbn)
        findViewById<AppCompatTextView>(R.id.comic_card_price).text = String.format(Locale.US, "Price: $%s", comic.prices.first()?.price.toString())
        Glide.with(this).load(comic.thumbnail.toString()).into(findViewById<AppCompatImageView>(R.id.comic_card_image))
    }


    override fun onFetchDataStarted() {

    }

    override fun onFetchDataCompleted() {

    }

    override fun onFetchDataSuccess(model: MarvelCharacterList) {
        mCharacterAdapter?.setData(model)
    }

    override fun onFetchDataError(e: Throwable) {

    }


    private fun getComic(): MarvelComic = comicDb.query(ComicSpecification(intent.extras.getInt(COMIC_IC))).first()


    companion object {
        const val COMIC_IC = "COMIC_ID"

        fun newInstance(context: Context, id: Int): Intent {
            var intent = Intent(context, ComicActivity::class.java)
            intent.putExtra(COMIC_IC, id)
            return intent;
        }
    }

}
