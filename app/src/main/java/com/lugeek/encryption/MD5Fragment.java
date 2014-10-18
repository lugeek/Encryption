package com.lugeek.encryption;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lugeek.algorithm.md5;

import java.math.BigInteger;

public class MD5Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private EditText mingEdit;
    private Button encryptButton;
    private EditText miEdit;
    public MD5Fragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

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
        public void onClick(View view) {
            if (view == encryptButton){
                String mingwen = mingEdit.getText().toString();
                if (mingwen.equals("")){
                    Toast.makeText(getActivity(), "待加密数据不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                byte[] mingData = mingwen.getBytes();
                BigInteger md5Data = null;
                try {
                    md5Data = new BigInteger(1, md5.encryptMD5(mingData));
                }catch (Exception e){
                    e.printStackTrace();
                }
                String md5Str = md5Data.toString(16);
                if(md5Str.length()<32){
                    md5Str = 0 + md5Str;
                }
                miEdit.setText(md5Str);
            }
        }
    }
    public static MD5Fragment newInstance(int sectionNumber){
        MD5Fragment fragment = new MD5Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
