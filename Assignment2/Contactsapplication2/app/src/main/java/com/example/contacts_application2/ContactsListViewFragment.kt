package com.example.contacts_application2

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ContactsListViewFragment: Fragment() {

    private var contactsList: ArrayList<Contact> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonAddNewContact: ImageView
    private lateinit var fragmentActionListener: FragmentActionListener
    private lateinit var buttonGoBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_contacts_list_view, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)
            == PackageManager.PERMISSION_GRANTED) {
            loadContacts()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf (
                    android.Manifest.permission.READ_CONTACTS,
                    android.Manifest.permission.WRITE_CONTACTS
                ),
                PackageManager.PERMISSION_GRANTED
            )
        }

        buttonAddNewContact = view.findViewById(R.id.btn_add_new_contact)
        buttonAddNewContact.setOnClickListener() {
            fragmentActionListener.onAddNewContactFragment()
        }

        buttonGoBack = view.findViewById(R.id.btn_go_back)
        buttonGoBack.setOnClickListener() {
            parentFragmentManager.popBackStack()
        }
    }

    fun setFragmentActionListener(fragmentActionListener: FragmentActionListener) {
        this.fragmentActionListener = fragmentActionListener
    }

    private fun loadContacts() {
        contactsList = ContactFetcher.fetchContacts(requireContext())
        recyclerView.adapter = RecyclerViewAdaptor(requireActivity(), contactsList)
    }

}