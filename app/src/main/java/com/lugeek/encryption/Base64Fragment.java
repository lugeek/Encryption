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

import com.lugeek.algorithm.base64;

public class Base64Fragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private EditText editMing;
    private Button buttonJiami;
    private Button buttonJiemi;
    private EditText editMi;

    public Base64Fragment() {
    }

    public static Base64Fragment newInstance (int sectionNumber){
        Base64Fragment fragment = new Base64Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_base64,container,false);
        editMing = (EditText)rootview.findViewById(R.id.mingEdit);
        buttonJiami = (Button)rootview.findViewById(R.id.encryptButton);
        buttonJiemi = (Button)rootview.findViewById(R.id.decryptButton);
        editMi = (EditText)rootview.findViewById(R.id.miEdit);
        buttonJiami.setOnClickListener(new myClickListener());
        buttonJiemi.setOnClickListener(new myClickListener());
        return rootview;
    }

    private class myClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view){
            if (view == buttonJiami){
                String mingwen = editMing.getText().toString();
                if (mingwen.equals("")){
                    Toast.makeText(getActivity(),"待加密数据不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                byte[] mingData = mingwen.getBytes();
                String miwen = "";
                try {
                     miwen= base64.encryptBASE64(mingData);
                }catch (Exception e){
                    e.printStackTrace();
                }
                editMi.setText(miwen);
            }else if(view == buttonJiemi){
                String miwen = editMi.getText().toString();
                if(miwen.equals("")){
                    Toast.makeText(getActivity(),"待解密数据不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                byte[] mingData = null;
                try {
                     mingData = base64.decryptBASE64(miwen);
                }catch (Exception e){
                    e.printStackTrace();
                }
                String mingwen = new String(mingData);
                editMing.setText(mingwen);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
