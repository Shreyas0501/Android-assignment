package com.example.contacts_application2

import android.content.pm.PackageManager
import android.os.Bundle
import android.Manifest
import android.content.ContentProviderOperation
import android.content.DialogInterface
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

class AddNewContactFragment: Fragment() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var buttonAddNewContact: Button
    private lateinit var buttonGoBack: ImageView
    private lateinit var name: String
    private lateinit var phoneNumber: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new_contact, container, false)

        editTextName = view.findViewById(R.id.et_name)
        editTextPhoneNumber = view.findViewById(R.id.et_phone_number)
        buttonAddNewContact = view.findViewById(R.id.btn_add_contact)

        buttonGoBack = view.findViewById<ImageView>(R.id.btn_go_back)

        buttonAddNewContact.setOnClickListener() {
            name = editTextName.text.toString()
            phoneNumber = editTextPhoneNumber.text.toString()

            if (name.isNotBlank() && phoneNumber.isNotBlank()) {
                saveNewContact(name, phoneNumber)
            } else {
                Toast.makeText(activity, "Please enter name and phone number", Toast.LENGTH_LONG).show()
            }
        }

        buttonGoBack.setOnClickListener() {
            name = editTextName.text.toString()
            phoneNumber = editTextPhoneNumber.text.toString()
            if (name.isNotEmpty() || phoneNumber.isNotEmpty() ) {
                println("Not blank!")
                showDialog()
            } else {
                println("Blank!")
                parentFragmentManager.popBackStack()
            }
        }

        return view
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setIcon(R.drawable.ic_delete)
        dialog.setTitle("Confirm Discard")
        dialog.setMessage("Are you sure you want to discard?")

        dialog.setPositiveButton("DISCARD") { dialogInterface, _ ->
            parentFragmentManager.popBackStack()
            dialogInterface.dismiss()
        }
        dialog.setNegativeButton("CANCEL", object : DialogInterface.OnClickListener {
            override fun onClick(dialogInterface: DialogInterface, which: Int) {
                dialogInterface.dismiss()
            }
        })

        dialog.create().show()

    }

    private fun saveNewContact(name: String, phoneNumber: String) {

        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.WRITE_CONTACTS),
                1
            )
//            println("PERMISSION NOT GRANTED!")
            return
        }

        val ops = ArrayList<ContentProviderOperation>()

        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build()
        )

        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .build()
        )

        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build()
        )

        try {
            requireActivity().contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)
            Toast.makeText(activity, "Contact saved successfully", Toast.LENGTH_SHORT).show()

            editTextName.text.clear()
            editTextPhoneNumber.text.clear()
            parentFragmentManager.popBackStack()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(activity, "Failed to save contact", Toast.LENGTH_SHORT).show()
        }


    }

}