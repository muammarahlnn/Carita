package com.ardnn.carita.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ardnn.carita.R
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.databinding.ActivityDetailBinding
import com.ardnn.carita.ui.util.loadImage
import com.ardnn.carita.ui.util.withDateFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val story = intent.getParcelableExtra<StoryResponse>(EXTRA_STORY) as StoryResponse

        setupToolbar()
        setupViews(story)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        binding.collapsingToolbar.title = ""
    }

    private fun setupViews(story: StoryResponse) {
        with(binding) {
            ivPhoto.loadImage(story.photoUrl)
            tvName.text = story.name ?: "-"
            tvDesc.text = story.description ?: "-"
            tvDate.text = story.createdAt.withDateFormat()
        }
    }

    companion object {

        const val EXTRA_STORY = "extra_story"
    }
}