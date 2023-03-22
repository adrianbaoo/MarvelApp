package com.marvel.app.ui.character.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.marvel.app.R
import com.marvel.app.ui.character.detail.viewmodel.CharacterDetailViewModel
import com.marvel.app.util.Constant.CHARACTER_ID_INPUT

class CharacterDetailActivity : AppCompatActivity(), CharacterDetailPresenter.View {

    private val presenter: CharacterDetailPresenter = CharacterDetailPresenter(
        this
    )

    companion object {
        fun newIntent(context: Context, characterId: Int): Intent {
            val intent = Intent(
                context, CharacterDetailActivity::class.java
            )
            intent.putExtra(CHARACTER_ID_INPUT, characterId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        val characterId: Int = intent.extras!!.getInt(CHARACTER_ID_INPUT)
        presenter.onCreate(characterId)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun renderCharacter(characterViewModel: CharacterDetailViewModel) {
        renderThumbnail(characterViewModel.thumbnail)
        renderName(characterViewModel.name)
        renderDescription(characterViewModel.description)
    }

    override fun showErrorWhenGettingCharacter() {
        buildErrorAlert(getString(R.string.error_when_getting_character))
    }

    override fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
    }

    private fun renderThumbnail(thumbnail: String) {
        val characterImageIV: ImageView = findViewById(R.id.character_detail_image_iv)
        Glide.with(this).load(thumbnail).into(characterImageIV)
    }

    private fun renderName(name: String) {
        val characterNameTV: TextView = findViewById(R.id.character_detail_name_tv)
        characterNameTV.text = name
    }

    private fun renderDescription(description: String) {
        val characterDescriptionTV: TextView = findViewById(R.id.character_detail_description_tv)
        characterDescriptionTV.text = description
    }

    private fun buildErrorAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        builder.show()
    }

}