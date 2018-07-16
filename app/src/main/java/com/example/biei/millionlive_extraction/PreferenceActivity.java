package com.example.biei.millionlive_extraction;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


/**
 * PreferenceActivityクラス
 * class:PreferenceActivity
 * extends:AppCompatActivity
 */
public class PreferenceActivity extends AppCompatActivity {


    /**
     * onCreate()
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_preference);

        //Toolbarの初期設定
        Toolbar toolbar = findViewById(R.id.preference_toolbar);
        setSupportActionBar(toolbar);

        //Toolbarのナビゲーションボタン初期設定
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            /**
             * ナビゲーションボタン押下時の処理
             * onClick()
             *
             * @param view View
             */
            @Override
            public void onClick(View view) {

                Log.d("PreferenceActivity", "NavigationIcon Selected!");

                Intent intent = new Intent();
                intent.setClass(PreferenceActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        //PreferenceFragmentセット
        getFragmentManager().beginTransaction()
                .replace(R.id.preference_frameLayout, new SettingFragment())
                .commit();
    }


    /**
     * onDestroy()
     */
    protected void onDestroy() {
        super.onDestroy();

        Log.d("PreferenceActivity", "ReturnButton Pushed!");

        Intent intent = new Intent();
        intent.setClass(PreferenceActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



}
