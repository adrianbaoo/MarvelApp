package com.marvel.app.ui.character.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.app.R
import com.marvel.app.ui.character.detail.CharacterDetailActivity
import com.marvel.app.ui.character.list.adapter.CharacterListAdapter
import com.marvel.app.ui.character.list.viewmodel.CharacterListViewModel

class CharacterListActivity : AppCompatActivity(), CharacterListPresenter.View,
    CharacterListAdapter.CharacterItemClickListener {

    private lateinit var characterListAdapter: CharacterListAdapter
    private val presenter: CharacterListPresenter = CharacterListPresenter(
        this
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        setupCharactersAdapter()
        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun renderCharacters(charactersViewModels: List<CharacterListViewModel>) {
        characterListAdapter.setList(charactersViewModels)
    }

    override fun showProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
    }

    override fun goToCharacterDetail(characterId: Int) {
        startActivity(CharacterDetailActivity.newIntent(this, characterId))
    }

    private fun setupCharactersAdapter() {
        val charactersRecycler = findViewById<RecyclerView>(R.id.character_list_recycler)
        val mRecyclerViewOnScrollListener =
            initRecyclerViewScrollListener(charactersRecycler.layoutManager as LinearLayoutManager)
        charactersRecycler.addOnScrollListener(mRecyclerViewOnScrollListener)

        characterListAdapter = CharacterListAdapter()
        characterListAdapter.setListener(this)
        charactersRecycler.adapter = characterListAdapter
    }

    private fun initRecyclerViewScrollListener(layoutManager: LinearLayoutManager): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {

            override fun onScrolled(
                recyclerView: RecyclerView, dx: Int, dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    presenter.loadMoreCharacters()
                }
            }
        }
    }

    override fun didClickItem(characterId: Int) {
        presenter.didClickCharacter(characterId)
    }
}