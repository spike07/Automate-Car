package pl.maslanka.automatecar.connected;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import pl.maslanka.automatecar.R;
import pl.maslanka.automatecar.disconnected.PopupDisconnectedFragment;
import pl.maslanka.automatecar.helpers.CallbackService;
import pl.maslanka.automatecar.helpers.Constants;
import pl.maslanka.automatecar.services.CarConnectedService;
import pl.maslanka.automatecar.services.CarDisconnectedService;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by Artur on 29.11.2016.
 */

public class PopupConnectedFragment extends Fragment
        implements Constants.PREF_KEYS, Constants.DEFAULT_VALUES, Constants.BROADCAST_NOTIFICATIONS,
        Constants.CALLBACK_ACTIONS{

    public static final String TAG = PopupDisconnectedFragment.class.getSimpleName();
    private AlertDialog alertDialog;
    private CountDownTimer counter;
    private int timeoutLeft;
    private Context contextToCallback;

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        try {
            contextToCallback = ((PopupConnectedActivity) getActivity()).getContextToCallback();

            createAlertDialog();
            setAlertDialogParameters();

            alertDialog.show();

            startCounter();

        } catch (NullPointerException ex) {
            Log.e("Error", "Cannot finish popupAction!");
        }
    }

    protected void createAlertDialog() {
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.car_connected) + " - " + PopupConnectedActivity.dialogTimeout + "s")
                .setMessage(getString(R.string.car_detected_desc))
                .setIcon(R.drawable.connected_icon)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        counter.cancel();
                        sendCallbackToPopupConnectedActivity(POPUP_CONNECTED_FINISH_CONTINUE);
                        getActivity().finish();
                    }
                })
                .setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        counter.cancel();
                        sendCallbackToPopupConnectedActivity(POPUP_CONNECTED_FINISH_DISCONTINUE);
                        getActivity().finish();
                    }
                }).create();

    }

    protected void setAlertDialogParameters() throws NullPointerException {
        alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                counter.cancel();
                sendCallbackToPopupConnectedActivity(POPUP_CONNECTED_FINISH_DISCONTINUE);
                getActivity().finish();
            }
        });
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KEYCODE_BACK) {
                    counter.cancel();
                    sendCallbackToPopupConnectedActivity(POPUP_CONNECTED_FINISH_DISCONTINUE);
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }

    protected void startCounter() {
        counter = new CountDownTimer(PopupConnectedActivity.dialogTimeout*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeoutLeft = (int) millisUntilFinished/1000;
                if (CarConnectedService.isCanceled()) {
                    sendCancelCallback();
                    cancel();
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                } else if (isAdded()) {
                    alertDialog.setTitle(getString(R.string.car_connected) + " - " + timeoutLeft + "s");
                }
            }

            @Override
            public void onFinish() throws NullPointerException {
                if (PopupConnectedActivity.actionDialogTimeout && timeoutLeft == 1 && getActivity() != null) {
                    sendCallbackToPopupConnectedActivity(POPUP_CONNECTED_FINISH_CONTINUE);
                } else {
                    sendCallbackToPopupConnectedActivity(POPUP_CONNECTED_FINISH_DISCONTINUE);
                }

                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
        }.start();
    }

    private void sendCallbackToPopupConnectedActivity(String action) {
        if (contextToCallback instanceof CallbackService){
            ((CallbackService) contextToCallback).callback(action,
                    PopupConnectedActivity.carConnectedServiceStartId);
        }
    }

    private void sendCancelCallback() {
        Log.d(TAG, "sendCancelCallback");
        if (contextToCallback instanceof CarDisconnectedService){
            ((CarDisconnectedService) contextToCallback).callback("", START_ID_NO_VALUE);
        }
    }

}
