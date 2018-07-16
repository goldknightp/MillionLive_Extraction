package com.example.biei.millionlive_extraction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;


/**
 * SettingFragmentクラス
 * class:SettingFragment
 * extends:PreferenceFragment
 */
public class SettingFragment extends PreferenceFragment {


    /**
     * フィールド変数
     */
    //OnSharedPreferenceChangeListenerのコールバックに使用
    private SharedPreferences.OnSharedPreferenceChangeListener listener
            = new SharedPreferences.OnSharedPreferenceChangeListener() {

        /**
         * 設定値が変更された場合に呼び出しされる処理
         * onSharedPreferenceChanged()
         *
         * @param sharedPreferences SharedPreferences
         * @param key String
         */
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            ListPreference leftListPreference
                    = (ListPreference)findPreference(getString(R.string.string_preferenceListLeftKey));
            leftListPreference.setSummary("選択アイドル：" + leftListPreference.getEntry());

            ListPreference centerListPreference
                    = (ListPreference)findPreference(getString(R.string.string_preferenceListCenterKey));
            centerListPreference.setSummary("選択アイドル：" + centerListPreference.getEntry());

            ListPreference rightListPreference
                    = (ListPreference)findPreference(getString(R.string.string_preferenceListRightKey));
            rightListPreference.setSummary("選択アイドル：" + rightListPreference.getEntry());

            ListPreference showIdolListPreference
                    = (ListPreference)findPreference(getString(R.string.string_showIdolOfNumber));
            showIdolListPreference.setSummary("表示人数：" + showIdolListPreference.getEntry());

            ListPreference backGroundListPreference
                    = (ListPreference)findPreference(getString(R.string.string_preferenceBGListKey));
            backGroundListPreference.setSummary("背景：" + backGroundListPreference.getEntry());
        }
    };


    /**
     * onCreate()
     *
     * @param savedInstanceState Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preference);
    }


    /**
     * onActivityCreated()
     *
     * @param savedInstanceState Bundle
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }


    /**
     * View部品の初期設定を行う処理
     * initView()
     */
    private void initView() {

        ListPreference leftListPreference
                = (ListPreference)findPreference(getString(R.string.string_preferenceListLeftKey));
        leftListPreference.setSummary("選択アイドル：" + leftListPreference.getEntry());

        ListPreference centerListPreference
                = (ListPreference)findPreference(getString(R.string.string_preferenceListCenterKey));
        centerListPreference.setSummary("選択アイドル：" + centerListPreference.getEntry());

        ListPreference rightListPreference
                = (ListPreference)findPreference(getString(R.string.string_preferenceListRightKey));
        rightListPreference.setSummary("選択アイドル：" + rightListPreference.getEntry());

        ListPreference showIdolListPreference
                = (ListPreference)findPreference(getString(R.string.string_showIdolOfNumber));
        showIdolListPreference.setSummary("表示人数：" + showIdolListPreference.getEntry());

        ListPreference backGroundListPreference
                = (ListPreference)findPreference(getString(R.string.string_preferenceBGListKey));
        backGroundListPreference.setSummary("背景：" + backGroundListPreference.getEntry());
    }


    /**
     * onResume()
     */
    @Override
    public void onResume() {
        super.onResume();

        //OnSharedPreferenceChangeListener登録処理
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(listener);
    }


    /**
     * onPause()
     */
    @Override
    public void onPause() {
        super.onPause();

        //OnSharedPreferenceChangeListener解除処理
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(listener);
    }

}
