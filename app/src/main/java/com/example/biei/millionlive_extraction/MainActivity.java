package com.example.biei.millionlive_extraction;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


/**
 * MainActivityクラス
 * class:MainActivity
 * extends:AppCompatActivity
 * implements:NavigationView.OnNavigationItemSelectedListener
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /**
     * onCreate()
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbarの初期設定
        Toolbar toolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(toolbar);

        //DrawerLayoutの初期設定
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationViewの初期設定
        NavigationView navigationView = findViewById(R.id.drawer_NavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        showTopFragment();
    }


    /**
     * ドロワーメニューが選択された場合の処理
     * onNavigationItemSelected()
     *
     * @param item MenuItem
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            //"ボイス"項目を押下した場合の処理
            case R.id.menu_drawerVoice:

                Log.d("MainActivity", "Voice Selected!");

                break;

            //"イメージ"項目を押下した場合の処理
            case R.id.menu_drawerImage:

                Log.d("MainActivity", "Image Selected!");

                break;

            //"セットリスト"項目を押下した場合の処理
            case R.id.menu_drawerSetList:

                Log.d("MainActivity", "SetList Selected!");

                break;

            //"コール集"項目を押下した場合の処理
            case R.id.menu_drawerCallCollection:

                Log.d("MainActivity", "CallCollection Selected!");

                break;

            //"お知らせ"項目を押下した場合の処理
            case R.id.menu_drawerNotice:

                Log.d("MainActivity", "Notice Selected!");

                String noticeTitle = "お知らせ";
                String noticeMessage = "まだテスト中";
                showAlertDialog(noticeTitle, noticeMessage);

                break;

            //"このアプリについて"項目を押下した場合の処理
            case R.id.menu_drawerSummary:

                Log.d("MainActivity", "Summary Selected!");

                String summaryTitle = "概要";
                String summaryMessage = "作ってみた";
                showAlertDialog(summaryTitle, summaryMessage);

                break;

            //"設定"項目を押下した場合の処理
            case R.id.menu_drawerSetting:

                Log.d("MainActivity", "Setting Selected!");

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PreferenceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                break;
        }

        //ドロワーメニューを閉じる
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    /**
     * Topフラグメントを表示する処理
     * showTopFragment()
     */
    private void showTopFragment() {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frameLayout, new TopFragment());
        transaction.commit();
    }


    /**
     * 表示するフラグメントの切り替え処理
     * changeFragment()
     * (android.support.v4.app.fragment郡)
     *
     * @param fragment Fragment
     */
    private void changeFragment(Fragment fragment) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frameLayout, fragment);
        transaction.commit();
    }


    /**
     * アラートダイアログ表示処理
     * showAlertDialog()
     *
     * @param title String
     * @param message String
     */
    private void showAlertDialog(String title, String message) {

        //AlertDialogの初期設定
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.setCancelable(false);

        //AlertDialogを構築
        builder.create();

        //AlertDialogを表示
        builder.show();
    }
}
