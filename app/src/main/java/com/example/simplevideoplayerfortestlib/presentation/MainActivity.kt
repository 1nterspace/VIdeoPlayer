package com.example.simplevideoplayerfortestlib.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.simplevideoplayerfortestlib.R
import com.example.simplevideoplayerfortestlib.databinding.ActivityMainBinding
import com.example.simplevideoplayerfortestlib.domain.models.VideoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), RvVideoAdapter.Listener {

    private val dataModel: DataModel by viewModel<DataModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RvVideoAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        //supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, VideoPlayerFragment.newInstance()).commit()

        // Обновление макета в зависимости от ориентации
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setContentView(R.layout.activity_main_l)
                    Log.d("ConfigAAAA", Constants.ORIENTATION_LANDSCAPE)
                    initViews(Constants.SPAN_COUNT_LANDSCAPE)
        } else {
                setContentView(R.layout.activity_main)
                Log.d("ConfigAAAA", Constants.ORIENTATION_PORTRAIT)
                initViews(Constants.SPAN_COUNT_PORTRAIT)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("AAA", "Activity created")
        super.onCreate(savedInstanceState)
        initViews(Constants.SPAN_COUNT_PORTRAIT)
        CoroutineScope(Dispatchers.Main).launch {
            dataModel.getList()
        }
    }
    // Реализация интерфейса для обработки нажатия на элемент ресайкл вью
    override fun obClick(videoItem: VideoItem) {
        //dataModel.messageToActivity.value = Constants.MESSAGE_TO_ACTIVITY_SHOW_FRAGMENT
        dataModel.messageToFragmentMP4.value = videoItem.assets.mp4
        dataModel.messageToFragmentTitle.value = videoItem.title
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, VideoPlayerFragment.newInstance()).commit()
        binding.fragmentContainerView.visibility = View.VISIBLE
    }

    @SuppressLint("CommitTransaction")
    private fun initViews(spanCount: Int) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RvVideoAdapter(this)
        binding.placeHolder.adapter = adapter

        binding.placeHolder.layoutManager = GridLayoutManager(this, spanCount)

        binding.placeHolder.addItemDecoration(
            GridSpacingItemDecoration(spanCount, Constants.GRID_SPACING, true)
        )

        dataModel.resultList.observe(this, Observer {
            if (it.isEmpty()) {
                val toast = Toast.makeText(this, "Ошибка запроса, проверте ваше интернет соединение.", Constants.TOAST_DURATION)
                toast.setGravity(Constants.TOAST_GRAVITY, Constants.TOAST_X_OFFSET, Constants.TOAST_Y_OFFSET)
                toast.show()
            } else {
                adapter.submitList(it)
            }
        })

        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            CoroutineScope(Dispatchers.Main).launch {
                dataModel.getList()
                delay(Constants.SWIPE_REFRESH_DELAY)
                binding.placeHolder.scrollToPosition(0)
                swipeRefreshLayout.isRefreshing = false
            }
        }

        dataModel.messageToActivity.observe(this) {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, VideoPlayerFragment.newInstance()).commit()
            binding.fragmentContainerView.visibility = View.VISIBLE
            dataModel.messageToActivity.value = null
        }

    }


}

