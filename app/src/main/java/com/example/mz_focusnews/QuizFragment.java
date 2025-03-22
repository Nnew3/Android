package com.example.mz_focusnews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

public class QuizFragment extends Fragment {
    private int SCORE = 0;      // 사용자 획득 점수

    // 테스트용 데이터
    private String USER_ID;    // 사용자 ID
    private String USER_ANSWER = "NONE";    // 사용자가 입력한 정답
    private static String SUMMARY;

    // 프론트
    private NavController navController;
    private TextView text_question, text_score, text_progress;
    private Button btn_option1, btn_option2, btn_option3, btn_option4, btn_stop, btn_next, btn_complete;
    private ImageView img_correct, img_incorrect, img_character_default, img_character_today_quiz;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 로그인할 때, SharedPreferences로 저장된 USER_ID 가져오기
        SharedPreferences preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        USER_ID = preferences.getString("user_id", "null");

        // SharedPreferences로 저장된 SUMMARY 가져오기
        preferences = getActivity().getSharedPreferences("NewsData", Context.MODE_PRIVATE);
        SUMMARY = preferences.getString("summary", "null");

        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        text_question = view.findViewById(R.id.question);
        text_score = view.findViewById(R.id.score);
        text_progress = view.findViewById(R.id.progress);
        btn_option1 = view.findViewById(R.id.option1);
        btn_option2 = view.findViewById(R.id.option2);
        btn_option3 = view.findViewById(R.id.option3);
        btn_option4 = view.findViewById(R.id.option4);

        img_correct = view.findViewById(R.id.quiz_correct);
        img_incorrect = view.findViewById(R.id.quiz_incorrect);
        img_character_default = view.findViewById(R.id.character_default);
        img_character_today_quiz = view.findViewById(R.id.character_today_quiz);

        showQuiz(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void showQuiz(View view) {

    }
}