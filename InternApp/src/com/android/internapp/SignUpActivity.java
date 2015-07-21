package com.android.internapp;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse; 
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Activity which displays a login screen to the user.
 */
public class SignUpActivity extends Activity {
  // UI references.
  private EditText usernameEditText;
  private EditText passwordEditText;
  private EditText firstnameEditText;
  private EditText lastnameEditText;
  private EditText passwordAgainEditText;
  private static int RESULT_LOAD_IMAGE = 1;

  Bitmap bmp;
  Intent i;
  Uri BmpFileName = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_signup);

    // Set up the signup form.
    usernameEditText = (EditText) findViewById(R.id.username_edit_text);
    firstnameEditText = (EditText) findViewById(R.id.firstname_text);
    lastnameEditText = (EditText) findViewById(R.id.lastname_text);
    passwordEditText = (EditText) findViewById(R.id.password_edit_text);
    passwordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text);
    passwordAgainEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == R.id.edittext_action_signup ||
            actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
          signup();
          return true;
        }
        return false;
      }
    });

    // Set up the submit button click handler
    Button mActionButton = (Button) findViewById(R.id.action_button);
    mActionButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        signup();
      }
    });
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
  
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent  data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null  != data) {
          Uri selectedImage = data.getData();
//          String[] filePathColumn = { MediaStore.Images.Media.DATA };
//          Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
//          cursor.moveToFirst();
//          int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//          String picturePath = cursor.getString(columnIndex);
          Uri selectedImageURI = data.getData();
          String picturePath = getRealPathFromURI(selectedImageURI);
          
          
          
//          PreferenceManager.getDefaultSharedPreferences(this).edit().putString("picturePath", picturePath).commit();
//          cursor.close();
         
          
//          	Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//			// Convert it to byte
//			ByteArrayOutputStream stream = new ByteArrayOutputStream();
//			// Compress image to lower quality scale 1 - 100
//			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//			byte[] image = stream.toByteArray();

			String username = usernameEditText.getText().toString().trim();
		    String password = passwordEditText.getText().toString().trim();
		    String firstname = firstnameEditText.getText().toString().trim();
		    String lastname = lastnameEditText.getText().toString().trim();
		    String passwordAgain = passwordAgainEditText.getText().toString().trim();
		    
		    
			ParseUser user = new ParseUser();
		    user.setUsername(username);
		    user.setPassword(password);
		    //user.setEmail(username);
		    user.put("firstName", firstname);
		    user.put("lastName", lastname);
		    user.put("email", username);
		    user.put("location", "Evendale, OH");
		    user.put("program", "EID Intern");
			
			
			
			
			
			
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

            // Create a New Class called "ImageUpload" in Parse
            //ParseObject imgupload = new ParseObject("Image");

            // Create a column named "ImageName" and set the string
            //imgupload.put("Image", "picturePath");


            // Create a column named "ImageFile" and insert the image
            //imgupload.put("ImageFile", file);

            // Create the class and the columns
            user.put("profilePicture", file);
            user.saveInBackground();

            // Show a simple toast message
            //Toast.makeText(LoadImg.this, "Image Saved, Upload another one ",Toast.LENGTH_SHORT).show();
          
			 user.signUpInBackground(new SignUpCallback() {
			      @Override
			      public void done(ParseException e) {
			        //dialog.dismiss();
			        if (e != null) {
			          // Show the error message
			          Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
			        } else {
			          // Start an intent for the dispatch activity
			          Intent intent = new Intent(SignUpActivity.this, ItemListActivity.class);
			          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
			          startActivity(intent);
			        }
			      }
			    });
          
          
          
          
       } 
}
  
  
  
  private void signup() {
//    String username = usernameEditText.getText().toString().trim();
//    String password = passwordEditText.getText().toString().trim();
//    String firstname = firstnameEditText.getText().toString().trim();
//    String lastname = lastnameEditText.getText().toString().trim();
//    String passwordAgain = passwordAgainEditText.getText().toString().trim();

    // Validate the sign up data
//    boolean validationError = false;
//    StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
//    if (username.length() == 0) {
//      validationError = true;
//      validationErrorMessage.append(getString(R.string.error_blank_username));
//    }
//    if (password.length() == 0) {
//      if (validationError) {
//        validationErrorMessage.append(getString(R.string.error_join));
//      }
//      validationError = true;
//      validationErrorMessage.append(getString(R.string.error_blank_password));
//    }
//    if (!password.equals(passwordAgain)) {
//      if (validationError) {
//        validationErrorMessage.append(getString(R.string.error_join));
//      }
//      validationError = true;
//      validationErrorMessage.append(getString(R.string.error_mismatched_passwords));
//    }
//    validationErrorMessage.append(getString(R.string.error_end));
//
//    // If there is a validation error, display the error
//    if (validationError) {
//      Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
//          .show();
//      return;
//    }

    
//    String storageState = Environment.getExternalStorageState();
//    if (storageState.equals(Environment.MEDIA_MOUNTED)) {
//
//    String path = Environment.getExternalStorageDirectory().getName() + File.separatorChar + "Android/data/" + this.getPackageName() + "/files/" + "Doc1" + ".jpg";
//
//    File photoFile = new File(path);
//    try {
//    if (photoFile.exists() == false) { 
//    photoFile.getParentFile().mkdirs();
//    photoFile.createNewFile();
//    }
//    } 
//    catch (IOException e) 
//    {
//    Log.e("DocumentActivity", "Could not create file.", e);
//    }
//    Log.i("DocumentActivity", path);
//    BmpFileName = Uri.fromFile(photoFile);
//    i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//    i.putExtra(MediaStore.EXTRA_OUTPUT, BmpFileName);
//    startActivityForResult(i, 0);
    
    Intent i = new Intent(
            Intent.ACTION_PICK,
             android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(i, RESULT_LOAD_IMAGE);
    
 
    
    
    
    // Set up a progress dialog
    final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
    dialog.setMessage(getString(R.string.progress_signup));
    dialog.show();

    // Set up a new Parse user
//    ParseUser user = new ParseUser();
//    user.setUsername(username);
//    user.setPassword(password);
//    //user.setEmail(username);
//    user.put("firstName", firstname);
//    user.put("lastName", lastname);
//    user.put("email", username);
//    user.put("location", "Evendale, OH");
//    user.put("program", "EID Intern");
    
    
 // Ensure bmp has value
//    if (bmp == null || BmpFileName == null) {
//    Log.d ("Error" , "Problem with image");
//    return;
//    }
//    ParseFile pFile = null ;
//    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//    bmp.compress(CompressFormat.PNG, 100, stream);
//    pFile = new ParseFile("DocImage.png", stream.toByteArray());
//    try 
//    {
//    pFile.save();
//    user.put("profilePicture", pFile);
//    //user.save();
//    //_mParse.DisplayMessage("Image Saved");
//    } 
//    catch (ParseException e) 
//    {
//    // TODO Auto-generated catch block
//    //_mParse.DisplayMessage("Error in saving image");
//    e.printStackTrace();
//    }
    
    
    // Call the Parse signup method
//    user.signUpInBackground(new SignUpCallback() {
//      @Override
//      public void done(ParseException e) {
//        dialog.dismiss();
//        if (e != null) {
//          // Show the error message
//          Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//        } else {
//          // Start an intent for the dispatch activity
//          Intent intent = new Intent(SignUpActivity.this, ItemListActivity.class);
//          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//          startActivity(intent);
//        }
//      }
//    });
  }
}

