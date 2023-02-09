package com.example.githubrepository.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubrepository.databinding.RecyclerviewListRowBinding
import com.example.githubrepository.model.TrendingItem

class GithubTrendingAdapter : RecyclerView.Adapter<GithubTrendingAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: RecyclerviewListRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TrendingItem>() {
        override fun areItemsTheSame(oldItem: TrendingItem, newItem: TrendingItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TrendingItem, newItem: TrendingItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var trending : List<TrendingItem>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerviewListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTrending = trending[position]

        holder.binding.tvRepoTitle.text = currentTrending.full_name
        holder.binding.tvAuthor.text = currentTrending.name
//        holder.binding.tvStarCount.text = currentTrending.owner.starred_url  /*star and forks count didn't work*/
        holder.binding.tvDescription.text = currentTrending.description

        holder.binding.ivUser.load(currentTrending.owner.avatar_url)
    }

    override fun getItemCount() = trending.size
}