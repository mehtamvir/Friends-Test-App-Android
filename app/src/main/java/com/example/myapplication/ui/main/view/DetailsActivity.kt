package com.example.myapplication.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var bundle :Bundle ?=intent.extras
        setupUI(bundle!!.getString("friendPortrait"),
            bundle!!.getString("friendFullName"),
            bundle!!.getString("friendAddress"),
            bundle!!.getString("friendCity"),
            bundle!!.getString("friendState"),
            bundle!!.getString("friendCountry"),
            bundle!!.getString("friendEmail"),
            bundle!!.getString("friendCell"))
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI(portrit: String?, fullName: String?, address: String?, city: String?, state: String?, country: String?, email: String?, cell: String?) {
        Glide.with(binding.imageViewAvatar.context)
            .load(portrit)
            .into(binding.imageViewAvatar)

        binding.textViewName.text = fullName
        binding.textViewAddress.text = address
        binding.textViewCityState.text = "$city, $state"
        binding.textViewCountry.text = country
        binding.textViewEmail.text = email
        binding.textViewCell.text = cell
    }
}