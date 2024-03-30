package com.agaafar.tictactoe.Adapter

import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.agaafar.tictactoe.R
import com.google.android.material.button.MaterialButton

class MarketAdapter (private val itemList: List<marketplacedata>) : RecyclerView.Adapter<MarketAdapter.ViewHolder>() {
    private lateinit var itemListener: onItemClickListener

     interface onItemClickListener{
        fun onItemClick(position:Int)
    }
     fun setOnItemClickListener(listener: onItemClickListener){
           this.itemListener = listener
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View,onItemClickListener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemimg)
        val button: MaterialButton = itemView.findViewById(R.id.buybtn)
        val background: LinearLayout = itemView.findViewById(R.id.backlayout)
        init {
            button.setOnClickListener {
                if (onItemClickListener != null){
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(position)
                    }
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_item_saleitem, parent, false)
        return ViewHolder(view,itemListener)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: MarketAdapter.ViewHolder, position: Int) {
        val currItems = itemList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(currItems.image)

        // sets the text to the textview from our itemHolder class
        holder.button.text = currItems.text
        holder.button.setIconResource(currItems.icon)

        holder.background.setBackgroundResource(currItems.background)

        

    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}