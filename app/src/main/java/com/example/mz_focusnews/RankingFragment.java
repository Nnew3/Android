package com.example.mz_focusnews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class RankingFragment extends Fragment {
    private String USER_ID;     // 사용자 ID

    private Button btn_quiz_start;

    private TextView score1, score2, score3;
    private TextView score_user;
    private TextView rank_user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        USER_ID = preferences.getString("user_id", "null");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        score1 = view.findViewById(R.id.score1);
        score2 = view.findViewById(R.id.score2);
        score3 = view.findViewById(R.id.score3);
        score_user = view.findViewById(R.id.score_user);
        rank_user = view.findViewById(R.id.rank_user);

        // 퀴즈 랭킹 보여주기
        showRanking(view);

        btn_quiz_start = view.findViewById(R.id.quizStart);

        // 게임 시작 버튼 클릭 시 퀴즈 화면으로 이동
        btn_quiz_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private void showRanking(View view) {

    }
}
