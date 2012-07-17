package com.alberovalley.smstutorial;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnviaMensaje extends Activity implements OnClickListener{

	Button btnSendSMS;
    EditText txtPhoneNo;
    EditText txtMessage;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
 
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
 
        btnSendSMS.setOnClickListener(this);        
    }

	@Override
	public void onClick(View v) {
		String phoneNo = txtPhoneNo.getText().toString();
        String message = txtMessage.getText().toString();                 
        if (phoneNo.length()>0 && message.length()>0)                
            sendSMS(phoneNo, message);                
        else
            Toast.makeText(getBaseContext(), 
                "Please enter both phone number and message.", 
                Toast.LENGTH_SHORT).show();
		
	}    
	
	private void sendSMS(String phoneNumber, String message)
    {        
        PendingIntent pi = PendingIntent.getActivity(this, 0,
            new Intent(this, EnviaMensaje.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        try{
        	Log.i("SMStutorial", "longitud SMS = " + message.length());
        	sms.sendTextMessage(phoneNumber, null, message, pi, null);
        }catch(Exception e){
        	Log.e("SMStutorial", e.getMessage());
        }
    }    

	
}
