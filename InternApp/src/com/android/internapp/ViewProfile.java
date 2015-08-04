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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.internapp.OnboardingResources.DrawerListAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
public class ViewProfile extends OnboardingResources {
	TextView profilename;
	TextView profile_firstName;
	TextView profile_lastName;
	TextView profile_address;
	TextView profile_email;
	TextView profile_phoneNumber;
	TextView profile_college;
	TextView profile_major;
	TextView profile_about;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_profile);
		profilename = (TextView) findViewById(R.id.profilename);
		profile_firstName=(TextView)findViewById(R.id.profile_firstName);
        profile_lastName=(TextView)findViewById(R.id.profile_lastName);
        profile_address=(TextView)findViewById(R.id.profile_address);
        profile_email=(TextView)findViewById(R.id.profile_email);
        profile_phoneNumber=(TextView)findViewById(R.id.profile_phoneNumber);
        profile_college=(TextView)findViewById(R.id.profile_college);
        profile_major=(TextView)findViewById(R.id.profile_major);
        profile_about=(TextView)findViewById(R.id.profile_about);
		
		
		
		 // DrawerLayout
	    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
	 
	    // Populate the Navigtion Drawer with options
	    mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
	    mDrawerList = (ListView) findViewById(R.id.navList);
	    DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
	    mDrawerList.setAdapter(adapter);
	 
	    // Drawer Item click listeners
	    mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            selectItemFromDrawer(position);
	        }
	    });
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
	        @Override
	        public void onDrawerOpened(View drawerView) {
	            super.onDrawerOpened(drawerView);
	     
	            invalidateOptionsMenu();
	        }
	     
	        @Override
	        public void onDrawerClosed(View drawerView) {
	            super.onDrawerClosed(drawerView);
	           // Log.d(TAG, "onDrawerClosed: " + getTitle());
	     
	            invalidateOptionsMenu();
	        }
	    };
	     
	    mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		
		
		
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
	        		profile_firstName.setText(firstname);
	        		profile_lastName.setText(lastname);
	        		profile_email.setText(email);
	        		profile_address.setText(address);
	        		profile_phoneNumber.setText(phonenumber);
	        		profile_college.setText(college);
	        		profile_major.setText(major);
	        		profile_about.setText(about);
	        		
	        		
	                	ParseFile image = (ParseFile) user.get("profilePicture");
	                	byte[] data;
	                	try {
	                		BitmapFactory.Options opt;
	                		opt = new BitmapFactory.Options();
	                		BitmapFactory.Options o = new BitmapFactory.Options();
	             	        // Decode with inSampleSize
	             	        BitmapFactory.Options o2 = new BitmapFactory.Options();
	             	        o2.inSampleSize = 2;
	             	       o2.inPreferredConfig = Bitmap.Config.RGB_565;
	                		data = image.getData();
	                		Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, o2);
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
		super.finish();
		//super.onBackPressed();
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