package com.agaafar.tictactoe

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.agaafar.tictactoe.data.userViewModel
import com.agaafar.tictactoe.data.userViewModelFactory
import com.agaafar.tictactoe.databinding.FragmentSelectGameBinding

class SelectGameFragment : Fragment() {

    private val userViewModel: userViewModel by viewModels {
        userViewModelFactory((activity?.application as TicTacToeApplication).repository)
    }
    private lateinit var dialog: Dialog
    private lateinit var binding: FragmentSelectGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog = Dialog(requireContext())



        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            binding.score.text = user[0].score.toString()

        })
        var clicked = false

        binding.robotimg.setOnClickListener {
            if (clicked == false) {
                binding.robotimg.setImageResource(R.drawable.robotwithoutback)
                clicked = true
            }else{
                binding.robotimg.setImageResource(R.drawable.robot_02_icon)
                clicked = false
            }

        }
        binding.twoplayerimg.setOnClickListener {
            if (clicked == false) {
                binding.twoplayerimg.setImageResource(R.drawable.play_2)
                clicked = true
            }else{
                binding.twoplayerimg.setImageResource(R.drawable.play__1_)
                clicked = false
            }
        }

        binding.store.setOnClickListener {
            if (clicked == false) {
                binding.store.setImageResource(R.drawable.storeopened3)
                clicked = true
            }else{
                binding.store.setImageResource(R.drawable.store)
                clicked = false
            }

        }

        val animations = arrayOf(30f,-30f).map { translation ->
            ObjectAnimator.ofFloat(binding.sutarnselimg, "rotationY", translation).apply {
                duration = 500
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
            }
        }

        val set = AnimatorSet()
        set.playTogether(animations)
        set.start()

        binding.sutarnselimg.setOnClickListener {
            if (clicked == false) {
                set.cancel()
                clicked = true
            }else{
                set.start()
                clicked = false
            }
        }

        binding.selectgametxt.setOnClickListener {
            binding.selectline.animate().apply {
                duration = 1000
               scaleX(1.2f)

            }.withEndAction {
                binding.selectline.animate().apply{
                    duration = 1900
                    scaleX(-1.1f)
                }

            }.start()
        }

        val consoleAnimations = arrayOf(30f,-30f).map { translation ->
            ObjectAnimator.ofFloat(binding.consolehand, "rotation", translation).apply {
                duration = 500
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
            }
        }

        val secset = AnimatorSet()
        secset.playTogether(consoleAnimations)
        binding.consolehand.setOnClickListener {
            if (clicked == false) {
                secset.start()
                clicked = true
            }else{
                secset.cancel()
                clicked = false
            }
        }

        fun showChooseTypeDialog(action: NavDirections) {
            //show when no player wins dialog
            dialog.setContentView(R.layout.choosetype)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
           /* dialog.findViewById<TextView>(R.id.backbtndig).setOnClickListener {
                findNavController().popBackStack()
                dialog.hide()
            }
            dialog.findViewById<TextView>(R.id.plyagndig).setOnClickListener {
                playAgain(binding.playerTwoScore,playerTwoScore)
                playAgain(binding.playerOneScore,playerOneScore)
            }*/

            dialog.show()
        }

        binding.singleplayerbtn.setOnClickListener {
            clicked = true
            userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
                val action = SelectGameFragmentDirections.actionSelectGameFragmentToGameFragment(1,user[0].currentSkin)

                //show dialog then on its choice it will navigat with arguments
                ///showChooseTypeDialog(action)
                findNavController().navigate(action)
            })

            //Snackbar.make(requireContext(),this.requireView(),"Coming soon", Snackbar.LENGTH_LONG).show()
        }
        //userViewModel.updateSkin(R.drawable.skin4,1)
        binding.twoplayerbtn.setOnClickListener {
            clicked = true
            userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
                val action = SelectGameFragmentDirections.actionSelectGameFragmentToGameFragment(2,user[0].currentSkin)
                findNavController().navigate(action)
            })

        }
        binding.marketbtn.setOnClickListener {
            clicked = true
            userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->

                val action = SelectGameFragmentDirections.actionSelectGameFragmentToMarketPlaceFragment(user[0].score,user[0].currentSkin,
                    user[0].skins.toIntArray())

                findNavController().navigate(action)
            })


        }





    }


}