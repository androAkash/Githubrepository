package com.example.githubrepository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepository.adapter.GithubTrendingAdapter
import com.example.githubrepository.databinding.ActivityMainBinding
import com.example.githubrepository.viewModel.GithubTrendingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mviewModel : GithubTrendingViewModel by viewModels()
    private lateinit var githubTrendingAdapter : GithubTrendingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etSearchView.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode== KeyEvent.KEYCODE_ENTER){
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        setUpRv()
    }
// Setup RecyclerView
    private fun setUpRv() {
        githubTrendingAdapter = GithubTrendingAdapter()

        binding.rvRecyclerView.apply {
            adapter = githubTrendingAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
        }

        mviewModel.responseGithubTrending.observe(this) { trendingItem ->
            githubTrendingAdapter.trending = trendingItem
        }
    }
}