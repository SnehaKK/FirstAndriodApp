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
		
		EditText enteredText = (EditText) findViewById(R.id.edit_message);
		String stockName = enteredText.getText().toString();
		new getStockPrice().execute(stockName);
	}
    
	public class getStockPrice extends AsyncTask<String, Void, String> {
        
		/**
		 * The system calls this to perform work in a worker thread and delivers
		 * it the parameters given to AsyncTask.execute()
		 */
		protected String doInBackground(String... params) {
            
			// Get the stock price and display it to the user.
            
			// URL to retrieve the stock value
			HttpClient Client = new DefaultHttpClient();
			String URL = "http://finance.yahoo.com/d/quotes.csv?f=l1&s="
            + params[0];
            
			try {
                
				HttpGet httpget = new HttpGet(URL);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String response = Client.execute(httpget, responseHandler);
				System.out.println("response" + response);
                
				return response;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
        
		/**
		 * The system calls this to perform work in the UI thread and delivers
		 * the result from doInBackground()
		 */
		protected void onPostExecute(String result) {
			// Toast is used to display a pop up in android.
			// Toast toast = Toast.makeText(getApplicationContext(), result,
			// Toast.LENGTH_SHORT);
			// toast.show();
            
			// Update the stock on the UI.
			// EditText stockText= (EditText) findViewById(R.id.edit_message);
			// stockText.setText(result);
			TextView stockValue = (TextView) findViewById(R.id.stock_value);
			stockValue.setText("Stock Value:" + result);
		}
	}
}
