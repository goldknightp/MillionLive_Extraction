package com.example.biei.millionlive_extraction;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;


/**
 * MainActivityクラス
 * class:TopFragment
 * extends:android.app.Fragment
 */
public class TopFragment extends Fragment implements View.OnClickListener {


    /**
     * フィールド変数
     */
    //コンテキスト
    private Context context;
    //部品のインスタンス変数
    private ImageView topLeftImage;
    private ImageView topCenterImage;
    private ImageView topRightImage;
    private ImageView topBackGroundImage;
    private LinearLayout layout;
    private Button showIdolOfNumberSelector;
    private Button backGroundSelector;
    private Button changeLeftToCenter;
    private Button changeLeftToRight;
    private Button changeCenterToRight;

    //SharedPreferencesのインスタンス変数
    private SharedPreferences preferences;

    //各種ファイルパス
    private String LEFT_FILE_PATH;
    private String CENTER_FILE_PATH;
    private String RIGHT_FILE_PATH;
    private String BG_FILE_PATH;


    /**
     * onAttach()
     *
     * @param context Context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    /**
     * onCreate()
     *
     * @param savedInstanceState Bundle
     */
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SharedPreferencesのインスタンス化
        preferences = PreferenceManager.getDefaultSharedPreferences(context);

        //表示アイドル(左)関連
        LEFT_FILE_PATH = preferences.getString(
                getString(R.string.string_preferenceListLeftKey),
                getString(R.string.string_listDefaultLeftValue));

        //表示アイドル(中心)関連
        CENTER_FILE_PATH = preferences.getString(
                getString(R.string.string_preferenceListCenterKey),
                getString(R.string.string_listDefaultCenterValue));

        //表示アイドル(右)関連
        RIGHT_FILE_PATH = preferences.getString(
                getString(R.string.string_preferenceListRightKey),
                getString(R.string.string_listDefaultRightValue));

        //背景関連
        BG_FILE_PATH = preferences.getString(
                getString(R.string.string_preferenceBGListKey),
                getString(R.string.string_listDefaultBGValue));
    }


    /**
     * onCreateView()
     *
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top, container, false);

        // コンテンツ部分のLayoutを取ってくる
        layout = view.findViewById(R.id.top_allChargeIdolLayout);

        //表示アイドル(左)関連
        topLeftImage = view.findViewById(R.id.top_chargeIdolLeft);

        //表示アイドル(中心)関連
        topCenterImage = view.findViewById(R.id.top_chargeIdolCenter);

        //表示アイドル(右)関連
        topRightImage = view.findViewById(R.id.top_chargeIdolRight);

        //表示人数切り替え
        showIdolOfNumberSelector = view.findViewById(R.id.top_showIdolOfNumberSelector);
        showIdolOfNumberSelector.setOnClickListener(this);

        //背景関連
        topBackGroundImage = view.findViewById(R.id.top_backGround);
        backGroundSelector = view.findViewById(R.id.top_backGroundSelector);
        backGroundSelector.setOnClickListener(this);

        //表示切り替えボタン
        changeLeftToCenter = view.findViewById(R.id.top_changeLeftToCenter);
        changeLeftToCenter.setOnClickListener(this);
        changeLeftToRight = view.findViewById(R.id.top_changeLeftToRight);
        changeLeftToRight.setOnClickListener(this);
        changeCenterToRight = view.findViewById(R.id.top_changeCenterToRight);
        changeCenterToRight.setOnClickListener(this);

        switch (preferences.getString(
                getString(R.string.string_showIdolOfNumber),
                getString(R.string.string_showIdolDefault))) {

            case "ONE":

                // 内容を全部消す
                layout.removeAllViews();

                // test_sub.xmlに変更する
                inflater.inflate(R.layout.one_charge_idol_layout, layout);

                //表示切り替えボタンの有効設定
                changeLeftToCenter.setEnabled(false);
                changeLeftToRight.setEnabled(false);
                changeCenterToRight.setEnabled(false);

                if( !setImage(topCenterImage, CENTER_FILE_PATH)
                        || !setImage(topBackGroundImage, BG_FILE_PATH)) {

                    return null;
                }

                break;

            case "TWO":

                // 内容を全部消す
                layout.removeAllViews();

                // test_sub.xmlに変更する
                inflater.inflate(R.layout.two_charge_idol_layout, layout);

                //表示切り替えボタンの有効設定
                changeLeftToCenter.setEnabled(false);
                changeLeftToRight.setEnabled(true);
                changeCenterToRight.setEnabled(false);

                if( !setImage(topLeftImage, LEFT_FILE_PATH)
                        || !setImage(topRightImage, RIGHT_FILE_PATH)
                        || !setImage(topBackGroundImage, BG_FILE_PATH)) {

                    return null;
                }

                break;

            case "THREE":

                // 内容を全部消す
                layout.removeAllViews();

                // test_sub.xmlに変更する
                inflater.inflate(R.layout.three_charge_idol_layout, layout);

                //表示切り替えボタンの有効設定
                changeLeftToCenter.setEnabled(true);
                changeLeftToRight.setEnabled(true);
                changeCenterToRight.setEnabled(true);

                if( !setImage(topLeftImage, LEFT_FILE_PATH)
                    || !setImage(topCenterImage, CENTER_FILE_PATH)
                    || !setImage(topRightImage, RIGHT_FILE_PATH)
                    || !setImage(topBackGroundImage, BG_FILE_PATH)) {

                return null;
            }
                break;
        }

        return view;
    }


    /**
     * 対応した部品が押下されたときには発動
     * onClick()
     *
     * @param view View
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.top_changeLeftToCenter:

                Log.d("TopFragment", "ChangeLeftToCenter_ButtonClick!");

                reloadFragment();

                break;

            case R.id.top_changeLeftToRight:

                Log.d("TopFragment", "ChangeLeftToRight_ButtonClick!");

                reloadFragment();

                break;

            case R.id.top_changeCenterToRight:

                Log.d("TopFragment", "ChangeCenterToRight_ButtonClick!");

                reloadFragment();

                break;

            case R.id.top_showIdolOfNumberSelector:

                Log.d("TopFragment", "ShowIdolOfNumberSelector_ButtonClick!");

                showIdolOfNumberSelector();

                reloadFragment();

                break;

            case R.id.top_backGroundSelector:

                Log.d("TopFragment", "BackGroundSelector_ButtonClick!");

                backGroundSelector();

                reloadFragment();

                break;
        }
    }


    /**
     * ImageViewに出力する画像をセット
     * setImage()
     *
     * @param imageView ImageView
     * @return boolean
     */
    private boolean setImage(ImageView imageView, String filePath) {

        final String DIRECTORY_PATH = "top_image/";

        try {

            InputStream inputStream
                    = getResources().getAssets().open(DIRECTORY_PATH + filePath);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);

            return true;

        } catch (IOException e) {

            Log.d("TopFragment", "Error setImageBitmap!");

            return false;
        }
    }


    /**
     * 表示人数切り替えダイアログ表示
     * showIdolOfNumberSelector()
     */
    private void showIdolOfNumberSelector() {

        final String[] items = {"1人", "2人", "3人"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("表示人数を選択");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            /**
             * onClick
             *
             * @param dialogInterface DialogInterface
             * @param which int
             */
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                SharedPreferences.Editor editor = preferences.edit();

                switch (which) {
                    case 0:
                        Log.d("TopFragment", "oneIdol selected!");
                        editor.putString(
                                getString(R.string.string_showIdolOfNumber),
                                getString(R.string.string_showIdolOne));
                        break;

                    case 1:
                        Log.d("TopFragment", "twoIdol selected!");
                        editor.putString(
                                getString(R.string.string_showIdolOfNumber),
                                getString(R.string.string_showIdolTwo));
                        break;

                    case 2:
                        Log.d("TopFragment", "threeIdol selected!");
                        editor.putString(
                                getString(R.string.string_showIdolOfNumber),
                                getString(R.string.string_showIdolThree));
                        break;
                }
                editor.apply();
                showIdolOfNumberSelector.setText(items[which]);
            }
        });
        builder.create();
        builder.show();
    }



    private void backGroundSelector() {

        final String[] items = getResources().getStringArray(R.array.stringArray_backGroundList);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("表示背景を選択");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            /**
             * onClick
             *
             * @param dialogInterface DialogInterface
             * @param which int
             */
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                SharedPreferences.Editor editor = preferences.edit();

                switch (which) {
                    case 0:
                        Log.d("TopFragment", "normalBG selected!");
                        editor.putString(
                                getString(R.string.string_preferenceBGListKey),
                                getString(R.string.string_BG_normal));
                        break;

                    case 1:
                        Log.d("TopFragment", "anniversaryBG selected!");
                        editor.putString(
                                getString(R.string.string_preferenceBGListKey),
                                getString(R.string.string_BG_anniversary));
                        break;

                    case 2:
                        Log.d("TopFragment", "shipBG selected!");
                        editor.putString(
                                getString(R.string.string_preferenceBGListKey),
                                getString(R.string.string_BG_ship));
                        break;

                    case 3:
                        Log.d("TopFragment", "shrineBG selected!");
                        editor.putString(
                                getString(R.string.string_preferenceBGListKey),
                                getString(R.string.string_BG_shrine));
                        break;
                }
                editor.apply();
                backGroundSelector.setText(items[which]);
            }
        });
        builder.create();
        builder.show();
    }


    /**
     * TopFragmentをリロード
     * reloadFragment()
     */
    private void reloadFragment() {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frameLayout, this);
        transaction.commit();
    }
}
