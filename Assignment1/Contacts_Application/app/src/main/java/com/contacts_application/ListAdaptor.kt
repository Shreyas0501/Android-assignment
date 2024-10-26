package com.contacts_application

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class ListAdaptor(private val context: Activity, private val arrayList: ArrayList<Contact>) :
    ArrayAdapter<Contact>(context, R.layout.contact_list_item, arrayList) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.contact_list_item, parent, false)

        val image = view.findViewById<CircleImageView>(R.id.img_avatar)
        val fullName = view.findViewById<TextView>(R.id.full_name)
        val mobileNumber = view.findViewById<TextView>(R.id.mobile_number)


        fullName.text = arrayList[position].firstName + " " + arrayList[position].lastName
        mobileNumber.text = arrayList[position].mobileNumber

        Glide.with(view.context)
            .load(arrayList[position].avatarUrl)
            .into(image)

        return view
    }
}