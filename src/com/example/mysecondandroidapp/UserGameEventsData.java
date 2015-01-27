/***
 * Excerpted from "Hello, Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/eband3 for more book information.
***/
package com.example.mysecondandroidapp;

import static android.provider.BaseColumns._ID;
import static com.example.mysecondandroidapp.Constants.USER_TABLE_NAME;
import static com.example.mysecondandroidapp.Constants.UserLoginTIME;
import static com.example.mysecondandroidapp.Constants.UserLogoutTIME;
import static com.example.mysecondandroidapp.Constants.UserName; 		
import static com.example.mysecondandroidapp.Constants.UserScore; 	
import static com.example.mysecondandroidapp.Constants.UserLevel;
import static com.example.mysecondandroidapp.Constants.UserDescription;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class UserGameEventsData extends SQLiteOpenHelper {
   private static final String DATABASE_NAME = "userevents.db";
   private static final int DATABASE_VERSION = 1;

   /** Create a helper object for the Events database */
   public UserGameEventsData(Context ctx) { 
      super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) { 
      db.execSQL("CREATE TABLE " + USER_TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	  		+ UserName + " TEXT NOT NULL," + UserScore + " TEXT NOT NULL," + UserLevel + " TEXT NOT NULL,"
			+ UserLoginTIME + " INTEGER," + UserLogoutTIME + " INTEGER," 
			+ UserDescription + " TEXT NOT NULL);");
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, 
         int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
      onCreate(db);
   }
}