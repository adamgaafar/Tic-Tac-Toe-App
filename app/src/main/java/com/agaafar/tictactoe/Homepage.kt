package com.agaafar.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agaafar.tictactoe.R
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.agaafar.tictactoe.databinding.FragmentHomepageBinding

import android.os.Build

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import java.util.Collections.rotate
import android.view.animation.LinearInterpolator
import android.widget.ImageView

import java.util.Collections.rotate





class Homepage : Fragment() {

    private lateinit var binding:FragmentHomepageBinding
    var animationStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomepageBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rotation = AnimationUtils.loadAnimation(this.requireContext(),R.anim.rotateclockwise)
        rotation.setInterpolator(LinearInterpolator())
       // binding.mainStarOne.startAnimation(rotation)
        binding.mainStarSecond.startAnimation(rotation)
      //  binding.mainStarThird.startAnimation(rotation)
        animationStarted = true

        binding.mainStarOne.setOnClickListener {
            //stop and start the animation by click
            if (animationStarted){
            binding.mainStarOne.clearAnimation()
                animationStarted = false
            }else{
                binding.mainStarOne.startAnimation(rotation)
                animationStarted = true
            }
            /////////////////////////////////////

        }
        binding.mainStarSecond.setOnClickListener {
            //stop and start the animation by click
            if (animationStarted){
                binding.mainStarSecond.clearAnimation()
                animationStarted = false
            }else{
                binding.mainStarSecond.startAnimation(rotation)
                animationStarted = true
            }
            /////////////////////////////////////

        }

        binding.mainStarThird.setOnClickListener {
            //stop and start the animation by click
            if (animationStarted){
                binding.mainStarThird.clearAnimation()
                animationStarted = false
            }else{
                binding.mainStarThird.startAnimation(rotation)
                animationStarted = true
            }
            /////////////////////////////////////
        }

        binding.letsplaybtn.setOnClickListener {
            animationStarted = false
            val action = HomepageDirections.actionHomepageToSelectGameFragment()
            findNavController().navigate(action)
        }

    }



}