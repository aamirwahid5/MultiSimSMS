package project.aamir.sheikh.multisimsms;

import android.content.Context;

/**
 * Created by Aamir on 25-01-2017.
 */

public class MultiSimSMS {

    public MultiSimSMS(Context context) {
        Model.setContext(context);
    }

    public static void setNumber(String number) {
        Model.setMobNumber(number);
    }
    public static void setMessage(String message) {
        Model.setMessage(message);
    }

    public static void sendMessage() {
        ImplSendSms.sendTextMessage();
    }
    public static void sendMessage(setOnSMSListener listener) {
        Model.setListener(listener);
        ImplSendSms.sendTextMessage();
    }
    public static void initialize(Context context) {
        Model.setContext(context);
    }

    public interface setOnSMSListener {
        void onSmsSent();

        void onGenericFailure();

        void onNoService();

        void onNullPdu();

        void onRadioOff();

        void onInvalidDestinationAddress();

        void ServiceAvailOnSingleSim(CharSequence Carrier, int id);

        void SingleSimOrLessBuild();
    }

}

