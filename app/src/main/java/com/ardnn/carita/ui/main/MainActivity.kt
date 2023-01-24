package com.ardnn.carita.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.R
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.databinding.ActivityMainBinding
import com.ardnn.carita.ui.addstory.AddStoryFragment
import com.ardnn.carita.ui.detail.DetailActivity
import com.ardnn.carita.ui.login.LoginActivity
import com.ardnn.carita.ui.maps.MapsActivity
import com.ardnn.carita.ui.onboarding.OnBoardingActivity
import com.ardnn.carita.ui.util.ViewModelFactory
import com.ardnn.carita.ui.util.showToast
import com.ardnn.carita.vo.Status
import kotlinx.coroutines.launch
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

        executeInitialUseCases()
        initLifecycleActivity()
        setupToolbar()
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
//                logout()
                viewModel.logoutFlow()
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

    private fun setupAction() {
        binding.fabAddStory.setOnClickListener {
            AddStoryFragment().run {
                arguments = Bundle().apply {
                    putString(AddStoryFragment.EXTRA_TOKEN, user.token)
                }
                show(supportFragmentManager, "")
            }
        }
        binding.fabMap.setOnClickListener {
            startActivity(
                Intent(this, MapsActivity::class.java)
                    .putExtra(MapsActivity.EXTRA_USER, user)
            )
        }
    }

    private fun executeInitialUseCases() {
        viewModel.getHasBeenLaunched()
    }

    private fun initLifecycleActivity() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is MainUiState.Loading -> {
                            if (state.isLoading) showLoading()
                            else hideLoading()
                        }

                        is MainUiState.Error -> {
                            showError()
                        }

                        is MainUiState.ErrorToast -> {
                            showToast(this@MainActivity, getString(state.stringId))
                        }

                        is MainUiState.OnSuccessGetHasBeenLaunched -> {
                            handleOnSuccessGetHasBeenLaunched(state.hasBeenLaunched)
                        }

                        is MainUiState.OnUserIsLogin -> {
                            handleOnUserIsLogin(state.user)
                        }

                        is MainUiState.OnUserNotLogin -> {
                            handleUserNotLogin()
                        }

                        is MainUiState.OnSuccessGetStories -> {
                            handleOnSuccessGetStories(state.stories)
                        }

                        is MainUiState.OnSuccessLogout -> {
                            handleOnSuccessLogout()
                        }

                        else -> {
                            // no op
                        }
                    }
                }
            }
        }
    }

    private fun handleOnSuccessGetHasBeenLaunched(hasBeenLaunched: Boolean) {
        if (!hasBeenLaunched) {
            finish()
            startActivity(Intent(this@MainActivity, OnBoardingActivity::class.java))
        } else {
            viewModel.getUser()
        }
    }

    private fun handleOnUserIsLogin(user: User) {
        this.user = user
        getStories()
    }

    private fun handleUserNotLogin() {
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun handleOnSuccessGetStories(data: PagingData<StoryResponse>) {
        val adapter = StoryAdapter { story ->
            startActivity(Intent(this, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_STORY, story)
            )
        }.apply {
            addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.Loading -> showLoading()
                    is LoadState.NotLoading -> hideLoading()
                    is LoadState.Error -> showError()
                }
            }
            submitData(lifecycle, data)
            enablingMapsButton()
        }

        binding.rvStory.apply {
            this.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun handleOnSuccessLogout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun processStatus(
        statusStories: Status<*>,
        onSuccess: () -> Unit,
        onError: () -> Unit = { }
    ) {
        when (statusStories) {
            is Status.Success -> {
                hideLoading()
                onSuccess()
            }

            is Status.Error -> {
                hideLoading()
                onError()
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
        viewModel.getStories(user.token)
    }

    private fun showLoading() {
        binding.loading.root.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.root.visibility = View.INVISIBLE
    }

    private fun showError() {
        binding.tvNoConnection.visibility = View.VISIBLE
    }

    private fun enablingMapsButton() {
        binding.fabMap.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        getStories()
    }
}