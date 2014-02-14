package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * To go to the SJSU Site
	 */
	public void gotoSite(View view) {
		Uri uri = Uri.parse("http://www.sjsu.edu/");
		//Action View activity action: displays data from the uri to the user
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	
	/**
	 * To check the stock entered by the user and provide the value
	 */
	public void checkStock(View view){
		Intent intent =new Intent(this, MainActivity.class);
		EditText enteredText= (EditText) findViewById(R.id.edit_message);
		String stockName= enteredText.getText().toString();
		
	}
}
