package com.example.mz_focusnews;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ContentFragment extends Fragment {

    private TextView tv_title;
    private TextView tv_time;
    private TextView summary1, summary2, summary3;
    private TextView relatedNews1, relatedNews2;
    private ImageView image;
    private ImageButton btn_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        tv_title = view.findViewById(R.id.news_title);
        tv_time = view.findViewById(R.id.news_date);
        summary1 = view.findViewById(R.id.news_content1);
        summary2 = view.findViewById(R.id.news_content2);
        summary3 = view.findViewById(R.id.news_content3);
        image = view.findViewById(R.id.news_img);
        relatedNews1 = view.findViewById(R.id.news_related1);
        relatedNews2 = view.findViewById(R.id.news_related2);

        // 뒤로가기
        btn_back = view.findViewById(R.id.backbtn);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ContentFragment.this).navigateUp();
            }
        });

        // Bundle에서 newsId 추출
        if (getArguments() != null) {
            int newsId = getArguments().getInt("newsId", -1);
            fetchSummaryData(getActivity(), newsId, view);
        }

        relatedNews1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    public void fetchSummaryData(Context context, int newsId, View view) {

    }
}