/***
 * Excerpted from "Hello, Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/eband3 for more book information.
***/
package com.example.mysecondandroidapp;

import android.provider.BaseColumns;
public interface Constants extends BaseColumns {
   public static final String USER_TABLE_NAME = "usergameevents";
   // Columns in the Events database
   public static final String UserName 			= "username";
   public static final String  UserScore 		= "userscore";
   public static final String UserLevel 		= "userlevel";
   public static final String UserLoginTIME 	= "userlogintime";
   public static final String UserLogoutTIME 	= "userlogouttime";
   public static final String UserDescription	= "userdescription";  
   
}
