package com.agaafar.tictactoe

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.agaafar.tictactoe.data.userEntity
import com.agaafar.tictactoe.data.userViewModel
import com.agaafar.tictactoe.data.userViewModelFactory
import com.agaafar.tictactoe.databinding.FragmentGameBinding
import com.agaafar.tictactoe.databinding.FragmentSelectGameBinding
import com.agaafar.tictactoe.databinding.LoseDialogBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class GameFragment : Fragment() {
    private val userViewModel: userViewModel by viewModels {
        userViewModelFactory((activity?.application as TicTacToeApplication).repository)
    }
    //change multiplayer when player two wins show playertwowins dialog
    private lateinit var binding: FragmentGameBinding
    private val args by navArgs<GameFragmentArgs>()
    private var playerOne = ArrayList<Int>()
    private var playerTwo = ArrayList<Int>()
    private var computer = ArrayList<Int>()
    private lateinit var playerOneLayout:LinearLayout
    private lateinit var playerTwoLayout:LinearLayout
    var playerOneScore = 0
    var playerTwoScore = 0
    private lateinit var dialog:Dialog
    private var currentSkin:Int = 0
    private lateinit var skinspairs:Pair<Int,Int>


    var aiTurn = 0

    var activePlayer = 1
    var attemptsCount = 9
    var boardCells = mutableListOf<ImageView>()
    //var remainingEmptyCells = mutableListOf<ImageView>()
//implement db and its sdks for player score and skins they have

    //get the board
   lateinit var image1:ImageView
   lateinit var image2:ImageView
   lateinit var image3:ImageView
   lateinit var image4:ImageView
   lateinit var image5:ImageView
   lateinit var image6:ImageView
   lateinit var image7:ImageView
   lateinit var image8:ImageView
   lateinit var image9:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)




    }

    fun imageClicked(view: View){
        val imgSelected = view as ImageView
        var cellId = 0
        when(imgSelected.id){
            image1.id -> cellId = 1
            image2.id -> cellId = 2
            image3.id -> cellId = 3
            image4.id -> cellId = 4
            image5.id -> cellId = 5
            image6.id -> cellId = 6
            image7.id -> cellId = 7
            image8.id -> cellId = 8
            image9.id -> cellId = 9
        }
        playGame(cellId,imgSelected)
    }

    fun singleImageClicked(view: View){
        val imgSelected = view as  ImageView
        var cellId = 0
        when(imgSelected.id){
            image1.id -> cellId = 1
            image2.id -> cellId = 2
            image3.id -> cellId = 3
            image4.id -> cellId = 4
            image5.id -> cellId = 5
            image6.id -> cellId = 6
            image7.id -> cellId = 7
            image8.id -> cellId = 8
            image9.id -> cellId = 9
        }
        playSingleGame(cellId,imgSelected)

    }

    private fun playSingleGame(cellId: Int, imgSelected: ImageView) {
        //ai put minimax
        //after putting it and test it make levels

        var BoardArray = arrayOf(
            arrayOf("","",""),
            arrayOf("","",""),
            arrayOf("","","")
        )









      /*attemptsCount -= 2
        boardCells.remove(imgSelected)
        var turn = 1
        if(turn == 1){
            imgSelected.setImageResource(skinspairs.first)
            playerOne.add(cellId)
            playerOneLayout.setBackgroundResource(R.drawable.border_dark_blue)
            playerTwoLayout.setBackgroundResource(R.drawable.border_dark_blue)
            turn = 2
            if (turn == 2){
                if(boardCells.isNotEmpty() && boardCells.size > 1) {
                    val i = boardCells.random()
                    var cell2id = 0
                    i.setImageResource(skinspairs.second)
                    i.isEnabled = false
                    when (i.id) {*/
                     /*   image1.id -> cell2id = 1
                        image2.id -> cell2id = 2
                        image3.id -> cell2id = 3
                        image4.id -> cell2id = 4
                        image5.id -> cell2id = 5
                        image6.id -> cell2id = 6
                        image7.id -> cell2id = 7
                        image8.id -> cell2id = 8
                        image9.id -> cell2id = 9

                    }
                    boardCells.remove(i)
                    computer.add(cell2id)

                }
                if (boardCells.size == 0){
                    attemptsCount += 1
                }
            }
        }*/


     /*   if (aiTurn == 0){
            attemptsCount -= 1
            imgSelected.setImageResource(R.drawable.xxxxx)
            playerOne.add(cellId)
            playerOneLayout.setBackgroundResource(R.drawable.border_dark_blue)
            playerTwoLayout.setBackgroundResource(R.drawable.border_dark_blue)
            if(boardCells.isNotEmpty() && boardCells.size > 1) {
                val i = boardCells.random()
                var cell2id = 0
                i.setImageResource(R.drawable.ooo)
                i.isEnabled = false
                when(i.id){
                    image1.id -> cell2id = 1
                    image2.id -> cell2id = 2
                    image3.id -> cell2id = 3
                    image4.id -> cell2id = 4
                    image5.id -> cell2id = 5
                    image6.id -> cell2id = 6
                    image7.id -> cell2id = 7
                    image8.id -> cell2id = 8
                    image9.id -> cell2id = 9

                }
                boardCells.remove(i)
                computer.add(cell2id)


               if(boardCells.size == 1){
                 //  boardCells[0].setImageResource(R.drawable.xxxxx)
                 //  boardCells[0].isEnabled = false
                //    attemptsCount -= 1
                //    playerOne.add(cellId)

                   imgSelected.setImageResource(R.drawable.xxxxx)
                   imgSelected.isEnabled = false
                   playerOne.add(cellId)
                   attemptsCount -= 1


                }
            }
           
        }*/
       /* imgSelected.isEnabled = false
        checkAiWinner()*/


    }


    fun playGame(cellId: Int, imgSelected: ImageView) {
        attemptsCount -= 1
        if (activePlayer == 1){
            imgSelected.setImageResource(skinspairs.first)
            playerOne.add(cellId)
            playerOneLayout.setBackgroundResource(R.drawable.border_dark_blue)
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border)
            activePlayer = 2
        }else{
            imgSelected.setImageResource(skinspairs.second)
            playerTwo.add(cellId)
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border)
            playerTwoLayout.setBackgroundResource(R.drawable.border_dark_blue)
            activePlayer = 1
        }
        imgSelected.isEnabled = false
        checkWinner()


    }



    private fun checkWinner() {
        var winner = -1
        if (attemptsCount == 0){
            Snackbar.make(requireContext(),requireView(),"Draw",Snackbar.LENGTH_LONG).show()
            showDrawDialog()
        }
        //Rows
        if (playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3)){
            winner = 1
            stopGame()
        }
        if (playerTwo.contains(1) && playerTwo.contains(2) && playerTwo.contains(3)){
            winner = 2
            stopGame()
        }

        if (playerOne.contains(4) && playerOne.contains(5) && playerOne.contains(6)){
            winner = 1
            stopGame()
        }
        if (playerTwo.contains(4) && playerTwo.contains(5) && playerTwo.contains(6)){
            winner = 2
            stopGame()
        }

        if (playerOne.contains(7) && playerOne.contains(8) && playerOne.contains(9)) {
            winner = 1
            stopGame()
        }
        if (playerTwo.contains(7) && playerTwo.contains(8) && playerTwo.contains(9)){
            winner = 2
            stopGame()
        }


        //Columns
        if (playerOne.contains(1) && playerOne.contains(4) && playerOne.contains(7)){
            winner = 1
            stopGame()
        }
        if (playerTwo.contains(1) && playerTwo.contains(4) && playerTwo.contains(7)){
            winner = 2
            stopGame()
        }


        if (playerOne.contains(2) && playerOne.contains(5) && playerOne.contains(8)){
            winner = 1
            stopGame()
        }
        if (playerTwo.contains(2) && playerTwo.contains(5) && playerTwo.contains(8)){
            winner = 2
            stopGame()
        }

        if (playerOne.contains(3) && playerOne.contains(6) && playerOne.contains(9)){
            winner = 1
            stopGame()
        }
        if (playerTwo.contains(3) && playerTwo.contains(6) && playerTwo.contains(9)){
            winner = 2
            stopGame()
        }


        //Diagonals
        if (playerOne.contains(1) && playerOne.contains(5) && playerOne.contains(9)){
            winner = 1
            stopGame()
        }
        if (playerTwo.contains(1) && playerTwo.contains(5) && playerTwo.contains(9)){
            winner = 2
            stopGame()
        }

        if (playerOne.contains(3) && playerOne.contains(5) && playerOne.contains(7)){
            winner = 1
            stopGame()
        }
        if (playerTwo.contains(3) && playerTwo.contains(5) && playerTwo.contains(7)){
            winner = 2
            stopGame()
        }



       //determining which player won and make snackbar
        if (winner == 1) {
            Snackbar.make(requireContext(),requireView(),"playerOne wins",Snackbar.LENGTH_LONG).show()
            playerOneScore +=10
            showPlayerOneWinDialog()
        }
        if (winner == 2) {
            Snackbar.make(requireContext(),requireView(),"PlayerTwo wins",Snackbar.LENGTH_LONG).show()
            playerTwoScore += 8
            showPlayerOneLoseDialog()
        }
    }

    private fun checkAiWinner() {
        var winner = -1

        if (attemptsCount == 0){
            Snackbar.make(requireContext(),requireView(),"Draw",Snackbar.LENGTH_LONG).show()
            showDrawDialog()
        }

        //Rows
        if (playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3)){
            winner = 1
            stopGame()
        }else if (computer.contains(1) && computer.contains(2) && computer.contains(3)){
            winner = 2
            stopGame()
        }else if (playerOne.contains(4) && playerOne.contains(5) && playerOne.contains(6)){
            winner = 1
            stopGame()
        }else if (computer.contains(4) && computer.contains(5) && computer.contains(6)){
            winner = 2
            stopGame()
        } else if (playerOne.contains(7) && playerOne.contains(8) && playerOne.contains(9)) {
            winner = 1
            stopGame()
        } else if (computer.contains(7) && computer.contains(8) && computer.contains(9)){
            winner = 2
            stopGame()
        }else if (playerOne.contains(1) && playerOne.contains(4) && playerOne.contains(7)){
            winner = 1
            stopGame()
        } else if (computer.contains(1) && computer.contains(4) && computer.contains(7)){
            winner = 2
            stopGame()
        }else if (playerOne.contains(2) && playerOne.contains(5) && playerOne.contains(8)){
            winner = 1
            stopGame()
        }else if (computer.contains(2) && computer.contains(5) && computer.contains(8)){
            winner = 2
            stopGame()
        }else if (playerOne.contains(3) && playerOne.contains(6) && playerOne.contains(9)){
            winner = 1
            stopGame()
        }else if (computer.contains(3) && computer.contains(6) && computer.contains(9)){
            winner = 2
            stopGame()
        }else if (playerOne.contains(1) && playerOne.contains(5) && playerOne.contains(9)){
            winner = 1
            stopGame()
        }else if (computer.contains(1) && computer.contains(5) && computer.contains(9)){
            winner = 2
            stopGame()
        }else if (playerOne.contains(3) && playerOne.contains(5) && playerOne.contains(7)){
            winner = 1
            stopGame()
        }else if (computer.contains(3) && computer.contains(5) && computer.contains(7)){
            winner = 2
            stopGame()
        }/*else{
            Snackbar.make(requireContext(),requireView(),"Draw",Snackbar.LENGTH_LONG).show()
        }*/



        //determining which player won and make snackbar
        if (winner == 1) {
            Snackbar.make(requireContext(),requireView(),"playerOne wins",Snackbar.LENGTH_LONG).show()
            playerOneScore +=5
            binding.playerOneScore.text = playerOneScore.toString()
           // userViewModel.updateScore(playerOneScore,1)

            showPlayerOneWinDialog()

        }
        if (winner == 2) {
            Snackbar.make(requireContext(),requireView(),"computer wins",Snackbar.LENGTH_LONG).show()
           playerTwoScore += 1
            showPlayerOneLoseDialog()

        }
    }

    private fun showDrawDialog() {
        //show when no player wins dialog
        dialog.setContentView(R.layout.draw_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<TextView>(R.id.backbtndig).setOnClickListener {
            findNavController().popBackStack()
            dialog.hide()
        }
        dialog.findViewById<TextView>(R.id.plyagndig).setOnClickListener {
            playAgain(binding.playerTwoScore,playerTwoScore)
            playAgain(binding.playerOneScore,playerOneScore)
        }
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun showPlayerOneLoseDialog() {
        //show player one losing dialog
        dialog.setContentView(R.layout.lose_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<TextView>(R.id.playerwondigtext).setText("Player Two Wins")
        dialog.findViewById<TextView>(R.id.backbtndig).setOnClickListener {
            findNavController().popBackStack()
            dialog.hide()
        }
        dialog.findViewById<TextView>(R.id.plyagndig).setOnClickListener {
            playAgain(binding.playerTwoScore,playerTwoScore)
        }
        dialog.setCancelable(false)
        dialog.show()

    }

    private fun showPlayerOneWinDialog() {
        //show player one wining dialog
        dialog.setContentView(R.layout.win_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<TextView>(R.id.playerwondigtext).setText("You Win!")
        dialog.findViewById<TextView>(R.id.backbtndig).setOnClickListener {
            findNavController().popBackStack()
            dialog.hide()
        }
        dialog.findViewById<TextView>(R.id.plyagndig).setOnClickListener {
            playAgain(binding.playerOneScore,playerOneScore)

        }
        dialog.setCancelable(false)
        dialog.show()


    }

    private fun stopGame(){
        image1.isEnabled = false
        image2.isEnabled = false
        image3.isEnabled = false
        image4.isEnabled = false
        image5.isEnabled = false
        image6.isEnabled = false
        image7.isEnabled = false
        image8.isEnabled = false
        image9.isEnabled = false

    }
    private fun playAgain(playerText:TextView,playerWon:Int){
        playerText.setText(playerWon.toString())
        boardCells = mutableListOf(image1,image2,image3,image4,image5,image6,image7,image8,image9)
        playerTwo.clear()
        playerOne.clear()
        computer.clear()
        attemptsCount = 9

        dialog.dismiss()
        image1.isEnabled = true
        image2.isEnabled = true
        image3.isEnabled = true
        image4.isEnabled = true
        image5.isEnabled = true
        image6.isEnabled = true
        image7.isEnabled = true
        image8.isEnabled = true
        image9.isEnabled = true

        image1.setImageResource(R.drawable.transparent_back)
        image2.setImageResource(R.drawable.transparent_back)
        image3.setImageResource(R.drawable.transparent_back)
        image4.setImageResource(R.drawable.transparent_back)
        image5.setImageResource(R.drawable.transparent_back)
        image6.setImageResource(R.drawable.transparent_back)
        image7.setImageResource(R.drawable.transparent_back)
        image8.setImageResource(R.drawable.transparent_back)
        image9.setImageResource(R.drawable.transparent_back)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Snackbar.make(requireContext(),this.requireView(),args.btnnavigated.toString(),Snackbar.LENGTH_LONG).show()
        currentSkin = args.currentskin

            skinspairs = when(currentSkin){
                //determine each pair
                // [1, 2, 3, 4, 5, 6] ids of the drawables
                1 -> Pair(R.drawable.xxxxx,R.drawable.ooo)
                2 -> Pair(R.drawable.xskin2,R.drawable.oskin2)
                3 -> Pair(R.drawable.xskin3,R.drawable.oskin3)
                4 -> Pair(R.drawable.xskin4,R.drawable.oskin4)
                5 -> Pair(R.drawable.xskin5,R.drawable.oskin5)
                6 -> Pair(R.drawable.xskin6,R.drawable.oskin6)
                else -> Pair(R.drawable.xxxxx,R.drawable.ooo)
            }


        binding.gameback.setOnClickListener {
             findNavController().popBackStack()
         }




         dialog = Dialog(requireContext())
         image1 = binding.image1
         image2 = binding.image2
         image3 = binding.image3
         image4 = binding.image4
         image5 = binding.image5
         image6 = binding.image6
         image7 = binding.image7
         image8 = binding.image8
         image9 = binding.image9
        playerOneLayout = binding.playerOneLayout
        playerTwoLayout = binding.playerTwoLayout





        if (args.btnnavigated == 2){
            //user choosed two players mode
            binding.playerXiconLayout.setImageResource(skinspairs.first)
            binding.playerOiconLayout.setImageResource(skinspairs.second)
            binding.image1.setOnClickListener {
                imageClicked(it)
            }
            binding.image2.setOnClickListener {
                imageClicked(it)
            }
            binding.image3.setOnClickListener {
                imageClicked(it)
            }
            binding.image4.setOnClickListener {
                imageClicked(it)
            }
            binding.image5.setOnClickListener {
                imageClicked(it)
            }
            binding.image6.setOnClickListener {
                imageClicked(it)
            }
            binding.image7.setOnClickListener {
                imageClicked(it)
            }
            binding.image8.setOnClickListener {
                imageClicked(it)
            }
            binding.image9.setOnClickListener {
                imageClicked(it)
            }


        }else if (args.btnnavigated == 1){
            binding.playerOneName.text = "You"
            binding.playerTwoName.text = "Computer"
            binding.playerXiconLayout.setImageResource(skinspairs.first)
            binding.playerOiconLayout.setImageResource(skinspairs.second)
            boardCells = mutableListOf(image1,image2,image3,image4,image5,image6,image7,image8,image9)
            //user chose single player mode

           //use ai minimax or make moves on the board with highest score for the computer to move
          // that will either block the other player from wining or make your chance to win bigger
            binding.image1.setOnClickListener {
                singleImageClicked(it)
            }
            binding.image2.setOnClickListener {
                singleImageClicked(it)
            }
            binding.image3.setOnClickListener {
                singleImageClicked(it)
            }
            binding.image4.setOnClickListener {
                singleImageClicked(it)
            }
            binding.image5.setOnClickListener {
                singleImageClicked(it)
            }
            binding.image6.setOnClickListener {
                singleImageClicked(it)
            }
            binding.image7.setOnClickListener {
                singleImageClicked(it)
            }
            binding.image8.setOnClickListener {
                singleImageClicked(it)
            }
            binding.image9.setOnClickListener {
                singleImageClicked(it)
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (args.btnnavigated == 1) {
            userViewModel.updateScore(playerOneScore, 1)
        }

    }


}