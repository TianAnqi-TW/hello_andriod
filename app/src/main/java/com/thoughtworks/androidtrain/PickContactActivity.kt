package com.thoughtworks.androidtrain

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity

class PickContactActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pick_contact)
        // 选择联系人
        pickContact()
    }
    private fun pickContact() {
        val contactIntent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(contactIntent, PICK_CONTACT_REQUEST)
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { contactUri ->
                val cursor: Cursor? = contentResolver.query(contactUri, null, null, null, null)
                cursor?.use {
                    if (it.moveToFirst()) {
                        val nameColumnIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                        val phoneNumberColumnIndex = it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)

                        val contactName = it.getString(nameColumnIndex)
                        var contactPhoneNumber: String? = null

                        if (it.getInt(phoneNumberColumnIndex) > 0) {
                            val id = it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
                            val phones = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                null,
                                null
                            )
                            phones?.use { phoneCursor ->
                                if (phoneCursor.moveToFirst()) {
                                    contactPhoneNumber = phoneCursor.getString(
                                        phoneCursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Phone.NUMBER
                                        )
                                    )
                                }
                            }
                        }

                        // 返回联系人信息到 MainActivity
                        val resultIntent = Intent()
                        resultIntent.putExtra("name", contactName)
                        resultIntent.putExtra("phoneNumber", contactPhoneNumber)
                        setResult(Activity.RESULT_OK, resultIntent)
                    }
                }
            }
        }
        // 关闭 PickContactActivity
        finish()
    }

    companion object {
        private const val PICK_CONTACT_REQUEST = 1
    }
}