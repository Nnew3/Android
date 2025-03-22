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

public class MonthlyNewsFragment extends Fragment {

    private String user_id;

    private TextView monthly_title;
    private TextView monthly_content;
    private TextView monthly_date;
    private ImageView monthly_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_news, container, false);

        monthly_title = view.findViewById(R.id.monthly_title);
        monthly_content = view.findViewById(R.id.monthly_content);
        monthly_image = view.findViewById(R.id.monthly_image);
        monthly_date = view.findViewById(R.id.monthly_date);

        SharedPreferences sp = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        user_id = sp.getString("user_id", null);

        monthly_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loadMonthlyNews();
        return view;
    }

    private void loadMonthlyNews() {

    }

    private String getStartDateOfMonth() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul")); // 한국 표준시로 설정
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return dateFormat.format(calendar.getTime());
    }
}
