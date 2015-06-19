
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

public class signupactivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
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
	
	 public void signupOnClick(View view) {
	    	EditText username = (EditText) findViewById(R.id.usernamesignup);
	    	EditText password = (EditText) findViewById(R.id.passwordsignup);
	    	EditText nama = (EditText) findViewById(R.id.namasignup);
	    	
	    	
	    	
	    	
	    	if(validation(username) && validation(password) && validation(nama))
	    	{
	    		try
	    		{
	    			String url = "http://192.168.115.1/panenservice/signup.php";
	    			
	    			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    			
	    			postParameters.add(new BasicNameValuePair("username", username.getText().toString()));
	    			postParameters.add(new BasicNameValuePair("password", password.getText().toString()));
	    			postParameters.add(new BasicNameValuePair("nama", nama.getText().toString()));
	    			
	    			if(android.os.Build.VERSION.SDK_INT > 9)
	    			{
	    				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    				
	    				StrictMode.setThreadPolicy(policy);
	    			}
	    			
	    			CustomHttpClient.executeHttpPost(url, postParameters);
	    			
	    		
	    				Toast.makeText(this, "Pendaftaran Berhasil", Toast.LENGTH_LONG).show();
	    				
	    				Intent intent = new Intent(signupactivity.this, loginActivity.class);
	    				
	    				startActivity(intent);
	    			
	    		}
	    		catch(Exception e)
	    		{
	    			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
	    		}
	    	}
	    }



}
