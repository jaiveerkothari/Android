package com.android.internapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
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
import com.android.internapp.OnboardingResources.NavItem;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

public class Profile extends Activity {
	TextView profile1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		final Button button = (Button) findViewById(R.id.editprofile);
		final Intent detailIntent4 = new Intent(this, EditProfile.class);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				startActivity(detailIntent4);
            }
        });
		
        
        
        
        
        
        
		
		profile1 = (TextView) findViewById(R.id.profile1);
		
		ParseUser user = ParseUser.getCurrentUser();
		String email = user.getUsername();
		String firstname = user.getString("firstName");
	    String lastname = user.getString("lastName");
	    String address = user.getString("address");
	    String phonenumber = user.getString("phoneNumber");
	    String college = user.getString("college");
	    String major = user.getString("major");
	    String about = user.getString("about");
	    
	    
		profile1.setText(email);
		
		
        	ParseFile image = (ParseFile) user.get("profilePicture");
        	byte[] data;
        	try {
        		data = image.getData();
        		Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);

        		Bitmap circleBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);

        		BitmapShader shader = new BitmapShader (bmp,  TileMode.CLAMP, TileMode.CLAMP);
        		Paint paint = new Paint();
        		        paint.setShader(shader);
        		paint.setAntiAlias(true);
        		Canvas c = new Canvas(circleBitmap);
        		c.drawCircle(bmp.getWidth()/2, bmp.getHeight()/2, bmp.getWidth()/2, paint);

        		
        		bmp = getRoundedShape(bmp);
                ImageView pic;

                pic = (ImageView) findViewById(R.id.userpic);

                pic.setImageBitmap(bmp);

                //pic.setImageBitmap(circleBitmap);

			} catch (ParseException e1) {
				data = null;
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
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

		
	
	
}
