package com.example.contacts_application2

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore

object ContactFetcher {

    private var contactsCache: ArrayList<Contact>? = null

    fun fetchContacts(context: Context): ArrayList<Contact> {

        if (contactsCache != null) return contactsCache!!

        val contactsList = ArrayList<Contact>()

        val contacts = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )

        contacts?.use {
            val nameColumnIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberColumnIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val photoUriColumnIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)

            while (it.moveToNext()) {
                val name = if (nameColumnIndex != -1) it.getString(nameColumnIndex) else ""
                val phoneNumber = if (numberColumnIndex != -1) it.getString(numberColumnIndex) else ""
                val photoUri = if (photoUriColumnIndex != -1) it.getString(photoUriColumnIndex) else null

                val contact = Contact(name, phoneNumber)

                if (photoUri != null) {
                    contact.image = MediaStore.Images.Media.getBitmap(context.contentResolver, Uri.parse(photoUri))
                }
                contactsList.add(contact)
            }
        }

        contactsCache = contactsList
        return contactsList
    }
}
