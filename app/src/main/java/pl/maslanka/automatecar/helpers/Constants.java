package pl.maslanka.automatecar.helpers;

/**
 * Created by Artur on 08.11.2016.
 */

public class Constants {

    public static final String INTENT_EXTRA_RESULT_CODE = "intent_extra_result_code";

    public interface FILE_NAMES {
        String PATH = "appList";
        String FILE_NAME = "appList.obj";
    }

     public interface APP_CREATOR_FRAGMENT {
        String TAG_APP_CREATOR_FRAGMENT = "app_creator_fragment";
    }

    public interface SELECT_APPS_FRAGMENT {
        String TAG_SELECT_APPS_FRAGMENT = "select_apps_fragment";
    }

    public interface POPUP_CONNECTED_FRAGMENT {
        String TAG_POPUP_CONNECTED_FRAGMENT = "popup_connected_fragment";
    }

    public interface DEFAULT_VALUES {
        boolean FIRST_RUN_DEFAULT_VALUE = true;
        int DIALOG_DURATION_MAX_VALUE = 600;
        int DIALOG_DURATION_MIN_VALUE = 1;
        int SLEEP_TIMES_MIN_VALUE = 1;
        int SLEEP_TIMES_MAX_VALUE = 20;
        boolean DISABLE_LOCK_SCREEN_DEFAULT_VALUE = false;
        boolean FORCE_AUTO_ROTATION_DEFAULT_VALUE = false;
        boolean CHECK_IF_IN_POCKET_DEFAULT_VALUE = false;
        boolean CHECK_WIRELESS_POWER_SUPPLY_DEFAULT_VALUE = false;
        boolean CHECK_NFC_TAG_DEFAULT_VALUE = false;
        boolean SHOW_CANCEL_DIALOG_DEFAULT_VALUE = true;
        int DIALOG_TIMEOUT_DEFAULT_VALUE = 10;
        boolean ACTION_DIALOG_TIMEOUT_DEFAULT_VALUE = true;
        int SLEEP_TIMES_DEFAULT_VALUE = 8;
        boolean MAX_VOLUME_DEFAULT_VALUE = true;
        boolean PLAY_MUSIC_DEFAULT_VALUE = false;
        boolean SHOW_NAVI_DEFAULT_VALUE =  true;
    }

    public interface PREF_KEYS {
        String KEY_FIRST_RUN = "first_run";
        String KEY_SELECT_BLUETOOTH_DEVICES = "select_bluetooth_devices";
        String KEY_DISABLE_LOCK_SCREEN = "disable_lock_screen";
        String KEY_FORCE_AUTO_ROTATION = "force_auto_rotation";
        String KEY_ROTATION_EXCLUDED_APPS = "rotation_excluded_apps";
        String KEY_CHECK_IF_IN_POCKET = "check_if_in_pocket";
        String KEY_CHECK_WIRELESS_POWER_SUPPLY = "check_wireless_power_supply";
        String KEY_CHECK_NFC_TAG = "check_nfc_tag";
        String KEY_SHOW_CANCEL_DIALOG = "show_cancel_dialog";
        String KEY_DIALOG_TIMEOUT = "dialog_timeout";
        String KEY_ACTION_DIALOG_TIMEOUT = "action_dialog_timeout";
        String KEY_APPS_TO_LAUNCH = "apps_to_launch";
        String KEY_SLEEP_TIMES = "sleep_times";
        String KEY_MAX_VOLUME = "max_volume";
        String KEY_PLAY_MUSIC = "play_music";
        String KEY_CHOOSE_MUSIC_PLAYER = "choose_music_player";
        String KEY_SHOW_NAVI = "show_navi";
    }


    public interface ACTION {
       String MAIN_ACTION = "com.maslanka.automatecar.action.main";
       String START_FOREGROUND_ACTION = "com.maslanka.automatecar.startforeground";
       String STOP_FOREGROUND_ACTION = "com.maslanka.automatecar..stopforeground";
    }

    public interface NOTIFICATION_ID {
        int FOREGROUND_SERVICE = 101;
    }

    public interface BROADCAST_NOTIFICATIONS {
        String FORCE_ROTATION_ACTION = "com.maslanka.automatecar.force.rotation.action";
        String POPUP_ACTION = "com.maslanka.automatecar.popup.action";
        String CONTINUE_ACTION = "com.maslanka.automatecar.continue.action";
        String DISCONTINUE_ACTION = "com.maslanka.automatecar.discontinue.action";
        String PLAY_MUSIC_ACTION = "com.maslanka.automatecar.play.music.action";
        String DISABLE_LOCK_SCREEN_ACTION = "com.maslanka.automatecar.disable.lock.screen.action";
    }
}