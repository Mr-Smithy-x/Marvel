package com.smith.ufc

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.AppBarLayout
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.smith.ufc.character.CharacterContract
import com.smith.ufc.character.CharacterGenericAdapter
import com.smith.ufc.character.MarvelCharacterPresenter
import com.smith.ufc.comic.ComicContract
import com.smith.ufc.comic.ComicViewHolderAdapter
import com.smith.ufc.comic.MarvelComicPresenter
import com.smith.ufc.data.base.BaseActivity
import com.smith.ufc.data.models.MarvelCharacter
import com.smith.ufc.data.models.MarvelComic
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import com.smith.ufc.data.models.verbose.MarvelComicList
import com.smith.ufc.helpers.DrawableTextWatcher
import com.smith.ufc.helpers.RightDrawableClickListener
import com.smith.ufc.ui.adapters.CharacterAdapter
import com.smith.ufc.ui.adapters.ComicRecyclerAdapter
import com.smith.ufc.ui.manager.AnimatedGridLayoutManager
import com.smith.ufc.ui.views.ArrowViewPager
import com.smith.ufc.ui.views.GridRecyclerView
import io.realm.Realm
import java8.util.Objects
import java8.util.stream.Collectors
import java8.util.stream.StreamSupport
import timber.log.Timber
import java.util.*


class MainActivity : BaseActivity(), ComicContract.View {

    //region Presenters
    private val mMarvelComicPresenter: ComicContract.Presenter = MarvelComicPresenter(this)
    private val mMarvelCharacterPresenter: CharacterContract.Presenter = MarvelCharacterPresenter(object : CharacterContract.View {
        override fun onFetchDataSuccess(model: MarvelCharacterList) {
            mCharacterAdapter?.setData(model)
        }

        override fun onFetchDataStarted() {

        }

        override fun onFetchDataCompleted() {

        }


        override fun onFetchDataError(e: Throwable) {
            Timber.e(e)
        }

    })
    //endregion

    private var mComicAdapter: ComicRecyclerAdapter? = ComicRecyclerAdapter(ComicViewHolderAdapter())
    private var mCharacterAdapter: CharacterAdapter? = CharacterAdapter(CharacterGenericAdapter())
    private var animatedGridLayoutManager: AnimatedGridLayoutManager? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<DrawerLayout>(R.id.drawer_layout).addDrawerListener(ActionBarDrawerToggle(this, findViewById(R.id.drawer_layout), findViewById(R.id.toolbar), R.string.open_drawer, R.string.close_drawer))
        findViewById<AppCompatEditText>(R.id.search_bar).addTextChangedListener(DrawableTextWatcher(findViewById(R.id.search_bar)))
        findViewById<AppCompatEditText>(R.id.search_bar).setOnTouchListener(object : RightDrawableClickListener(findViewById<AppCompatEditText>(R.id.search_bar)) {
            override fun onClick(editText: EditText) {
                editText.setText("")
                mMarvelCharacterPresenter.getLatest()
                mMarvelComicPresenter.getLatest()
            }
        })
        findViewById<AppBarLayout>(R.id.app_bar).addOnOffsetChangedListener { appBarLayout, verticalOffset -> findViewById<ArrowViewPager>(R.id.arrow_view_pager).alpha = 1.0f - Math.abs(verticalOffset / appBarLayout.totalScrollRange.toFloat()) }
        findViewById<AppCompatEditText>(R.id.search_bar).setOnEditorActionListener { view, actionId, event ->
            Timber.d("onEditorAction")
            if (event != null && event.action != KeyEvent.ACTION_DOWN) {
                return@setOnEditorActionListener false
            } else if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                if (view.text.isNotEmpty()) {
                    mMarvelComicPresenter.searchComicByTitle(view.text.toString())
                    mMarvelCharacterPresenter.searchCharacterName(view.text.toString())
                }
                return@setOnEditorActionListener false
            }
            return@setOnEditorActionListener false
        }
        animatedGridLayoutManager = findViewById<GridRecyclerView>(R.id.recycler_view).layoutManager as AnimatedGridLayoutManager
        animatedGridLayoutManager!!.setRecycler(findViewById<GridRecyclerView>(R.id.recycler_view))
        findViewById<ArrowViewPager>(R.id.arrow_view_pager).getViewPager().adapter = mCharacterAdapter
        findViewById<RecyclerView>(R.id.recycler_view).adapter = mComicAdapter
        findViewById<NavigationView>(R.id.navigation_view).setNavigationItemSelectedListener {
            findViewById<DrawerLayout>(R.id.drawer_layout).closeDrawer(GravityCompat.START)
            true
        }
        setMenuCounter(R.id.action_char, 9)
        if (savedInstanceState == null) {
            mMarvelComicPresenter.getLatest()
            mMarvelCharacterPresenter.getLatest()
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putIntArray(KEY_CHARACTERS, mCharacterAdapter?.repository?.getItemId()?.toIntArray())
        outState?.putIntArray(KEY_COMICS, mComicAdapter?.repository?.getItemId()?.toIntArray())
        outState?.putInt(KEY_COMIC_POSITION, animatedGridLayoutManager?.findFirstVisibleItemPosition()!!)
        outState?.putInt(KEY_CHARACTER_POSITION, findViewById<ArrowViewPager>(R.id.arrow_view_pager)?.getViewPager()?.currentItem!!)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        StreamSupport.stream(savedInstanceState?.keySet()).forEach { r -> Log.e("KEY", r.toString()) }
        mCharacterAdapter?.setData(StreamSupport.stream(savedInstanceState?.getIntArray(KEY_CHARACTERS)?.asList()).map { r -> Realm.getDefaultInstance().where(MarvelCharacter::class.java).equalTo("id", r).findFirst() }.filter(Objects::nonNull).collect(Collectors.toList()) as ArrayList<MarvelCharacter?>)
        mComicAdapter?.setData(StreamSupport.stream(savedInstanceState?.getIntArray(KEY_COMICS)?.asList()).map { r -> Realm.getDefaultInstance().where(MarvelComic::class.java).equalTo("id", r).findFirst() }.filter(Objects::nonNull).collect(Collectors.toList()))
        try {
            findViewById<GridRecyclerView>(R.id.recycler_view).smoothScrollToPosition(savedInstanceState?.getInt(KEY_COMIC_POSITION, 0)!!)
            findViewById<ArrowViewPager>(R.id.arrow_view_pager).getViewPager().setCurrentItem(savedInstanceState.getInt(KEY_CHARACTER_POSITION, 0), true)
        } catch (e: Exception) {
            Timber.e(e, "Unable to scroll to position")
        }

    }

    private fun setMenuCounter(@IdRes itemId: Int, count: Int) {
        val item = findViewById<NavigationView>(R.id.navigation_view).menu.findItem(itemId)
        val view = item.actionView as TextView
        view.text = if (count > 0) count.toString() else null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_question -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        animatedGridLayoutManager?.animateSpruce()
    }

    //region Fetching Callbacks

    override fun onFetchDataStarted() {
        Toast.makeText(this, "Task started", Toast.LENGTH_LONG).show()
    }

    override fun onFetchDataCompleted() {
        Toast.makeText(this, "Task completed", Toast.LENGTH_LONG).show()
    }

    override fun onFetchDataSuccess(model: MarvelComicList) {
        mComicAdapter!!.setData(model)
    }

    override fun onFetchDataError(e: Throwable) {
        Timber.e(e)
        Toast.makeText(this, "Task failed", Toast.LENGTH_LONG).show()
    }

    //endregion

    companion object {
        const val KEY_CHARACTERS: String = "CHARACTERS"
        const val KEY_COMICS: String = "COMICS"
        const val KEY_CHARACTER_POSITION: String = "CHARACTER_POSITION"
        const val KEY_COMIC_POSITION: String = "COMIC_POSITION"
    }

}

