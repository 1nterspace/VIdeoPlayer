package com.example.simplevideoplayerfortestlib.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplevideoplayerfortestlib.R
import com.example.simplevideoplayerfortestlib.databinding.VideoCardBinding
import com.example.simplevideoplayerfortestlib.domain.models.VideoItem
import com.squareup.picasso.Picasso


class RvVideoAdapter(private val listener: Listener) : ListAdapter <VideoItem, RvVideoAdapter.ViewHolder>(
    Comparator()
) {

    class Comparator:DiffUtil.ItemCallback<VideoItem>(){
        override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem.videoId == newItem.videoId
        }

        override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = VideoCardBinding.bind(itemView)
         fun bind(videoItem: VideoItem, listener: Listener){
            Picasso.get().load(videoItem.assets.thumbnail).into(binding.videoPreview)
            binding.videoAutor.text = videoItem.description
            binding.videoTitle.text = videoItem.title
             itemView.setOnClickListener {
                 listener.obClick(videoItem)
             }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.video_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val item = getItem(position)
                holder.bind(item,listener)
    }
    //интерфейс для обработки нажатия
    interface Listener{
        fun obClick(videoItem: VideoItem)
    }

}
