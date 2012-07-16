package com.alberovalley.smstutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		// obtenemos el array de estructuras pdus
		Object messages[] = (Object[]) bundle.get("pdus");

		SmsMessage smsMessage[] = new SmsMessage[messages.length];

		for (int n = 0; n < messages.length; n++) {
			// generamos mensajes sms a partir de las estructuras pdu			
			smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}

		// mostramos el primer mensaje
		Toast toast = Toast.makeText(context, "SMS Recibido: " + 
				smsMessage[0].getMessageBody(), Toast.LENGTH_LONG);
		toast.show();
		/* algunas de las propiedades que pueden obtenerse de un SMS 
		smsMessage[0].getDisplayOriginatingAddress() //dirección de origen
		smsMessage[0].getIndexOnSim() // posición en la SIM
		smsMessage[0].getStatusOnSim() //estado: leído, sin leer, envíado, sin enviar
		*/
	}

}
