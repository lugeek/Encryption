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

import com.lugeek.algorithm.sha;

import java.math.BigInteger;

public class SHAFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String SHA_NUMBER = "sha_number";
    private EditText mingEdit;
    private Button encryptButton;
    private EditText miEdit;
    private String shaNumber;

    public SHAFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        shaNumber = getArguments().getString(SHA_NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_md5,container,false);
        mingEdit = (EditText)rootview.findViewById(R.id.mingEdit);
        miEdit = (EditText)rootview.findViewById(R.id.miEdit);
        encryptButton = (Button)rootview.findViewById(R.id.encryptButton);
        encryptButton.setOnClickListener(new myClickListener());
        return rootview;
    }
    class myClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            if(view == encryptButton){
                String inputStr = mingEdit.getText().toString();
                if (inputStr.equals("")){
                    Toast.makeText(getActivity(), "待加密数据不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                byte[] inputData = inputStr.getBytes();
                byte[] outputData = new byte[0];
                try {
                    outputData = sha.encryptSHA(inputData,shaNumber);
                }catch (Exception e){
                    e.printStackTrace();
                }
                BigInteger shaData = new BigInteger(1, outputData);
                miEdit.setText(shaData.toString(16));
            }
        }
    }

    public static SHAFragment newInstance(int sectionNumber, String shaN){
        SHAFragment fragment = new SHAFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER,sectionNumber);
        args.putString(SHA_NUMBER,shaN);
        fragment.setArguments(args);
        return fragment;
    }
}
