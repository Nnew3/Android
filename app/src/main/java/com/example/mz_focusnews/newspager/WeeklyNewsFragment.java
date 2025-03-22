package com.example.mz_focusnews.newspager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mz_focusnews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class WeeklyNewsFragment extends Fragment {
    private String user_id;

    private TextView weekly_title;
    private TextView weekly_content;
    private TextView weekly_date;
    private ImageView weekly_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekly_news, container, false);

        weekly_title = view.findViewById(R.id.weekly_title);
        weekly_content = view.findViewById(R.id.weekly_content);
        weekly_image = view.findViewById(R.id.weekly_Image);
        weekly_date = view.findViewById(R.id.weekly_date);

        SharedPreferences sp = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        user_id = sp.getString("user_id", null);

        weekly_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loadWeeklyNews();
        return view;
    }

    private void loadWeeklyNews() {

    }

    private String getStartDateOfWeek() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul")); // 한국 표준시로 설정
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        // 현재 날짜에서 주의 첫 번째 날(월요일)로 이동
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return dateFormat.format(calendar.getTime());
    }
}