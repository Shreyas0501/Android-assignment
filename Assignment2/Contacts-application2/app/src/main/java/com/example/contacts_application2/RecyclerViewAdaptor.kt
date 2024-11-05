package com.example.contacts_application2
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdaptor(private val context: Activity, private val contactsList: ArrayList<Contact>):
    RecyclerView.Adapter<RecyclerViewAdaptor.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false)
        return MyViewHolder(itemView, contactsList)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = contactsList[position]
        holder.name.text = currentItem.name
        holder.phoneNumber.text = currentItem.phoneNumber

        if (currentItem.image != null) {
            holder.profile.setImageBitmap(currentItem.image)
        } else {
            holder.profile.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_launcher_round))
        }

    }

    class MyViewHolder( itemView: View,  contactsList: ArrayList<Contact>): RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var phoneNumber: TextView = itemView.findViewById(R.id.phone_number)
        var profile: ImageView = itemView.findViewById(R.id.img_avatar)
    }
}