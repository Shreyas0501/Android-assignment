package com.example.contacts_application2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class ContactsGridViewFragment: Fragment() {

    private var contactsList: ArrayList<Contact> = ArrayList()
    private lateinit var gridView: GridView
    private lateinit var fragmentActionListener: FragmentActionListener
    private lateinit var buttonAddNewContact: ImageView
    private lateinit var buttonGoBack: ImageView

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_contacts_grid_view, container, false)
        gridView = view.findViewById(R.id.grid_view)
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
        gridView.adapter = GridViewAdaptor(requireActivity(), contactsList)
    }


}
