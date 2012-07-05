package br.com.tdc2012.mdm;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallService extends Service {

	private TelephonyManager telephonyManager;
	private PhoneStateListener onPhoneStatelistener;

	long startTime = 0;
	long stopTime = 0;
	long duration = 0;

	boolean isOutgoing = false;
	boolean isIncoming = false;

	static PhoneCall call;
	public static final String outgoingIntent = "android.intent.action.NEW_OUTGOING_CALL";

	@Override
	public void onCreate() {
		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		call = new PhoneCall();

		IntentFilter intentFilter = new IntentFilter(outgoingIntent);
		registerReceiver(callReceiver, intentFilter);
		
		onPhoneStatelistener = new PhoneStateListener() {
			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:
					if (isOutgoing == true || isIncoming == true) {
						stopTime = System.currentTimeMillis();
						duration = stopTime - startTime;
						call.setCallDuration(duration);
					}
					isOutgoing = false;
					isIncoming = false;
					startTime = 0;
					stopTime = 0;
					duration = 0;
					Log.v("callInfo", call.toString());
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					if (isIncoming == true) {
						call.setCallType("Incoming call");
					}
					break;
				case TelephonyManager.CALL_STATE_RINGING:
					isIncoming = true;
					startTime = System.currentTimeMillis();
					call.setFromNumber(incomingNumber);
					call.setCallType("Missed call");
					break;
				}
				
			}
		};
		
	

		super.onCreate();
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(callReceiver);
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		telephonyManager.listen(onPhoneStatelistener, PhoneStateListener.LISTEN_CALL_STATE);
	}

	private BroadcastReceiver callReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String toNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			isOutgoing = true;
			startTime = System.currentTimeMillis();
			call.setToNumber(toNumber);
			call.setCallType("Outgoing call");
		}

	};

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
