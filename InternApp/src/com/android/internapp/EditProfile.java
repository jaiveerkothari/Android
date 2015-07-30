package com.android.internapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;
import com.parse.SaveCallback;

public class EditProfile extends Activity {
	TextView t1;
	EditText e1; 
	TextView t2;
	EditText e2; 
	TextView t3;
	EditText e3; 
	TextView t4;
	EditText e4; 
	TextView t5;
	EditText e5; 
	TextView t6;
	EditText e6; 
	TextView t7;
	EditText e7; 
	TextView t8;
	EditText e8;
	TextView t9;
	EditText e9; 
	ParseUser user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		final Button button = (Button) findViewById(R.id.saveChanges);
		final Intent detailIntent4 = new Intent(this, Profile.class);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				saveChanges();
				startActivity(detailIntent4);
            }
        });
		
		
		
		t1 = (TextView) findViewById(R.id.t1);
        e1 = (EditText)findViewById(R.id.e1);
        t2 = (TextView) findViewById(R.id.t2);
        e2 = (EditText)findViewById(R.id.e2);
        t3 = (TextView) findViewById(R.id.t3);
        e3 = (EditText)findViewById(R.id.e3);
        t4 = (TextView) findViewById(R.id.t4);
        e4 = (EditText)findViewById(R.id.e4);
        t5 = (TextView) findViewById(R.id.t5);
        e5 = (EditText)findViewById(R.id.e5);
        t6 = (TextView) findViewById(R.id.t6);
        e6 = (EditText)findViewById(R.id.e6);
        t7 = (TextView) findViewById(R.id.t7);
        e7 = (EditText)findViewById(R.id.e7);
        t8 = (TextView) findViewById(R.id.t8);
        e8 = (EditText)findViewById(R.id.e8);
        t9 = (TextView) findViewById(R.id.t9);
        e9 = (EditText)findViewById(R.id.e9);
        t1.setText("First Name:");
        t2.setText("Last Name:");
        t3.setText("Address:");
        t4.setText("Phone Number:");
        t5.setText("College:");
        t6.setText("Major:");
        t7.setText("Change Password:");
        t8.setText("Retype Password:");
        t9.setText("About Me:");
        user = ParseUser.getCurrentUser();
        
        e1.setText(user.getString("firstName"));
        e2.setText(user.getString("lastName"));
        e3.setText(user.getString("address"));
        e4.setText(user.getString("phoneNumber"));
        e5.setText(user.getString("college"));
        e6.setText(user.getString("major"));
        e9.setText(user.getString("about"));
        
        
        
        
	}
	public void saveChanges() {
		user.put("firstName", e1.getText().toString());
        user.put("lastName", e2.getText().toString());
        user.put("address", e3.getText().toString());
        user.put("phoneNumber", e4.getText().toString());
        user.put("college", e5.getText().toString());
        user.put("major", e6.getText().toString());
        user.put("about", e9.getText().toString());
        if ((!e7.getText().toString().trim().matches("")) & e7.getText().toString().equals(e8.getText().toString())) {
        user.setPassword(e7.getText().toString());
        }
        user.saveInBackground(new SaveCallback() {
        	public void done(com.parse.ParseException e) {
        		  if (e == null) {
        		    // Save was successful!
 
        		  } else {
        		    // Save failed. Inspect e for details.

        		  }
        		}
        });
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_profile, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
