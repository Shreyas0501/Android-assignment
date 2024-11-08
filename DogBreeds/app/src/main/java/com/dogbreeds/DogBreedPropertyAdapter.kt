// DogBreedPropertyAdapter.kt
package com.dogbreeds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class DogBreedPropertyAdapter(
    private val context: Context,
    private val properties: List<DogBreedProperty>
) : BaseAdapter() {

    override fun getCount(): Int = properties.size

    override fun getItem(position: Int): Any = properties[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_dog_breed_property, parent, false)
        val property = properties[position]

        val titleTextView = view.findViewById<TextView>(R.id.property_title)
        val valueTextView = view.findViewById<TextView>(R.id.property_value)

        if (property.value.isBlank()) {
            property.value = "Not Specified"
        }

        titleTextView.text = property.title
        valueTextView.text = property.value

        return view
    }
}
