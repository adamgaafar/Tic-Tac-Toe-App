package com.agaafar.tictactoe.Adapter

import android.content.res.Resources
import android.view.View
import com.agaafar.tictactoe.R

//add price and ads false or true and make if and else for it
//in marketplacefragment check user current skin and make its text picked
//check price and user money= score when pressing at a skin to buy
//when he picks one change all the remaining skins to its normal text =" select"
data class marketplacedata(val image: Int, var text: String, val icon:Int, val skinId: Int, var price:Int, var ads:Boolean, val purchasedState:Boolean,
                           var background:Int = R.drawable.thirdplayerbtnback, var watchedAds:Int =0)
/*{
    /*fun changeText(newText: String){
        this.text = newText
    }
    init {
        if (this.changeText.first == true){
            changeText(this.changeText.second)
        }else{
            changeText(this.text)
        }*/
    }
}*/
