package com.android.internapp;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.app.Application; 

//import com.parse.Parse; 
//import com.parse.ParseObject;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details (if present) is a
 * {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity implements
	ItemListFragment.Callbacks {
	ArrayList<ParseUser> users;
	ArrayList<String> userFullNames;
	ListItem[] items;

	
	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);
		users = new ArrayList<ParseUser>();
		userFullNames = new ArrayList<String>();
		
		

		
		
		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}
	
	public void getFriends(List<String> numbers) {
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
	                for (int i = 0; i < numberList.size(); i++) {
	                    // What to do here?
	                	ParseUser user = numberList.get(i);
	                	String firstname = user.getString("firstName");
	                	String lastname = user.getString("lastName");
	                	userFullNames.add(firstname + " " + lastname);
	                    //Use this list of ParseUser (numberList) in an ArrayAdapter
	                    //or save it in a database
	                }
	            }
	        }
	    });
	}
	
	
	
	
	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			ItemDetailFragment fragment = new ItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			if (id.equals("1")) {
				Intent detailIntent1 = new Intent(this, Profile.class);
				detailIntent1.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
				startActivity(detailIntent1);
			} else if(id.equals("2")) {
				Intent detailIntent2 = new Intent(this, UserSearch.class);
				List<String> numbers = new ArrayList<String>();
				numbers.add("3");
				numbers.add("4");
				getFriends(numbers);
				detailIntent2.putExtra("mylist", userFullNames);
				detailIntent2.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
				startActivity(detailIntent2);	
			} else if(id.equals("3")) {
				Intent detailIntent3 = new Intent(this, CustomListViewAndroidExample.class);
				detailIntent3.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
				startActivity(detailIntent3);	
			} else if(id.equals("4")) {
				Intent detailIntent4 = new Intent(this, OnboardingResources.class);
				detailIntent4.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
				startActivity(detailIntent4);	
			}
			
		}
	}
}
