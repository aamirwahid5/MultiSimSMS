package project.aamir.sheikh.multisimsms;

import android.app.PendingIntent;

/**
 * Created by Aamir on 26-01-2017.
 */

public abstract class Utils {

    public abstract int getNoOfSims();

    public abstract int getSubscriptionId(int index);

    public abstract CharSequence getCarrierName(int index);

    public abstract void sendSMS(PendingIntent sentPI);

    public abstract void sendSMS(PendingIntent sentPI, int id);

    public abstract void registerReceiver(String SENT, final MultiSimSMS.setOnSMSListener listener);

    public abstract void showAlertDialog(final PendingIntent sentPI);

    public abstract void checkCondition(PendingIntent sentPI);

}
