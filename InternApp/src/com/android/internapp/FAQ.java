package com.android.internapp;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
public class FAQ extends ActionBarActivity {
	TextView txt_help_gest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faq);
		
		//Log.i("Faq Activity","onCreate");
		//txt_help_gest=(TextView) findViewById(R.id.txt_help_gest);
	    // hide until its title is clicked
	   // txt_help_gest.setVisibility(View.GONE);
		
	}
	
//	public void toggle_contents(View v){
//	      txt_help_gest.setVisibility( txt_help_gest.isShown()
//	                          ? View.GONE
//	                          : View.VISIBLE );
//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.faq, menu);
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
}