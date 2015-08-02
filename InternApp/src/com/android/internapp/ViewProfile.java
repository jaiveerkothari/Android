package com.android.internapp;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ViewProfile extends Activity {
	TextView profilename;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_profile);
		profilename = (TextView) findViewById(R.id.profilename);
		
		
		
		
		
		
		String newString;
		if (savedInstanceState == null) {
		    Bundle extras = getIntent().getExtras();
		    if(extras == null) {
		        newString= null;
		    } else {
		        newString= extras.getString("name");
		    }
		} else {
		    newString= (String) savedInstanceState.getSerializable("name");
		}
		//profilename.setText(newString);
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
	    query.whereEqualTo("objectId",newString);
	    query.findInBackground(new FindCallback<ParseObject>() {
	        public void done(List<ParseObject> objects, ParseException e) {
	            if (e == null) {
	            	ParseObject user = objects.get(0);
	            	String email = user.getString("username");
	        		String firstname = user.getString("firstName");
	        	    String lastname = user.getString("lastName");
	        	    String address = user.getString("address");
	        	    String phonenumber = user.getString("phoneNumber");
	        	    String college = user.getString("college");
	        	    String major = user.getString("major");
	        	    String about = user.getString("about");
	        	    
	        		profilename.setText(email);
	        		
	        		
	                	ParseFile image = (ParseFile) user.get("profilePicture");
	                	byte[] data;
	                	try {
	                		BitmapFactory.Options opt;
	                		opt = new BitmapFactory.Options();
	                		
	                		data = image.getData();
	                		Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);

	                		bmp = getRoundedShape(bmp);
	                        ImageView pic;
	                        pic = (ImageView) findViewById(R.id.searchpic);

	                        pic.setImageBitmap(bmp);


	        			} catch (ParseException e1) {
	        				data = null;
	        				// TODO Auto-generated catch block
	        				e1.printStackTrace();
	        			}
	                // row of Object Id "U8mCwTHOaC"
	            } else {
	                // error
	            }
	        }
	    });
		
	    
		
		

		
		
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_profile, menu);
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
	public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
		  // TODO Auto-generated method stub
		  int targetWidth = 600;
		  int targetHeight = 600;
		  Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, 
		                            targetHeight,Bitmap.Config.ARGB_8888);
		  
		 Canvas canvas = new Canvas(targetBitmap);
		  Path path = new Path();
		  path.addCircle(((float) targetWidth - 1) / 2,
		  ((float) targetHeight - 1) / 2,
		  (Math.min(((float) targetWidth), 
		                ((float) targetHeight)) / 2),
		          Path.Direction.CCW);
		  
		                canvas.clipPath(path);
		  Bitmap sourceBitmap = scaleBitmapImage;
		  canvas.drawBitmap(sourceBitmap, 
		                                new Rect(0, 0, sourceBitmap.getWidth(),
		    sourceBitmap.getHeight()), 
		                                new Rect(0, 0, targetWidth,
		    targetHeight), null);
		  return targetBitmap;
	}
	// 2.0 and above
	@Override
	public void onBackPressed() {
		deleteCache(this);
		super.onBackPressed();
	}
	public static void deleteCache(Context context) {
	    try {
	        File dir = context.getCacheDir();
	        if (dir != null && dir.isDirectory()) {
	            deleteDir(dir);
	        }
	    } catch (Exception e) {}
	}

	public static boolean deleteDir(File dir) {
	    if (dir != null && dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }
	    return dir.delete();
	}
	
}
