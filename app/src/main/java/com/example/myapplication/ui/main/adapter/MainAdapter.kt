package com.example.myapplication.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.FriendDetails
import com.example.myapplication.databinding.ItemLayoutBinding
import com.example.myapplication.ui.main.view.DetailsActivity


class MainAdapter(
    private val friends: ArrayList<FriendDetails>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(friend: FriendDetails) {
            val binding = ItemLayoutBinding.bind(itemView)
            binding.textViewName.text = friend.name.title + " " + friend.name.first + " " + friend.name.last
            binding.textViewCountry.text = friend.location.country
            Glide.with(binding.imageViewAvatar.context)
                .load(friend.picture.large)
                .into(binding.imageViewAvatar)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                intent.putExtra("friendPortrait", friend.picture.large)
                intent.putExtra("friendFullName", friend.name.title + " " + friend.name.first + " " + friend.name.last)
                intent.putExtra("friendAddress", friend.location.street.number.toString() + " " + friend.location.street.name)
                intent.putExtra("friendCity", friend.location.city)
                intent.putExtra("friendState", friend.location.state)
                intent.putExtra("friendCountry", friend.location.country)
                intent.putExtra("friendEmail", friend.email)
                intent.putExtra("friendCell", friend.cell)
                startActivity(itemView.context,intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(friends[position])

    fun addData(list: List<FriendDetails>) {
        friends.addAll(list)
    }
}