package com.example.hitungpanen;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
			}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean validation(EditText editText) {
    	if(editText.getText().toString().length() == 0)
    	{
    		editText.setError("Harus diisi");
    		
    		return false;
    	}
    	else
    	{
    		editText.setError(null);
    		
    		return true;
    	}
    }
	
	 public void loginOnClick(View view) {
	    	EditText username = (EditText) findViewById(R.id.username);
	    	EditText password = (EditText) findViewById(R.id.password);
	    	
	    	String values = null;
	    	
	    	if(validation(username) && validation(password))
	    	{
	    		try
	    		{
	    			String url = "http://192.168.115.1/panenservice/login.php";
	    			
	    			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    			
	    			postParameters.add(new BasicNameValuePair("username", username.getText().toString()));
	    			postParameters.add(new BasicNameValuePair("password", password.getText().toString()));
	    			
	    			if(android.os.Build.VERSION.SDK_INT > 9)
	    			{
	    				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    				
	    				StrictMode.setThreadPolicy(policy);
	    			}
	    			
	    			String json = CustomHttpClient.executeHttpPost(url, postParameters);
	    			
	    			JSONArray jArray = new JSONArray(json);
	    			
	    			
	    		
	    			
	    			JSONObject jObject = jArray.getJSONObject(0);
	    			values = jObject.getString("nama");
	    			
	    			if(jObject.getString("jumlah").equals("0"))
	    				Toast.makeText(this, "Login Gagal", Toast.LENGTH_LONG).show();
	    			else
	    			{
	    				Toast.makeText(this, "Login Berhasil", Toast.LENGTH_LONG).show();
	    				
	    					    				
	    				Intent intent = new Intent(loginActivity.this, HomeActivity.class);
	    				intent.putExtra("nama",values);
	    				startActivity(intent);
	    			}
	    		}
	    		catch(Exception e)
	    		{
	    			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
	    		}
	    	}
	 }
	 
	 public void kedaftar(View view)
	 {
		 Intent inte= new Intent(loginActivity.this,signupactivity.class);
		 startActivity(inte);
	 }
	 
	 public void keluar(View view)
	 {
		System.exit(1); 
	 }



}
