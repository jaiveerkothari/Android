package com.android.internapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class UserSearch extends OnboardingResources {
TextView selection;
protected EditText sUsername; 
ArrayList<String> userIDs;
ArrayAdapter<String> listAdapter;
    @Override
    public void onStart() {
        super.onStart();

    }
    
    public void getFriends(List<String> numbers) {
    	userIDs = new ArrayList<String>();
	    List<ParseQuery<ParseUser>> queries = new ArrayList<ParseQuery<ParseUser>>();
	    for (String number : numbers) {
	        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
	        queries.add(parseQuery);
	    }

	    ParseQuery<ParseUser> userQuery = ParseQuery.or(queries);

	    userQuery.findInBackground(new FindCallback<ParseUser>() {

	        @Override
	        public void done(List<ParseUser> numberList, ParseException e) {
	            if (e == null) {
	            	listAdapter.clear();
	            	userIDs.clear();
	                for (int i = 0; i < numberList.size(); i++) {
	                    // What to do here?
	                	ParseUser user = numberList.get(i);
	                	String firstname = user.getString("firstName");
	                	String lastname = user.getString("lastName");
	                	String college = user.getString("college");
	                	String major = user.getString("major");
	                	String email = user.getString("email");
	                	String objectid = user.getObjectId();
//	                	if (i == 1) {
//	                	ParseFile image = (ParseFile) user.get("profilePicture");
//	                	byte[] data;
//	                	try {
//	                		data = image.getData();
//	                		Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//		                    ImageView pic;
//	//
//		                    pic = (ImageView) findViewById(R.id.profilepic);
//		                    pic.setImageBitmap(bmp);
//						} catch (ParseException e1) {
//							data = null;
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//	                	}
	                	boolean checkSearch = false;
	                	String searchText = sUsername.getText().toString().toLowerCase();
	                	if (firstname.toLowerCase().contains(searchText)) {
	                		checkSearch = true;
	                	}
	                	if (lastname.toLowerCase().contains(searchText)) {
	                		checkSearch = true;
	                	}
	                	if (college.toLowerCase().contains(searchText)) {
	                		checkSearch = true;
	                	}
	                	if (major.toLowerCase().contains(searchText)) {
	                		checkSearch = true;
	                	}
	                	if (email.toLowerCase().contains(searchText)) {
	                		checkSearch = true;
	                	}
	                	if (checkSearch) {
	                	listAdapter.insert(firstname + " " + lastname, 0);
	                	userIDs.add(0,objectid);
	                	}
	                	
	                	
	                    //Use this list of ParseUser (numberList) in an ArrayAdapter
	                    //or save it in a database
	                }
	                listAdapter.notifyDataSetChanged();
	            }
	        }
	    });
	}
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        
        selection = (TextView) findViewById(R.id.selection);
        sUsername = (EditText)findViewById(R.id.searchFriend);
        // Set up the listview
        ArrayList<String> playerlist = new ArrayList<String>();
        // Create and populate an ArrayList of objects from parse
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        final ListView playerlv = (ListView)findViewById(android.R.id.list);
        playerlv.setAdapter(listAdapter);
        
		
	
		sUsername.addTextChangedListener(new TextWatcher() {
			 
			   public void afterTextChanged(Editable s) {
				   List<String> numbers = new ArrayList<String>();
					numbers.add("3");
					numbers.add("4");
			   getFriends(numbers);
			   }
			 
			   public void beforeTextChanged(CharSequence s, int start, 
			     int count, int after) {
				   List<String> numbers2 = new ArrayList<String>();
					numbers2.add("3");
					numbers2.add("4");
			   getFriends(numbers2);
			   }
			 
			   public void onTextChanged(CharSequence s, int start, 
			     int before, int count) {
				   
			   }
			  });
		
		//getFriends(numbers);
		//listAdapter.notifyDataSetChanged();
        //listAdapter.add("name");
        //listAdapter.notifyDataSetChanged();
		final Intent detailIntent4 = new Intent(this, ViewProfile.class);
		playerlv.setOnItemClickListener(new OnItemClickListener()
		   {
		      @Override
		      public void onItemClick(AdapterView<?> adapter, View v, int position,
		            long arg3) 
		      {
		            String value = (String)adapter.getItemAtPosition(position); 
		            String id = userIDs.get(position);
		            // assuming string and if you want to get the value on click of list item
		            // do what you intend to do on click of listview row
		            detailIntent4.putExtra("name", id);
		            v.getContext().startActivity(detailIntent4);
		      }
		      
		   });
		
//        List<ParseQuery<ParseUser>> queries = new ArrayList<ParseQuery<ParseUser>>();
//	    for (int i = 0; i<2; i++) {
//	        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
//	        queries.add(parseQuery);
//	    }
//
//	    ParseQuery<ParseUser> userQuery = ParseQuery.or(queries);
//
//	    userQuery.findInBackground(new FindCallback<ParseUser>() {
//
//	        @Override
//	        public void done(List<ParseUser> numberList, ParseException e) {
//	            if (e == null) {
//	                for (int i = 0; i < numberList.size(); i++) {
//	                    // What to do here?
//	                	ParseUser user = numberList.get(i);
//	                	String firstname = user.getString("firstName").toString();
//	                	String lastname = user.getString("lastName").toString();
//	                	listAdapter.add(firstname + " " + lastname);
//	                    //Use this list of ParseUser (numberList) in an ArrayAdapter
//	                    //or save it in a database
//	                }
//	            }
//	        }
//	    });
        
        
        
        
        
        
        
        
//        final ParseQuery query = ParseUser.getQuery();
//        //query.findInBackground(new FindCallback() {
//        	query.findInBackground(new FindCallback<ParseUser>() {
//            @Override
//            public void done(List<ParseUser> objects, ParseException e) {
//                if (e == null) {
//                    Toast.makeText(getApplicationContext(), objects.toString(), Toast.LENGTH_LONG)
//                            .show();
//                    for (int i = 0; i < objects.size(); i++) {
//                        ParseUser u = objects.get(i);
//                        String name = u.getString("firstName").toString();
//
//                        //String email = u.getString("email").toString();
//                        listAdapter.add(name);
//
//                        //listAdapter.add(email);
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

}





















//import java.util.ArrayList;
//
//import android.support.v7.app.ActionBarActivity;
//import android.app.ListActivity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.parse.ParseException;
//
//
//
//public class UserSearch extends ListActivity
//{
//    TextView selection;
//    ListItem[] items;
//
//    @Override
//    public void onCreate(Bundle icicle)
//    {
//        super.onCreate(icicle);
//        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");
//        items = new ListItem[myList.size()];
//		items = myList.toArray(items);		
//        		
//        setContentView(R.layout.activity_map);
//        String[] myList2 = {"asf", "fdsf"};
//
//        setListAdapter(new ArrayAdapter<ListItem>(
//                this, android.R.layout.simple_list_item_1, items));
//        selection = (TextView) findViewById(R.id.selection);
//    }
//
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id)
//    {
//        super.onListItemClick(l, v, position, id);
//        final Intent intent = new Intent(this, items[position].getActivity());
//        startActivityForResult(intent, position);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
//    {
//        super.onActivityResult(requestCode, resultCode, intent);
//        if (resultCode == RESULT_OK)
//        {
//            // Perform different actions based on from which activity is
//            // the application returning:
//            switch (requestCode)
//            {
//                case 0:
//                    // TODO: handle the return of the StartTripActivity
//                    break;
//                case 1:
//                    // TODO: handle the return of the ClockinActivity
//                    break;
//                case 2:
//                    // TODO: handle the return of the CustomerSvcActivity
//                    // and so on...
//                    break;
//                default:
//                    break;
//            }
//        }
//        else if (resultCode == RESULT_CANCELED)
//        {
//           // resultsTxt.setText("Canceled");
//        }
//    }
//}