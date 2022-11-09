package com.ardnn.carita.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.R
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.databinding.ActivityMainBinding
import com.ardnn.carita.ui.addstory.AddStoryFragment
import com.ardnn.carita.ui.detail.DetailActivity
import com.ardnn.carita.ui.login.LoginActivity
import com.ardnn.carita.ui.onboarding.OnBoardingActivity
import com.ardnn.carita.ui.util.ViewModelFactory
import com.ardnn.carita.vo.Status
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AddStoryFragment.OnSuccessPostStory {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        setupInject()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupViewModel()
        setupAction()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_language -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }

            R.id.nav_logout -> {
                logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupInject() {
        (application as CaritaApplication).applicationComponent.inject(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupViewModel() {
        viewModel.getHasBeenLaunched()
        viewModel.getUser()
        subscribeViewModel()
    }

    private fun setupAction() {
        binding.fabAddStory.setOnClickListener {
            AddStoryFragment().run {
                arguments = Bundle().apply {
                    putString(AddStoryFragment.EXTRA_TOKEN, user.token)
                }
                show(supportFragmentManager, "")
            }
        }
    }

    private fun subscribeViewModel() {
        viewModel.hasBeenLaunched.observe(this) { hasBeenLaunched ->
            if (!hasBeenLaunched) {
                viewModel.saveHasBeenLaunched()
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finishAffinity()
            }
        }
        viewModel.isLogin.observe(this) { isLogin ->
            if (!isLogin) {
                // to login
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }
        }
        viewModel.user.observe(this) { user ->
            Timber.d("USER -> ${user.name}")
            this.user = user
            getStories()
        }
        viewModel.stories.observe(this) { statusStories ->
            processStories(statusStories) {
                val stories = statusStories.data
                if (stories?.isEmpty() as Boolean) {
                    binding.tvNoConnection.visibility = View.VISIBLE
                    return@processStories
                }
                val adapter = StoryAdapter(stories) { story ->
                    startActivity(Intent(this, DetailActivity::class.java)
                        .putExtra(DetailActivity.EXTRA_STORY, story)
                    )
                }
                binding.rvStory.apply {
                    this.adapter = adapter
                    layoutManager = LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                }
            }
        }
        viewModel.logoutStatus.observe(this) { status ->
            processStories(status) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun processStories(statusStories: Status<*>, onSuccess: () -> Unit) {
        when (statusStories) {
            is Status.Success -> {
                hideLoading()
                onSuccess()
            }

            is Status.Error -> {
                hideLoading()
            }

            is Status.Loading -> {
                showLoading()
            }
        }
    }

    private fun logout() {
       viewModel.logout()

    }

    private fun getStories() {
        viewModel.getStories(user.token.toString())
    }

    private fun showLoading() {
        binding.loading.root.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.root.visibility = View.INVISIBLE
    }

    override fun onSuccess() {
        getStories()
    }
}