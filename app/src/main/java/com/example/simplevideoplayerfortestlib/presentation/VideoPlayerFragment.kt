package com.example.simplevideoplayerfortestlib.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.example.simplevideoplayerfortestlib.databinding.FragmentVideoPlayerBinding


class VideoPlayerFragment : Fragment() {

    private val dataModel: DataModel by activityViewModels()
    private lateinit var binding: FragmentVideoPlayerBinding
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoPlayerBinding.inflate(inflater)
        return binding.root
    }

    @OptIn(UnstableApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("FragmentAAA","FragmentCreated ${dataModel.messageToFragmentTitle.value}")

        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.VideoPlayer.player = exoPlayer

        dataModel.messageToFragmentMP4.observe(viewLifecycleOwner) {
            val mediaItem = MediaItem.fromUri(it)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
        }

        dataModel.messageToFragmentTitle.observe(viewLifecycleOwner) {
            binding.Title.text = it
        }

        binding.backButton.setOnClickListener {
            dataModel.videoPlayerisActive.value = false
            exoPlayer.stop()
            parentFragmentManager.commit {
                remove(this@VideoPlayerFragment)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        dataModel.videoPlayerisActive.value = false
        Log.d("FragmentAAA", "FragmentDestroy ${dataModel.messageToFragmentTitle.value}")
        exoPlayer.release()
    }
    companion object {
        @JvmStatic
        fun newInstance() = VideoPlayerFragment() }
}

