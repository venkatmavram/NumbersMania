package com.example.mysecondandroidapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
	public final static String EXTRA_MESSAGE = "com.example.mysecondandroidapp.MESSAGE";
	
	public int defaultlevel = 0;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
	// Set up click listeners for all the buttons
      View playButton = findViewById(R.id.button_play);
      playButton.setOnClickListener(this);
	  
      View newButton = findViewById(R.id.button_new);
      newButton.setOnClickListener(this);
	  
	  View exitButton = findViewById(R.id.button_exit);
      exitButton.setOnClickListener(this);
      //View aboutButton = findViewById(R.id.about_button);
      //aboutButton.setOnClickListener(this);
      //View exitButton = findViewById(R.id.exit_button);
      //exitButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
	public void onClick(View v) {
      switch (v.getId()) {
    
      case R.id.button_play:
/*	     Intent i = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        i.putExtra(EXTRA_MESSAGE, message);*/
        
         startGame(defaultlevel);
         break;
      // More buttons go here (if any) ...
      case R.id.button_new:
	     //Intent ii = new Intent(this, GamePuzzleActivity.class);
         //startActivity(ii);
         openNewGameDialog();
         break;
      case R.id.button_exit:
         finish();
         
         break;
      }
   }
    /** Ask the user what difficulty level they want */
   private void openNewGameDialog() {
      new AlertDialog.Builder(this)
           .setTitle(R.string.new_game_title)
           .setItems(R.array.difficulty,
            new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialoginterface,
                     int i) {
                  startGame(i);
               }
            })
           .show();
   }
   
   /** Start a new game with the given difficulty level */
   private void startGame(int i) {
      //Log.d(TAG, "clicked on " + i);
      Intent intent = new Intent(this, GamePuzzleActivity.class);
      String newText = "Mytext"+i;
      intent.putExtra(EXTRA_MESSAGE, newText);
      startActivity(intent);
   }
     
 /*   
    // Called when the user clicks the Send button 
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    // Called when the user clicks the Send button 
    public void okMessage(View view) {
        Intent intent = new Intent(this, GamePuzzleActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = "abc";//editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    */
}
