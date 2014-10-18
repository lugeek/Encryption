package com.lugeek.encryption;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lugeek.algorithm.hmac;

import java.math.BigInteger;

public class HmacFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_HMAC_METHOD = "hmac_method";
    private String HmacMthod;
    private EditText mingEdit;
    private EditText miyaoEdit;
    private  EditText miEdit;
    private Button jiamiButton;
    public HmacFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        HmacMthod = getArguments().getString(ARG_HMAC_METHOD);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_hmac,container,false);
        mingEdit = (EditText)rootview.findViewById(R.id.mingEdit);
        miyaoEdit = (EditText)rootview.findViewById(R.id.miyaoEdit);
        miEdit = (EditText)rootview.findViewById(R.id.miEdit);
        jiamiButton = (Button)rootview.findViewById(R.id.encryptButton);
        jiamiButton.setOnClickListener(new myClickListener());
        return rootview;
    }

    class myClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (view == jiamiButton){
                String inputStr = mingEdit.getText().toString();
                if(inputStr.equals("")){
                    Toast.makeText(getActivity(),"待加密数据不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                String inputKey = miyaoEdit.getText().toString();
                if(inputKey.equals("")){
                    Toast.makeText(getActivity(),"密钥不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                byte[] inputData = inputStr.getBytes();
                byte[] outputData = new byte[0];
                try {
                    outputData = hmac.encryptHMAC(inputData,inputKey,HmacMthod);
                }catch (Exception e){
                    e.printStackTrace();
                }
                BigInteger hmacData = new BigInteger(1, outputData);
                miEdit.setText(hmacData.toString(16));
            }
        }
    }

    public static HmacFragment newInstance(int sectionNumber, String hmacMthod){
        HmacFragment fragment = new HmacFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER,sectionNumber);
        args.putString(ARG_HMAC_METHOD,hmacMthod);
        fragment.setArguments(args);
        return fragment;
    }
}
