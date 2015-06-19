package com.example.hitungpanen;

import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPanen extends Activity {
	
	private ImageView imageView;
	private TextView textNama;
	private TextView textmasa;
	private TextView textdesk;
	private Bitmap loadedImage;
	private long loadInterval = 1000L * 60L * 60L;
	private long lastLoadTime = 0;
	private String imageHttpAddress = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_panen);
		
		imageView = (ImageView) findViewById(R.id.imgTanaman);
		textNama = (TextView) findViewById(R.id.txtnama);
		textmasa = (TextView) findViewById(R.id.txtMasa);
		textdesk =(TextView) findViewById(R.id.txtdesk);
		
		
		Intent intent = getIntent();

		String linkGambar = intent.getStringExtra("link");
		String nama = intent.getStringExtra("nama");
		Integer masa = intent.getIntExtra("masa",0);
		String desk = intent.getStringExtra("desk");

		textNama.setText(nama);
		textmasa.setText(masa.toString());
		textdesk.setText(desk);
		
		imageHttpAddress = linkGambar;
		downloadFile(imageHttpAddress);
		
	}
	
	private void downloadFile(String imageHttpAddress) {
		if (System.currentTimeMillis() - lastLoadTime > loadInterval) {
			lastLoadTime = System.currentTimeMillis();
			URL imageUrl = null;
			try {
				if (android.os.Build.VERSION.SDK_INT > 9) {
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
					StrictMode.setThreadPolicy(policy);
				}
				imageUrl = new URL(imageHttpAddress);
				HttpURLConnection conn = (HttpURLConnection) imageUrl
						.openConnection();
				conn.connect();
				loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
				imageView.setImageBitmap(loadedImage);

			} catch (Exception e) {
				Toast.makeText(getApplicationContext(),
						"Unable to load image: " + e.toString(),
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			
		}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}




