package com.android.internapp;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
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
	private static int PICK_PHOTO_FOR_AVATAR = 1;
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
		
        final Button button2 = (Button) findViewById(R.id.editpic);
		final Intent detailIntent = new Intent(this, Profile.class);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	pickImage();
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
	private byte[] readInFile(String path) throws IOException {
	    // TODO Auto-generated method stub
	    byte[] data = null;
	    File file = new File(path);
	    InputStream input_stream = new BufferedInputStream(new FileInputStream(file));
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    data = new byte[16384]; // 16K
	    int bytes_read;
	    while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
	        buffer.write(data, 0, bytes_read);
	    }
	    input_stream.close();
	    return buffer.toByteArray();

	}
  private String getRealPathFromURI(Uri contentURI) {
	    String result;
	    Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
	    if (cursor == null) { // Source is Dropbox or other similar local file path
	        result = contentURI.getPath();
	    } else { 
	        cursor.moveToFirst(); 
	        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA); 
	        result = cursor.getString(idx);
	        cursor.close();
	    }
	    return result;
	}
	public void pickImage() {
		  Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		  intent.setType("image/*");
		  
		  startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
		}

		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
		    super.onActivityResult(requestCode, resultCode, data);
		    if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
		        if (data == null) {
		            //Display an error
		            return;
		        }
		        Uri selectedImageURI = data.getData();
		        String picturePath = getRealPathFromURI(selectedImageURI);
		        byte[] image = null;
			    try {
	                image = readInFile(picturePath);
	                //ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	                //Bitmap bmp2 = BitmapFactory.decodeByteArray(image, 0, image.length);
	                //imageView.setImageBitmap(bmp2);      
	            }
	            catch(Exception e) { 
	                e.printStackTrace();
	            }

	            // Create the ParseFile
	            ParseFile file = new ParseFile("picturePath", image);
	            // Upload the image into Parse Cloud
	            try {
					file.save();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	            file.saveInBackground();

	    
	            user.put("profilePicture", file);
	            user.saveInBackground();
		    }
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
