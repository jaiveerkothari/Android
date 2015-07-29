//package com.android.internapp;
//
//import java.util.ArrayList;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.graphics.Rect;
//import android.graphics.Shader.TileMode;
//import android.os.Bundle;
//import android.support.v4.app.ActionBarDrawerToggle;
//import android.support.v4.widget.DrawerLayout;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.android.internapp.OnboardingResources.DrawerListAdapter;
//import com.android.internapp.OnboardingResources.NavItem;
//import com.parse.ParseException;
//import com.parse.ParseFile;
//import com.parse.ParseUser;
//
//public class Home extends OnboardingResources {
//	TextView profile1;
//	private static String TAG = OnboardingResources.class.getSimpleName();
//	 
//	ListView mDrawerList;
//	RelativeLayout mDrawerPane;
//	public ActionBarDrawerToggle mDrawerToggle;
//	public DrawerLayout mDrawerLayout;
//	 
//	ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_home);
//		
//	    // DrawerLayout
//	    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//	 
//	    // Populate the Navigtion Drawer with options
//	    mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
//	    mDrawerList = (ListView) findViewById(R.id.navList);
//	    DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
//	    mDrawerList.setAdapter(adapter);
//	 
//	    // Drawer Item click listeners
//	    mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//	        @Override
//	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//	            selectItemFromDrawer(position);
//	        }
//	    });
//		
//		
//		
//		profile1 = (TextView) findViewById(R.id.profile1);
//		ParseUser user = ParseUser.getCurrentUser();
//		String email = user.getString("firstName");
//		profile1.setText(email);
//		
//        	ParseFile image = (ParseFile) user.get("profilePicture");
//        	byte[] data;
//        	try {
//        		data = image.getData();
//        		Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//
//        		Bitmap circleBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
//
//        		BitmapShader shader = new BitmapShader (bmp,  TileMode.CLAMP, TileMode.CLAMP);
//        		Paint paint = new Paint();
//        		        paint.setShader(shader);
//        		paint.setAntiAlias(true);
//        		Canvas c = new Canvas(circleBitmap);
//        		c.drawCircle(bmp.getWidth()/2, bmp.getHeight()/2, bmp.getWidth()/2, paint);
//
//        		        		
//        		
//        		
//        		
//        		
//        		
//        		
//        		
//        		bmp = getRoundedShape(bmp);
//                ImageView pic;
//
//                pic = (ImageView) findViewById(R.id.userpic);
//                pic.setImageBitmap(bmp);
//                //pic.setImageBitmap(circleBitmap);
//
//			} catch (ParseException e1) {
//				data = null;
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		
//	}
//	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.home, menu);
//		return true;
//	}
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//	public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
//		  // TODO Auto-generated method stub
//		  int targetWidth = 50;
//		  int targetHeight = 50;
//		  Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, 
//		                            targetHeight,Bitmap.Config.ARGB_8888);
//		  
//		                Canvas canvas = new Canvas(targetBitmap);
//		  Path path = new Path();
//		  path.addCircle(((float) targetWidth - 1) / 2,
//		  ((float) targetHeight - 1) / 2,
//		  (Math.min(((float) targetWidth), 
//		                ((float) targetHeight)) / 2),
//		          Path.Direction.CCW);
//		  
//		                canvas.clipPath(path);
//		  Bitmap sourceBitmap = scaleBitmapImage;
//		  canvas.drawBitmap(sourceBitmap, 
//		                                new Rect(0, 0, sourceBitmap.getWidth(),
//		    sourceBitmap.getHeight()), 
//		                                new Rect(0, 0, targetWidth,
//		    targetHeight), null);
//		  return targetBitmap;
//	}
//
//		
//	
//	
//}
