package utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

@SuppressLint("CommitPrefEdits")
public class AppPreferences {

    private static AppPreferences instance;

    public static AppPreferences init(Context context) {
        if (null == instance) {
            instance = new AppPreferences(context);
        }
        return instance;
    }

    private Context context;
    protected SharedPreferences sharedPreferences;

    public AppPreferences(Context context) {
        super();
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * *      Methods To Set values in Preferences
     **/

    private void setInteger(String key, int val) {
        Editor e = sharedPreferences.edit();
        e.putInt(key, val);
        e.commit();
    }

    public void setString(String key, String val) {
        Editor e = sharedPreferences.edit();
        e.putString(key, val);
        e.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void setBoolean(String key, boolean val) {
        Editor e = sharedPreferences.edit();
        e.putBoolean(key, val);
        e.commit();
    }

    private void setDouble(String key, double val) {
        Editor e = sharedPreferences.edit();
        e.putString(key, String.valueOf(val));
        e.commit();
    }

    private void setLong(String key, long val) {
        Editor e = sharedPreferences.edit();
        e.putLong(key, val);
        e.commit();
    }

    private void setFloat(String key, float val) {
        Editor e = sharedPreferences.edit();
        e.putFloat(key, val);
        e.commit();
    }

    private void remove(String key) {
        Editor e = sharedPreferences.edit();
        e.remove(key);
        e.commit();
    }

    /**
     * *      Public Methods To Get & Set Preferences
     */


}
