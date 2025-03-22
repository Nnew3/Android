package com.example.mz_focusnews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyPageFragment extends Fragment {

    private TextView tv_name;
    private Switch switch_alarm;

    private String user_id;
    private String user_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        tv_name = view.findViewById(R.id.my_name);
        switch_alarm = view.findViewById(R.id.switch_alarm);

        // Load user data from SharedPreferences
        SharedPreferences sp = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        user_id = sp.getString("user_id", null);
        user_name = sp.getString("user_name", null);
        tv_name.setText(user_name);

        fetchUserData(user_id);

        // Switch 상태 변경 리스너 설정
        switch_alarm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateAlarmSetting(isChecked);
        });

        return view;
    }

    private void fetchUserData(String userId) {

    }

    private void updateAlarmSetting(boolean isEnabled) {

    }
}
