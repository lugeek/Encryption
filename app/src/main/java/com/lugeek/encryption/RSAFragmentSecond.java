package com.lugeek.encryption;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lugeek.algorithm.rsa;

import java.math.BigInteger;

public class RSAFragmentSecond extends Fragment{
    private static final String ARG_TITLE = "pager_title";
    private static final String ARG_PAGE = "pager_number";
    private String title;
    private int page;
    private EditText userdataEdit;
    private EditText encryptedEdit;
    private EditText decryptedEdit;
    private Button encryptButton;
    private Button decryptButton;
    byte[] encodeData = null;

    public static RSAFragmentSecond newInstance(int page, String title){
        RSAFragmentSecond fragment = new RSAFragmentSecond();
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
        View rootview =inflater.inflate(R.layout.fragment_rsa_second,container,false);
        userdataEdit = (EditText)rootview.findViewById(R.id.userdataEdit);
        encryptedEdit = (EditText)rootview.findViewById(R.id.encryptedEdit);
        decryptedEdit = (EditText)rootview.findViewById(R.id.decryptedEdit);
        encryptButton = (Button)rootview.findViewById(R.id.encryptButton);
        decryptButton = (Button)rootview.findViewById(R.id.decryptButton);
        encryptButton.setOnClickListener(new myClickListener());
        decryptButton.setOnClickListener(new myClickListener());
        return rootview;
    }

    class myClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == encryptButton){
                String publicKey = RSAFragmentFirst.getPublicKey();
                if(publicKey.equals("")){
                    Toast.makeText(getActivity(),"请先初始化公钥和私钥",Toast.LENGTH_SHORT).show();
                    return;
                }
                String userStr = userdataEdit.getText().toString();
                if(userStr.equals("")){
                    Toast.makeText(getActivity(),"请先输入待加密数据",Toast.LENGTH_SHORT).show();
                    return;
                }
                byte[] userData = userStr.getBytes();
                try {
                    encodeData = rsa.encryptByPublicKey(userData, publicKey);
                    String encodeStr = new BigInteger(1, encodeData).toString(16);
                    encryptedEdit.setText(encodeStr);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else if (view == decryptButton){
                String privateKey = RSAFragmentFirst.getPrivateKey();
                if (privateKey.equals("")){
                    Toast.makeText(getActivity(),"请先初始化公钥和私钥",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(encodeData == null){
                    Toast.makeText(getActivity(),"请先进行公钥加密",Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    byte[] decodeData = rsa.decryptByPrivateKey(encodeData,privateKey);
                    String decodeStr = new String(decodeData);
                    decryptedEdit.setText(decodeStr);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
}
