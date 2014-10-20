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

public class RSAFragmentThird extends Fragment{
    private static final String ARG_TITLE = "pager_title";
    private static final String ARG_PAGE = "pager_number";
    private String title;
    private int page;
    private EditText userdataEdit;
    private EditText signedEdit;
    private EditText provedEdit;
    private Button signButton;
    private Button proveButton;

    public static RSAFragmentThird newInstance(int page, String title){
        RSAFragmentThird fragment = new RSAFragmentThird();
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
        View rootview =inflater.inflate(R.layout.fragment_rsa_third,container,false);
        userdataEdit = (EditText)rootview.findViewById(R.id.userdataEdit);
        signedEdit = (EditText)rootview.findViewById(R.id.signedEdit);
        provedEdit = (EditText)rootview.findViewById(R.id.provedEdit);
        signButton = (Button)rootview.findViewById(R.id.signButton);
        proveButton = (Button)rootview.findViewById(R.id.proveButton);
        signButton.setOnClickListener(new myClickListener());
        proveButton.setOnClickListener(new myClickListener());
        return rootview;
    }

    class myClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == signButton){
                String privateKey = RSAFragmentFirst.getPrivateKey();
                if(privateKey.equals("")){
                    Toast.makeText(getActivity(),"请先初始化公钥和私钥",Toast.LENGTH_SHORT).show();
                    return;
                }
                String userStr = userdataEdit.getText().toString();
                if (userStr.equals("")){
                    Toast.makeText(getActivity(),"请输入待签名数据",Toast.LENGTH_SHORT).show();
                    return;
                }
                byte[] usrData = userStr.getBytes();
                try {
                    String sign = rsa.sign(usrData, privateKey);
                    signedEdit.setText(sign);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else if (view == proveButton){
                String publicKey = RSAFragmentFirst.getPublicKey();
                if (publicKey.equals("")){
                    Toast.makeText(getActivity(),"请先初始化公钥和私钥",Toast.LENGTH_SHORT).show();
                    return;
                }
                String sign = signedEdit.getText().toString();
                if (sign.equals("")){
                    Toast.makeText(getActivity(),"请先进行私钥签名",Toast.LENGTH_SHORT).show();
                    return;
                }
                String usrStr = userdataEdit.getText().toString();
                if(usrStr.equals("")){
                    Toast.makeText(getActivity(),"请输入待签名数据",Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    boolean status = rsa.verify(usrStr.getBytes(),publicKey,sign);
                    if (status){
                        provedEdit.setText("验证签名成功！");
                    }else {
                        provedEdit.setText("验证签名失败！原始数据被篡改或者签名不正确。");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }
    }
}
