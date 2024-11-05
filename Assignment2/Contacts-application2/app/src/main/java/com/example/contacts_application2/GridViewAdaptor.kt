package com.example.contacts_application2

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class GridViewAdaptor(private var context: Activity, private var contactsList: ArrayList<Contact>): BaseAdapter() {
    override fun getCount(): Int {
        return contactsList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.contact_grid_item, parent, false)

        val image = view.findViewById<ImageView>(R.id.img_avatar)
        val fullName = view.findViewById<TextView>(R.id.full_name)
        val mobileNumber = view.findViewById<TextView>(R.id.mobile_number)

        val contact = contactsList[position]
        fullName.text = contact.name
        mobileNumber.text = contact.phoneNumber

        contact.image?.let {
            img -> image.setImageBitmap(img)
        } ?: run {
            image.setImageResource(R.mipmap.ic_launcher_round)
        }

        return view
    }
}