package com.example.mysecondandroidapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.util.Random;
import java.math.*;

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

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GamePuzzleActivity extends Activity implements OnClickListener {
	
	public int correctind;
	public int level;
	
	public UserGameEventsData userGameEvents;
	public SQLiteDatabase db;
	private static String[] FROM = { _ID, UserName, UserScore, };
	private static String ORDER_BY = UserName ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //Initialize database
	    userGameEvents = new UserGameEventsData(this); 
	    db = userGameEvents.getWritableDatabase();
	 
	    
/*    
	      try {
	         addUserGameEvent("Venkat","7","7"); 
	         Cursor cursor = getUserGameEvents(); 
	         getUserGameEvents(cursor); 
	      } finally {
	    	  userGameEvents.close(); 
	      }
*/
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	 
	    // Create the text view
	    //TextView textView = new TextView(this);
	   // textView.setTextSize(40);
	    //textView.setText(message);
	    

	    // Set the text view as the activity layout
	    setContentView(R.layout.game_puzzle);
	    
	    TextView qTextview = (TextView) findViewById(R.id.textview_Question);
	    qTextview.setText(message);
	    

	    char char_level = message.charAt(message.length()-1);
	    level = Character.digit(char_level, 10);

	    
	    qTextview.setText("Level is " + String.valueOf(level));
	 
	    
	    
	    int numanswers = 4;
	    
	    
	    int x = Math.abs(new Random().nextInt());
	    int y = Math.abs(new Random().nextInt()); 
	    
	    int[] ans = new int[4];
	    
	    for(int i = 0; i < numanswers; i++)
	    {
	    	ans[i] = Math.abs(new Random().nextInt());
	    }
	    	    
	    switch (level) {
	    
	    case 0:	x = x%10; 
	    	y = y%10;
	    	for(int i = 0; i < numanswers; i++)
		    {
		    	ans[i] = ans[i]%10;
		    }
	    	break;
	    	
	    case 1:	x = x%100; 
    		y = y%100;
    		for(int i = 0; i < numanswers; i++)
		    {
		    	ans[i] = ans[i]%100;
		    }
    		break;
    	
	    case 2:	x = x%1000; 
    		y = y%1000;
    		for(int i = 0; i < numanswers; i++)
		    {
		    	ans[i] = ans[i]%1000;
		    }
    		break;
	    }
	    
	    int correctans = x+y;
	    correctind = (Math.abs(new Random().nextInt()))%4;
	    ans[correctind] = correctans;
	    
	    qTextview.setText(String.valueOf(x) + " + " + String.valueOf(y) + " = ?");  
	    
	    	    
	    TextView b1 = (TextView) findViewById(R.id.button_AnswerOne);
	    b1.setText(String.valueOf(ans[0]));
	    b1.setOnClickListener(this);
	    
	    
	    TextView b2 = (TextView) findViewById(R.id.button_AnswerTwo);
	    b2.setText(String.valueOf(ans[1]));
	    b2.setOnClickListener(this);
	    
	    
	    TextView b3 = (TextView) findViewById(R.id.button_AnswerThree);
	    b3.setText(String.valueOf(ans[2]));
	    b3.setOnClickListener(this);
	    
	    
	    TextView b4 = (TextView) findViewById(R.id.button_AnswerFour);
	    b4.setText(String.valueOf(ans[3]));
	    b4.setOnClickListener(this);
	    
	    TextView b5 = (TextView) findViewById(R.id.button_YourScore);
		Cursor cursor = getUserGameEvents(); 
        String myscorestring = "0";
        myscorestring = getUserGameEvents(cursor); 
	    b5.setText(myscorestring);
        //b5.setText(String.valueOf(R.string.button_YourScore));
	    b5.setOnClickListener(this);
	    

	}
	



	private void openCorrectAnswerDialog() 
	{
		
		Cursor cursor = getUserGameEvents(); 
        String myscorestring = "0";
        myscorestring = getUserGameEvents(cursor); 
        int score = Integer.valueOf(myscorestring);
        
		  String username = "venkat";
		  String userlevel = String.valueOf(level);
		  String userscore = String.valueOf(score+level+1);
		  
		  addUserGameEvent(username,userlevel,userscore); 
	      new AlertDialog.Builder(this)
	           .setTitle(R.string.answer_correct)
	           .setItems(R.array.answeroptions,
	            new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialoginterface,
	                     int i) {
	            	   
	            	  if(i==1)
	            	  {
	            		    //close database connections
	            		    db.close();
	            		    userGameEvents.close();
	            		  finish();
	            	  }
	            	  else
	            	  {

	            		  startGame(level);

	            	  }
	               }
	            })
	           .show();
	}
	
	private void openWrongAnswerDialog() 
	{
	      new AlertDialog.Builder(this)
	           .setTitle(R.string.answer_wrong)
	           .setItems(R.array.answeroptions,
	            new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialoginterface,
	                     int i) {

		            	 if(i==1)
		            	 {
		            		    //close database connections
		            		    db.close();
		            		    userGameEvents.close();
		            	  finish();
		            	 }
		            	 else
		            	 {

			     	       startGame(level);
			                

		            	 }
	               }

	            })
	           .show();
	}
	private void openYourSCoreDialog() 
	{
		
        ////addUserGameEvent("Venkat","7","7"); 
        Cursor cursor = getUserGameEvents(); 
        String myscorestring = getUserGameEvents(cursor); 
        myscorestring =  myscorestring;
        
	      new AlertDialog.Builder(this)
	           .setTitle(myscorestring)
	           .setItems(R.array.answeroptions,
	            new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialoginterface,
	                     int i) {

		            	 if(i==1)
		            	 {
		            	  finish();
		            	 }
		            	 else
		            	 {
			                startGame(level);
			
		            	 }
	               }

	            })
	           .show();
	}
	
	private void startGame(int level) {
	      Intent intent = getIntent();
	      finish();
	      startActivity(intent);
	}

	public void onClick(View v) {
		
		int selectedans = 0;
		
		switch(v.getId()) {
		
		case R.id.button_AnswerOne:
			selectedans = 0;
			break;
		case R.id.button_AnswerTwo:
			selectedans = 1;
			break;
		case R.id.button_AnswerThree:
			selectedans = 2;
			break;
		case R.id.button_AnswerFour:
			selectedans = 3;
			break;
		case R.id.button_YourScore:
			selectedans = 4;
			break;		
			
		}
		
		if(selectedans == 4)
		{
			openYourSCoreDialog();
		}
		else{
			if(selectedans == correctind)
			{
				openCorrectAnswerDialog();
				
			}
			else
			{	
				openWrongAnswerDialog();
			}
		}
		
	}

	private void addUserGameEvent(String nameString, String userLevel, String userScore) {
	      // Insert a new record into the Events data source.
	      // You would do something similar for delete and update.
	      //SQLiteDatabase db = userGameEvents.getWritableDatabase();
	      ContentValues values = new ContentValues();
	      values.put(UserLoginTIME, System.currentTimeMillis());
	      values.put(UserLogoutTIME, System.currentTimeMillis());
	      values.put(UserName, nameString);
	      values.put(UserScore, userScore);
	      values.put(UserDescription, "My description notes");	
	      values.put(UserLevel, userLevel);	      

	      db.insertOrThrow(USER_TABLE_NAME, null, values);
	   }


		
	private Cursor getUserDBnamesCursor() {
		
		/*
	      // Perform a managed query. The Activity will handle closing
	      // and re-querying the cursor when needed.
	      SQLiteDatabase db = userGameEvents.getReadableDatabase();
	      Cursor cursor = db.query(USER_TABLE_NAME, FROM, null, null, null,
	            null, ORDER_BY);
	      startManagingCursor(cursor);
	      return cursor;
	      
	      */
		
		 String buildSQL = "SELECT DISTINCT " +  UserName + " FROM " + USER_TABLE_NAME + " ORDER BY "+ UserName +" ASC"; 	  
		 return db.rawQuery(buildSQL, null);
	   }
	
	private Cursor getUserGameEvents() {
	      // Perform a managed query. The Activity will handle closing
	      // and re-querying the cursor when needed.
	      SQLiteDatabase db = userGameEvents.getReadableDatabase();
	      Cursor cursor = db.query(USER_TABLE_NAME, FROM, null, null, null,
	            null, ORDER_BY);
	      startManagingCursor(cursor);
	      return cursor;
	   }

	private String getUserGameEvents(Cursor cursor) {
		// TODO Auto-generated method stub
	  
		  String MyScore="0";
	      // Stuff them all into a big string
	      StringBuilder builder = new StringBuilder( 
	            "Saved events:\n");
	      while (cursor.moveToNext()) { 
	         // Could use getColumnIndexOrThrow() to get indexes
	         long id = cursor.getLong(0); 
	         //long time = cursor.getLong(1);
	         MyScore = cursor.getString(2);
	         //builder.append(id).append(": "); 
	        // builder.append(time).append(": ");
	         //builder.append(title).append("\n");
	      }
	      
		    // Display on the screen
	       //TextView scoreButton = (TextView) findViewById(R.id.button_YourScore);
	       //scoreButton.setText(MyScore);
	   
	    return MyScore;
	  
	   }
	
	private String getUsersList(Cursor cursor) {
		// UserName, Userscore, Userlevel
		// UserLoginTime, UserLogoutTime,
		// UserDescription
	  
		  long id = 0;
		  String MyScore="0";
		  String MyUserName="0";
		  String MyUserLevel="0";
		  long  MyLoginTime=0;
		  long  MyLogoutTime=0;
		  String MyUserDescription = "";
	      	      
	      while (cursor.moveToNext()) { 
	         
	         id = cursor.getLong(0); 
	         MyUserName = cursor.getString(1);
	         MyScore = cursor.getString(2);
	         MyUserLevel = cursor.getString(3);
	         MyLoginTime = cursor.getLong(4);
	         MyLogoutTime = cursor.getLong(5);
	         MyUserDescription = cursor.getString(6);
	         
	      }
	      

	    return MyScore;
	  
	   }
	
	private void getUserScores() {
		// UserName, Userscore, Userlevel
		// UserLoginTime, UserLogoutTime,
		// UserDescription
	  
		  long id = 0;
		  String MyScore="0";
		  String MyUserName="0";
		  String MyUserLevel="0";
		  long  MyLoginTime=0;
		  long  MyLogoutTime=0;
		  String MyUserDescription = "";
	      	      
	      while (cursor.moveToNext()) { 
	         
	         id = cursor.getLong(0); 
	         MyUserName = cursor.getString(1);
	         MyScore = cursor.getString(2);
	         MyUserLevel = cursor.getString(3);
	         MyLoginTime = cursor.getLong(4);
	         MyLogoutTime = cursor.getLong(5);
	         MyUserDescription = cursor.getString(6);
	         
	      }
	        
	   }
}
