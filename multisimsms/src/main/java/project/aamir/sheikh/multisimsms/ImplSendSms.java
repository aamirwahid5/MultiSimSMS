package project.aamir.sheikh.multisimsms;

import android.app.PendingIntent;
import android.content.Intent;

/**
 * Created by Aamir on 25-01-2017.
 */

public class ImplSendSms {

    public static void sendTextMessage() {
        String SENT = "SMS_SENT";

        PendingIntent sentPI = PendingIntent.getBroadcast(Model.getContext(), 0,
                new Intent(SENT), 0);
        if (Model.getListener() != null) {
            new UtilImpl().registerReceiver(SENT, Model.getListener());
        }
        new UtilImpl().checkCondition(sentPI);
    }
}
