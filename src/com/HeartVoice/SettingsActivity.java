package com.HeartVoice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import com.umeng.analytics.MobclickAgent;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String KEY_DEFAULT_TEXT = "default_text_preference";
    public static final String KEY_DEFAULT_VOICE = "default_voice_preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.layout.settings);
        Preference default_text_preference = findPreference(KEY_DEFAULT_TEXT);
        default_text_preference.setSummary(getPreferenceManager().getSharedPreferences().getString(KEY_DEFAULT_TEXT, getString(R.string.default_text_value)));
        ListPreference listPref = (ListPreference) findPreference(KEY_DEFAULT_VOICE);
        if(listPref.getEntry() != null){
            listPref.setSummary(listPref.getEntry());
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);
        if (pref instanceof EditTextPreference) {
            EditTextPreference editPref = (EditTextPreference) pref;
            pref.setSummary(editPref.getText());
        } else if (pref instanceof ListPreference) {
            ListPreference listPref = (ListPreference) pref;
            pref.setSummary(listPref.getEntry());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        MobclickAgent.onPause(this);
    }
}
