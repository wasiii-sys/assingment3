package com.example.android3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity implements Adapter.ItemClickListener  {

    Adapter adapter;
    Button btnAdd ,btnDelete;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        recyclerView= findViewById(R.id.rvContact);

getContacts();
    }

    private void setAdapter(ArrayList<Contact> items) {
        adapter = new Adapter(items, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
    public void makeCall(Contact contact) {
//        Intent callIntent = new Intent(Intent.ACTION_CALL );
//        callIntent.setData(Uri.parse("tel:"+contact.getNumber()));
//
//        if (ActivityCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }else {
//            startActivity(callIntent);
        Toast.makeText(this , "Calling...",Toast.LENGTH_LONG).show();
//        }
    }

    public void getContacts(){
        ContentResolver cr = getContentResolver(); //Activity/Application android.content.Context
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null,ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        if (cursor.moveToFirst()) {
            ArrayList<Contact> alContacts = new ArrayList<Contact>();
            do {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String contactNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String contactName = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String contactImage = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

                        alContacts.add(new Contact(contactImage,contactName, contactNumber));
                        break;
                    }
                    pCur.close();
                }
            } while (cursor.moveToNext());
            setAdapter(alContacts);
        }
    }

}