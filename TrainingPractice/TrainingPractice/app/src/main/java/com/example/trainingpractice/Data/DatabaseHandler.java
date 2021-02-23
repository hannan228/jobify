package com.example.trainingpractice.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.trainingpractice.Model.Contact;
import com.example.trainingpractice.Utils.Util;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME,null, Util.DATABASE_VERSION);
    }

    // we are creating our table in following method
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL - Structured Query Language
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // deleting table
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);

        // Create table again

        onCreate(db);
    }

    /**
     * CRUD Operation
     */

    // Add Contact
    public void addContact(Contact contact){
        SQLiteDatabase db  =this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Util.KEY_NAME, contact.getName());
        value.put(Util.KEY_PHONE_NUMBER,contact.getPhoneNumber());

        //insert to row

        db.insert(Util.TABLE_NAME,null,value);
        db.close();
    }

    // Get a contact

    public Contact getContact(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[] { Util.KEY_ID,
        Util.KEY_NAME,Util.KEY_PHONE_NUMBER},Util.KEY_ID + "=?",
                new String[] {String.valueOf(id)},null,null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
            Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),cursor.getString(2));

            return contact;
    }

    // Get all contacts

    public List<Contact>  getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Contact> contactList = new ArrayList<>();

        //select all contacts

        String selectAll = "SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll,null);

        // Loop through our contact

        if (cursor.moveToFirst()){
            do {
                Contact contact  =new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                // add contact to our list

                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }

    //update contact

    public int updateContacts(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Util.KEY_NAME,contact.getName());
        value.put(Util.KEY_PHONE_NUMBER,contact.getPhoneNumber());

        // updte row
        return db.update(Util.TABLE_NAME,value,Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});

    }

    //Delete single Contact
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});

        db.close();
    }


    //Get contacts count
    public int getContactCount(){
        String countQuery = "SELECT * FROM "+ Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db. rawQuery(countQuery,null);

        // we can't return database after closing database
        //        db.close();

        return cursor.getCount();
    }
}
