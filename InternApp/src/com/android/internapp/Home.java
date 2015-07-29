package com.android.internapp;

import com.parse.Parse;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends Activity {
	TextView profile1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		profile1 = (TextView) findViewById(R.id.profile1);
		ParseUser user = ParseUser.getCurrentUser();
		String email = user.getString("firstName");
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
		  int targetWidth = 50;
		  int targetHeight = 50;
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
