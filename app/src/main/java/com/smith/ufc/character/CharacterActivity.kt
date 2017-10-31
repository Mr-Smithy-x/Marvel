package com.smith.ufc.character

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.smith.ufc.R
import com.smith.ufc.comic.ComicContract
import com.smith.ufc.comic.presenters.ComicRecyclerPresenter
import com.smith.ufc.comic.presenters.MarvelComicPresenter
import com.smith.ufc.data.base.BaseActivity
import com.smith.ufc.data.models.MarvelCharacter
import com.smith.ufc.data.models.verbose.MarvelComicList
import com.smith.ufc.data.repositories.character.CharacterSpecification
import com.smith.ufc.ui.adapters.ComicRecyclerAdapter
import com.smith.ufc.ui.views.GridRecyclerView
import com.willowtreeapps.spruce.Spruce
import com.willowtreeapps.spruce.animation.DefaultAnimations
import com.willowtreeapps.spruce.sort.DefaultSort
import timber.log.Timber

/**
 * @see com.smith.ufc.comic.presenters.MarvelComicPresenter
 * @see com.smith.ufc.ui.adapters.ComicRecyclerAdapter
 * @see com.smith.ufc.comic.presenters.ComicRecyclerPresenter
 * @see com.smith.ufc.comic.ComicContract
 */
class CharacterActivity : BaseActivity(), ComicContract.View {


    private val mMarvelComicPresenter: ComicContract.Presenter? = MarvelComicPresenter(this)
    private var mComicRecyclerAdapter: ComicRecyclerAdapter? = ComicRecyclerAdapter(ComicRecyclerPresenter())
    private var spruceAnimator: Animator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        var character = getCharacter()
        Glide.with(this).load(character.thumbnail.toString()).into(findViewById(R.id.character_avatar))
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.title = character.name
        supportActionBar!!.subtitle = "Character Details"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<GridRecyclerView>(R.id.recycler_view).adapter = mComicRecyclerAdapter
        findViewById<GridRecyclerView>(R.id.recycler_view).isNestedScrollingEnabled = false
        mMarvelComicPresenter?.getCharacterComics(character.id)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            this.onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun getCharacter(): MarvelCharacter = characterDb.query(CharacterSpecification(intent.extras.getInt(CHARACTER_ID))).first()

    //region Fetching Callbacks

    override fun onFetchDataStarted() {
        Toast.makeText(this, "Task started", Toast.LENGTH_LONG).show()
    }

    override fun onFetchDataCompleted() {
        Toast.makeText(this, "Task completed", Toast.LENGTH_LONG).show()
    }

    override fun onFetchDataSuccess(model: MarvelComicList) {
        mComicRecyclerAdapter!!.setData(model)
    }

    override fun onFetchDataError(e: Throwable) {
        Timber.e(e)
        Toast.makeText(this, "Task failed", Toast.LENGTH_LONG).show()
    }

    //endregion

    private fun initSpruce() {
        try {
            this.spruceAnimator = Spruce.SpruceBuilder(findViewById(R.id.recycler_view))
                    .sortWith(DefaultSort(/*interObjectDelay=*/100L))
                    .animateWith(DefaultAnimations.growAnimator(findViewById(R.id.recycler_view), /*duration=*/400))
                    .start()
        } catch (e: Exception) {

        }
    }
    companion object {
        const val CHARACTER_ID = "CHAR_ID"

        fun newInstance(context: Context, id: Int): Intent {
            var intent = Intent(context, CharacterActivity::class.java)
            intent.putExtra(CHARACTER_ID, id)
            return intent;
        }
    }
}
