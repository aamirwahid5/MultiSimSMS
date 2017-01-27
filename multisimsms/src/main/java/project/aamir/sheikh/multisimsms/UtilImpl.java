package project.aamir.sheikh.multisimsms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Aamir on 26-01-2017.
 */

class UtilImpl extends Utils {
    @Override
    public int getNoOfSims() {
        int count = -1;
        TelephonyManager manager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Call some material design APIs here
            manager = (TelephonyManager) Model.getContext().getSystemService(Model.getContext().TELEPHONY_SERVICE);
//            Log.e("AAMIR", "Single or Dual Sim " + manager.getPhoneCount());
////            Log.e("AAMIR", "Default device ID " + manager.getDeviceId());
//            Log.e("AAMIR", "Single 1 " + manager.getDeviceId(0));
//            Log.e("AAMIR", "Single 2 " + manager.getDeviceId(1));
            count = manager.getPhoneCount();

        }
        return count;
    }

    @Override
    public int getSubscriptionId(int index) {
        android.telephony.SubscriptionManager manager = android.telephony.SubscriptionManager.from(Model.getContext());

        SubscriptionInfo SubId = manager.getActiveSubscriptionInfoForSimSlotIndex(index);
        if (SubId != null) {
            return SubId.getSubscriptionId();
        } else
            return -1;
    }

    @Override
    public CharSequence getCarrierName(int index) {
        CharSequence value = "false";
        android.telephony.SubscriptionManager manager = android.telephony.SubscriptionManager.from(Model.getContext());
        SubscriptionInfo CarrierInfo = manager.getActiveSubscriptionInfoForSimSlotIndex(index);
        if (CarrierInfo != null) {
            return CarrierInfo.getCarrierName();
        } else
            return value;
    }

    @Override
    public void sendSMS(PendingIntent sentPI) {
        {

            SmsManager sms = SmsManager.getDefault();

            sms.sendTextMessage(Model.getMobNumber(), null, Model.getMessage(), sentPI, null);


        }
    }

    @Override
    public void sendSMS(PendingIntent sentPI, int id) {
        {
            try {
                SmsManager sms = SmsManager.getSmsManagerForSubscriptionId(id);

                sms.sendTextMessage(Model.getMobNumber(), null, Model.getMessage(), sentPI, null);
            } catch (IllegalArgumentException e) {
                Model.getListener().onInvalidDestinationAddress();
                Log.e(Resources.getTAG(), e.getMessage());
            }

        }
    }

    @Override
    public void registerReceiver(String SENT, final MultiSimSMS.setOnSMSListener listener) {
        {
            //---when the SMS has been sent---
            Model.getContext().registerReceiver(new BroadcastReceiver() {

                @Override
                public void onReceive(Context arg0, Intent arg1) {
                    switch (getResultCode()) {
                        case Activity.RESULT_OK:

                            if (listener != null) {

                                listener.onSmsSent();

                            }

                            break;
                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            if (listener != null) {
                                listener.onGenericFailure();
                            }

                            break;
                        case SmsManager.RESULT_ERROR_NO_SERVICE:
                            if (listener != null) {
                                listener.onNoService();
                            }
                            break;
                        case SmsManager.RESULT_ERROR_NULL_PDU:
                            if (listener != null) {
                                listener.onNullPdu();
                            }
                            break;
                        case SmsManager.RESULT_ERROR_RADIO_OFF:
                            if (listener != null) {
                                listener.onRadioOff();
                            }

                            break;
                    }
                }
            }, new IntentFilter(SENT));

        }
    }

    @Override
    public void showAlertDialog(final PendingIntent sentPI) {
        {
            Button select;
            LinearLayout mLinearLayout = new LinearLayout(Model.getContext());
            mLinearLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mLinearLayout.setLayoutParams(mParams);

            mLinearLayout.setOrientation(LinearLayout.VERTICAL);

            final RadioGroup mRadioGroup = new RadioGroup(Model.getContext());
            mRadioGroup.setPadding(Resources.getPADDING(), Resources.getPADDING(), Resources.getPADDING(), Resources.getPADDING());
            mRadioGroup.setOrientation(RadioGroup.VERTICAL);

            RadioButton mRadioButton = new RadioButton(Model.getContext());
            mRadioButton.setPadding(Resources.getPADDING(), Resources.getPADDING(), Resources.getPADDING(), Resources.getPADDING());
            mRadioButton.setId(R.id.b1);
            mRadioButton.setText(new UtilImpl().getCarrierName(Resources.getSIM0()).toString());
            mRadioGroup.addView(mRadioButton);

            RadioButton mRadioButton2 = new RadioButton(Model.getContext());
            mRadioButton2.setPadding(Resources.getPADDING(), Resources.getPADDING(), Resources.getPADDING(), Resources.getPADDING());
            mRadioButton2.setId(R.id.b2);
            mRadioButton2.setText(new UtilImpl().getCarrierName(Resources.getSIM1()).toString());
            mRadioGroup.addView(mRadioButton2);

            select = new Button(Model.getContext());
            select.setText("Select");

            //Add radiogroup to linear layout

            mLinearLayout.addView(mRadioGroup, 0);
            mLinearLayout.addView(select, 1);

            //Create AlertDialog Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(Model.getContext());

            //Give the Dialog a Title
            builder.setTitle("Select Sim");

            //Set the Dynamically created layout as the Dialogs view
            builder.setView(mLinearLayout);

            //Show the custom AlertDialog
            final AlertDialog alert = builder.create();

            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (mRadioGroup.getCheckedRadioButtonId() == R.id.b1) {

                        sendSMS(sentPI, new UtilImpl().getSubscriptionId(0));
                        alert.dismiss();

                    } else {
                        sendSMS(sentPI, new UtilImpl().getSubscriptionId(1));
                        alert.dismiss();
                    }


                }
            });
            alert.show();

        }
    }

    @Override
    public void checkCondition(PendingIntent sentPI) {
        {
            if (new UtilImpl().getNoOfSims() > 1) {
                //More than 1 sim is present
                if (!new UtilImpl().getCarrierName(0).toString().equalsIgnoreCase("No Service") &&
                        new UtilImpl().getCarrierName(1).toString().equalsIgnoreCase("No Service")) {
                    //if vodafone sim has service and jio Doesn't have

                    new UtilImpl().sendSMS(sentPI, new UtilImpl().getSubscriptionId(0));

                    Model.getListener().ServiceAvailOnSingleSim(new UtilImpl().getCarrierName(0), Resources.getSIM0());

                } else if (!new UtilImpl().getCarrierName(1).toString().equalsIgnoreCase("No Service") &&
                        new UtilImpl().getCarrierName(0).toString().equalsIgnoreCase("No Service")) {
                    //if jio sim has service and Voda doesn't have

                    new UtilImpl().sendSMS(sentPI, new UtilImpl().getSubscriptionId(1));
                    Model.getListener().ServiceAvailOnSingleSim(new UtilImpl().getCarrierName(1), Resources.getSIM1());

                } else if ((!new UtilImpl().getCarrierName(1).toString().equalsIgnoreCase("No Service") &&
                        !new UtilImpl().getCarrierName(0).toString().equalsIgnoreCase("No Service"))) {

                    //both sims are in service show alert Dialog

                    new UtilImpl().showAlertDialog(sentPI);
                }

            }
            else if (new UtilImpl().getNoOfSims() == 1 || new UtilImpl().getNoOfSims() == -1) {

                new UtilImpl().sendSMS(sentPI);//only 1 sim is present need to use Default method for SMS Manager
                Model.getListener().SingleSimOrLessBuild();
            }

        }
    }

}
