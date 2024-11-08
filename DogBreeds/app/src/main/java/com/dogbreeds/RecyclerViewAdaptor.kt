package com.dogbreeds

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerViewAdaptor(private val context: Activity, private val dogBreedsList: ArrayList<DogBreed>):
    RecyclerView.Adapter<RecyclerViewAdaptor.MyViewHolder>() {

    private lateinit var myListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClicking(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        myListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView, myListener, dogBreedsList)
    }

    override fun getItemCount(): Int {
        return dogBreedsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dogBreedsList[position]
        holder.name.text = currentItem.name

        Picasso.get().load(currentItem.url).into(holder.image)

    }

    class MyViewHolder( itemView: View, listener: OnItemClickListener, dogBreedsList: ArrayList<DogBreed>): RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var image: CircleImageView = itemView.findViewById(R.id.img_avatar)

        init {

            itemView.setOnClickListener {
                listener.onItemClicking(adapterPosition)
//                val dogBreedDetailsFragment = DogBreedDetailsFragment(dogBreedsList[adapterPosition])
            }
        }
    }
}