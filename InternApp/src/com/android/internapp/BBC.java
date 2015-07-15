package com.android.internapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BBC extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crown_point);
		Intent intent2 = new Intent();
		intent2.setClass(this, Map.class);
		intent2.putExtra("EXTRA_ID", "SOME DATAS");
		startActivity(intent2);
		String uri = "geo:0,0?q=10598%20Medallion%20Drive%2C%20Cincinnati%2C%20Ohio%2045241";
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
		intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		startActivity(intent);
	
	}
}

