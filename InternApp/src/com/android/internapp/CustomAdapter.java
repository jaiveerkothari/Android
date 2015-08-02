package com.android.internapp;
import java.util.ArrayList;
import com.parse.ParseFile;
import com.parse.ParseUser;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapter extends BaseAdapter implements OnClickListener {
          
         /*********** Declare Used Variables *********/
         private Activity activity;
         private ArrayList data;
         private static LayoutInflater inflater=null;
         public Resources res;
         ListModel tempValues=null;
         int i=0;
          
         /*************  CustomAdapter Constructor *****************/
         public CustomAdapter(Activity a, ArrayList d,Resources resLocal) {
              
                /********** Take passed values **********/
                 activity = a;
                 data=d;
                 res = resLocal;
              
                 /***********  Layout inflator to call external xml layout () ***********/
                  inflater = ( LayoutInflater )activity.
                                              getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              
         }
      
         /******** What is the size of Passed Arraylist Size ************/
         public int getCount() {
              
             if(data.size()<=0)
                 return 1;
             return data.size();
         }
      
         public Object getItem(int position) {
             return position;
         }
      
         public long getItemId(int position) {
             return position;
         }
          
         /********* Create a holder Class to contain inflated xml file elements *********/
         public static class ViewHolder{
              
             public TextView text;
             public TextView text1;
             public TextView textWide;
             public ImageView image;
      
         }
      
         /****** Depends upon data size called for each row , Create each ListView row *****/
         public View getView(int position, View convertView, ViewGroup parent) {
              
             View vi = convertView;
             ViewHolder holder;
              
             if(convertView==null){
                  
                 /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                 vi = inflater.inflate(R.layout.tabitem, null);
                  
                 /****** View Holder Object to contain tabitem.xml file elements ******/
 
                 holder = new ViewHolder();
                 holder.text = (TextView) vi.findViewById(R.id.text);
                 holder.text1=(TextView)vi.findViewById(R.id.text1);
                 holder.image=(ImageView)vi.findViewById(R.id.image);
                  
                /************  Set holder with LayoutInflater ************/
                 vi.setTag( holder );
             }
             else 
                 holder=(ViewHolder)vi.getTag();
              
             if(data.size()<=0)
             {
                 holder.text.setText("No Data");
                  
             }
             else
             {
                 /***** Get each Model object from Arraylist ********/
                 tempValues=null;
                 tempValues = ( ListModel ) data.get( position );
                  
                 /************  Set Model values in Holder elements ***********/
 
                  holder.text.setText( tempValues.getCompanyName() );
                  holder.text1.setText( tempValues.getUrl() );
                  ParseUser user = ParseUser.getCurrentUser();
          		  String email = user.getString("firstName");
          		
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
                          holder.image.setImageBitmap(bmp); }
                  	catch (Exception e){
                  		e.hashCode();
                  	}
                  	
                   
                   
                  /******** Set Item Click Listner for LayoutInflater for each row *******/
 
                  vi.setOnClickListener(new OnItemClickListener( position ));
                  
             }
             return vi;
         }
          
         @Override
         public void onClick(View v) {
                 Log.v("CustomAdapter", "=====Row button clicked=====");
         }
          
         /********* Called when Item click in ListView ************/
         private class OnItemClickListener  implements OnClickListener{           
             private int mPosition;
              
             OnItemClickListener(int position){
                  mPosition = position;
             }
              
             @Override
             public void onClick(View arg0) {
 
        
               CustomListViewAndroidExample sct = (CustomListViewAndroidExample)activity;
 
              /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
 
                 sct.onItemClick(mPosition);
             }               
         }   
     }