package com.contacts_application

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class ContactsListViewFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var contactFetcher: ContactFetcher

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)
        listView = view.findViewById(R.id.list_view)
        contactFetcher = ContactFetcher()
        fetchContacts()
        return view
    }

    private fun fetchContacts() {
        contactFetcher.fetchContacts(requireContext()) { contactsList ->
            if (contactsList != null) {
                val adaptor = ListAdaptor(requireActivity(), contactsList)
                listView.adapter = adaptor
            } else {
                Log.d("ContactsListView", "Failed to fetch contacts")
            }
        }
    }
}
