package project.aamir.sheikh.multisimsms;

import android.content.Context;

/**
 * Created by Aamir on 25-01-2017.
 */

public class Model {
    private static Context context;
    private static String message;
    private static String mobNumber;

    private static MultiSimSMS.setOnSMSListener listener;

    public static MultiSimSMS.setOnSMSListener getListener() {
        return listener;
    }

    public static void setListener(MultiSimSMS.setOnSMSListener listener) {
        Model.listener = listener;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Model.context = context;
    }

    public static String getMobNumber() {
        return mobNumber;
    }

    public static void setMobNumber(String mobNumber) {
        Model.mobNumber = mobNumber;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        Model.message = message;
    }


}
