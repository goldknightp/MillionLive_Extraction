package com.example.biei.millionlive_extraction;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class LiveInfoFragment extends Fragment {

    /**
     * フィールド変数
     */
    //コンテキスト
    private Context context;


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
    }


    /**
     * onCreateView()
     *
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setlist, container, false);

        ListView liveInfoList = view.findViewById(R.id.fragment_LiveInfoListView);

        return view;
    }
}
