package br.com.tdc2012.mdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class TDC2012MDMActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		startService(new Intent(TDC2012MDMActivity.this,
				CallService.class));
		
		Log.v("It", "worked");

    }
    
    protected void onDestroy() {
    	stopService(new Intent(TDC2012MDMActivity.this,
				CallService.class));
    	
    };
}