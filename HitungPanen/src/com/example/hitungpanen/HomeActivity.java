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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class HomeActivity extends Activity {
	private TextView textNama;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activiy_home);
		
		Spinner spinnerKomoditi = (Spinner) findViewById(R.id.komoditi);
		
		textNama = (TextView) findViewById(R.id.namapengguna);
		
		Intent it = getIntent();
		
		String nama = it.getStringExtra("nama");
		
		textNama.setText(nama);
		
		String values[] = null;
		try {
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			String result = CustomHttpClient
					.executeHttpGet("http://192.168.115.1/panenservice/tampilkomoditi.php");

			JSONArray jArray = new JSONArray(result);

			values = new String[jArray.length()+1];

			values[0] = "- Pilih Komoditi -";
			for (int i = 1; i < jArray.length()+1; i++) {
				JSONObject json_data = jArray.getJSONObject(i-1);
				values[i] = json_data.getString("NAMAKOMODITI");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast toast1 = Toast.makeText(HomeActivity.this, e.toString(),
					Toast.LENGTH_LONG);
			toast1.show();

		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				HomeActivity.this, android.R.layout.simple_spinner_item, values);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerKomoditi.setAdapter(adapter);
		
		
		spinnerKomoditi.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> spinner, View arg1,
					int position, long arg3) {
				if (position != 0) {
					String item = spinner.getItemAtPosition(position).toString();
					Intent intent = new Intent(HomeActivity.this,komoditas_activity.class);
					intent.putExtra("komoditi", item);
					startActivity(intent);					
					
				}
			}

			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
						
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
