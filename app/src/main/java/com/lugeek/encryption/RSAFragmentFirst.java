package com.lugeek.encryption;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.lugeek.algorithm.rsa;

import java.util.Map;

public class RSAFragmentFirst extends Fragment {
    private static final String ARG_TITLE = "pager_title";
    private static final String ARG_PAGE = "pager_number";
    private String title;
    private int page;
    private EditText publickeyEdit;
    private EditText privatekeyEdit;
    private Button initkeyButton;
    private static String publicKey = "";
    private static String privateKey = "";

    public static RSAFragmentFirst newInstance(int page, String title){
        RSAFragmentFirst fragment = new RSAFragmentFirst();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARG_PAGE);
        title = getArguments().getString(ARG_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.fragment_rsa_first,container,false);
        publickeyEdit = (EditText)rootview.findViewById(R.id.publickeyEdit);
        privatekeyEdit = (EditText)rootview.findViewById(R.id.privatekeyEdit);
        initkeyButton = (Button)rootview.findViewById(R.id.initkeyButton);
        initkeyButton.setOnClickListener(new myClickListener());
        return rootview;
    }

    class myClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == initkeyButton){
                try {
                    Map<String, Object> keyMap = rsa.initKey();
                    publicKey = rsa.getPublicKey(keyMap);
                    privateKey = rsa.getPrivateKey(keyMap);
                    publickeyEdit.setText(publicKey);
                    privatekeyEdit.setText(privateKey);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    public static String getPublicKey(){
        return publicKey;
    }

    public static String getPrivateKey(){
        return privateKey;
    }
}
