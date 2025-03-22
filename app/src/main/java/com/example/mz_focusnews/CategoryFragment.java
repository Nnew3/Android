package com.example.mz_focusnews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mz_focusnews.NewsDB.News;
import com.example.mz_focusnews.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private String user_id;

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<News> newsList;
    private NavController navController;
    private Button selectedButton; // 현재 선택된 버튼을 추적
    private String currentCategory = "politics"; // 현재 선택된 카테고리
    private String currentSortOption = "latest"; // 기본 정렬 옵션

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        SharedPreferences sp = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        user_id = sp.getString("user_id", null); // 기본값으로 null 설정

        newsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            newsAdapter = new NewsAdapter(getActivity(), newsList, news -> handleNewsClick(news));
        }

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);

        loadNewsByCategory(currentCategory);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        newsAdapter = new NewsAdapter(getActivity(), newsList, news -> handleNewsClick(news));
        recyclerView.setAdapter(newsAdapter);

        initializeUIComponents(view);

        return view;
    }

    private void handleNewsClick(News news) {
        Bundle bundle = new Bundle();
        bundle.putInt("newsId", news.getNewsId());

        NavHostFragment.findNavController(CategoryFragment.this)
                .navigate(R.id.action_categoryFragment_to_contentFragment, bundle);
    }

    private void initializeUIComponents(View view) {
        Button politicsButton = view.findViewById(R.id.politics);
        politicsButton.setOnClickListener(v -> loadNewsByCategory("politics"));
    }

    private void loadNewsByCategory(String category) {

    }
}