package com.example.androidteamproject;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetLocation();

        Button sendBtn = (Button)findViewById(R.id.sendSmsBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 긴급요청 버튼 이벤트
                EditText addrTxt = (EditText)findViewById(R.id.addrEditText);

                try {
                    sendSMS(addrTxt.getText().toString(), url); // 문자 보내기 함수 호출
                    Toast.makeText(MainActivity.this, "SMS 발송 완료", Toast.LENGTH_LONG).show(); // 확인 토스트

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    // 문자 보내기 함수
    private void sendSMS(String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        registerReceiver(new BroadcastReceiver(){
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        message = "긴급 상황 발생!!" + "\n" + message + "사건 발생 위치 위 링크 클릭";
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI); // 문자 메세지 보내기
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public void GetLocation()
    {
        try{
            LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location lastlocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            //tv.setText("수신중...");
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,100,1,mLocationListener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,1,mLocationListener);

        }
        catch(SecurityException e)
        {
            Toast.makeText(getApplicationContext(),"★에러 !! "+e.getMessage(),Toast.LENGTH_LONG).show();
            // 위치 정보 수집 권한 없을때 발생
        }
    }

    //위치 리스너!!
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("★","위치 변경됨 : "+location);

            double longtitude =location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();
            double accuracy = location.getAccuracy();
            String provider = location.getProvider();
            //tv.setText("경도 : "+longtitude+" / 위도 : "+latitude);
            url = "https://www.google.co.kr/maps/@"+latitude+","+longtitude+",17z";
            // 구글 지도 URL
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.d("★","Status Changed, status : "+s);
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.d("★","Provider Enabled, provider : "+s);
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.d("★","Provider Disabled, provider : "+s);
        }
    };
}
