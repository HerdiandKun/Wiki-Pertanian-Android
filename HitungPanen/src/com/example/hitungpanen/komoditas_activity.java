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
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class komoditas_activity extends Activity {
	String name[] = null;
	String link_gambar[] = null;
	Integer masa[] = null;
	String deski[]=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_komoditas);

		Spinner spinnerTanaman = (Spinner) findViewById(R.id.sptanaman);
		
		Intent it = getIntent();

		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("komoditi", it.getStringExtra("komoditi")));
		
		
		Toast.makeText(this, "Anda Memilih "+it.getStringExtra("komoditi"), Toast.LENGTH_LONG).show();

		try {
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
			String result = CustomHttpClient
					.executeHttpPost(
							"http://192.168.115.1/panenservice/detailtanaman.php",
							postParameters);

			JSONArray jArray = new JSONArray(result);

			name = new String[jArray.length()+1];
			link_gambar = new String[jArray.length()+1];
			masa = new Integer[jArray.length()+1];
			deski = new String[jArray.length()+1];
		
			name[0] = "- Pilih Tanaman -";
			for (int i = 1 ; i < jArray.length()+1; i++) {
				JSONObject json_data = jArray.getJSONObject(i-1);
				name[i] = json_data.getString("NAMATANAMAN");
				link_gambar[i] = json_data.getString("GAMBAR");
				deski[i]=json_data.getString("DESKRIPSI");
				masa[i]=json_data.getInt("MASAPANEN");	
			}
			
			
			
		}catch (Exception e) {
				// TODO Auto-generated catch block
				Toast toast1 = Toast.makeText(komoditas_activity.this,
						e.toString(), Toast.LENGTH_LONG);
				toast1.show();
				
			}	
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				komoditas_activity.this, android.R.layout.simple_spinner_item, name);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTanaman.setAdapter(adapter);
		
		
		
		spinnerTanaman.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> spinner, View arg1,
					int position, long arg3) {
				if (position !=0) {
					Intent intent = new Intent(komoditas_activity.this,DetailPanen.class);
					intent.putExtra("link", link_gambar[position]);
					intent.putExtra("nama", name[position]);
					intent.putExtra("masa", masa[position]);
					intent.putExtra("desk", deski[position]);
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
