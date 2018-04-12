package quanly_anything_you_want.manage.com.quanlyanything.interactor.prefer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import quanly_anything_you_want.manage.com.quanlyanything.MainApplication;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.ApiManager;
import quanly_anything_you_want.manage.com.quanlyanything.model.User;

import static android.content.Context.MODE_PRIVATE;

public class PreferManager {
    private SharedPreferences mPreferences;

    @SuppressLint("CommitPrefEdits")
    private PreferManager() {
        mPreferences = MainApplication.context.getSharedPreferences("MY_DATA_PREFER", MODE_PRIVATE);
    }

    public synchronized static PreferManager getInstance() {
        return INSTANCE;
    }

    private static final PreferManager INSTANCE = new PreferManager();

    private static final String KEY_TOKEN = "TOKEN";

    public String getToken() {
        return mPreferences.getString(KEY_TOKEN, null);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    private static final String KEY_USER = "KEY_USER";

    public void setUser(User user) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_USER, new Gson().toJson(user));
        editor.apply();
    }

    public User getUser() {
        User user = null;
        try {
            user = new Gson().fromJson(mPreferences.getString(KEY_USER, new Gson().toJson(new User())), User.class);
        } catch (Exception ignored) {
        }
        return user;
    }

    // logout
    public void resetUser() {
        setToken(null);
        setUser(null);
    }


}
