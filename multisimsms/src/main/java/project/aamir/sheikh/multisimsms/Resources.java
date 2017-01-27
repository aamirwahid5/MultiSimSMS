package project.aamir.sheikh.multisimsms;

/**
 * Created by Aamir on 27-01-2017.
 */

public class Resources {

    private static String TAG = Model.getContext().getClass().getSimpleName();
    private static int  PADDING = 30;
    private static int SIM0=0;
    private static int SIM1=1;

    public static int getSIM0() {
        return SIM0;
    }

    public static int getSIM1() {
        return SIM1;
    }

    public static String getTAG() {
        return TAG;
    }

    public static int getPADDING() {
        return PADDING;
    }
}
