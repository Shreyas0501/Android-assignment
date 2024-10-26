package com.contacts_application

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment

class ContactsGridView : Fragment() {

    private lateinit var gridView: GridView
    private lateinit var contactFetcher: ContactFetcher

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_grid, container, false)
        gridView = view.findViewById(R.id.grid_view)
        contactFetcher = ContactFetcher()
        fetchContacts()
        return view
    }

    private fun fetchContacts() {
        contactFetcher.fetchContacts(requireContext()) { contactsList ->
            if (contactsList != null) {
                val adaptor = GridAdaptor(requireActivity(), contactsList)
                gridView.adapter = adaptor
            } else {
                Log.d("contact gird view", "Failed to fetch contacts")
            }
        }
    }
}
