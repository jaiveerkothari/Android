package com.android.internapp;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Bldg800 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bldg800);
		Intent intent2 = new Intent();
		intent2.setClass(this, Map.class);
		intent2.putExtra("EXTRA_ID", "SOME DATAS");
		startActivity(intent2);
		String uri = "geo:0,0?q=39.239799,-84.448132(label)";
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		startActivity(intent);
	}
}
