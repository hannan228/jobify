package com.example.trainingpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.trainingpractice.Data.DatabaseHandler;
import com.example.trainingpractice.Model.Contact;

import java.util.List;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        TextView textView = findViewById(R.id.hello);

        DatabaseHandler db = new DatabaseHandler(this);

        //insert contacts
        Log.d("inser: ", "Interting");
        db.addContact(new Contact("Farooq","3034418344"));
        db.addContact(new Contact("Humza","33477477"));
        db.addContact(new Contact("Butt sab","3923"));

        // read then back

        Log.d("Reading", "reading all contacts....");

        List<Contact> contactList = db.getAllContacts();

//        reading all contact
//        for (Contact c : contactList){
//            String log = "ID: "+c.getId() + " , Name:"+ c.getName()+ " ,Phone:"+ c.getPhoneNumber();
//            Log.d("Name: ", log);
//        }

//        reading only index 1 row
//        Contact contact = db.getContact(1);
//        String rowOne = "ID: "+contact.getId() + " , Name:"+ contact.getName()+ " ,Phone:"+ contact.getPhoneNumber();
//        Log.d("Name: ", rowOne);

        // update contact
//        Contact updateContacts = db.getContact(1);
//        updateContacts.setName("Abdul Hafeez");
//        updateContacts.setPhoneNumber("03064141234");
//        int updated = db.updateContacts(updateContacts);
//        String updatedRow = "ID: "+updateContacts.getId() + " , Name:"+ updateContacts.getName()+ " ,Phone:"+ updateContacts.getPhoneNumber();
//        Log.d("Name: ", updatedRow);

        // delete contact
//        Contact deleteContacts = db.getContact(5);
//        db.deleteContact(deleteContacts);

        for (Contact c : contactList){
            String log = "ID: "+c.getId() + " , Name:"+ c.getName()+ " ,Phone:"+ c.getPhoneNumber();
            Log.d("Name: ", log);

            textView.setText(""+log);
        }

        // count number of row
        Log.d("Counts: ",""+ db.getContactCount());


    }

}