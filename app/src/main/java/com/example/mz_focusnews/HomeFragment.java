package com.example.mz_focusnews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mz_focusnews.NewsDB.News;
import com.example.mz_focusnews.adapter.BreakingNewsAdapter;
import com.example.mz_focusnews.adapter.InterestAdapter;
import com.example.mz_focusnews.adapter.ViewPager2Adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class HomeFragment extends Fragment {
    private String user_id;
    private String user_name;

    private RecyclerView recyclerView;
    private RecyclerView breakingNewsRecyclerView; // 속보 뉴스 RecyclerView

    private ProgressBar progressBar;
    private InterestAdapter interestAdapter;
    private BreakingNewsAdapter breakingNewsAdapter; // 속보 뉴스 Adapter
    private List<News> newsList;
    private List<News> breakingNewsList; // 속보 뉴스 리스트
    private TextView breakingNewsButton; // 속보 뉴스 버튼 참조를 위한 변수
    private DrawerLayout drawerLayout; // DrawerLayout 참조를 위한 변수

    private static final String PREFS_NAME = "BreakingNewsPrefs";
    private static final String LAST_BREAKING_NEWS_TITLE = "LastBreakingNewsTitle";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 뒤로가기 버튼 눌렀을 때 앱이 종료되게 함
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });

        // DrawerLayout 참조
        drawerLayout = view.findViewById(R.id.drawer_layout);

        // 사용자 이름 및 현재 시간 설정
        TextView userName = view.findViewById(R.id.user_name);
        TextView nowDate = view.findViewById(R.id.current_date);
        breakingNewsButton = view.findViewById(R.id.breakingNews); // 속보 뉴스 버튼 참조
        ImageView breakingNewsListButton = view.findViewById(R.id.breaking_news_list); // 드로어 열기 버튼 참조

        // SharedPreferences로 데이터 받아오기: 아이디, 이름
        SharedPreferences sp = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        user_id = sp.getString("user_id", null);
        user_name = sp.getString("user_name", null);

        userName.setText("Hi, " + user_name);

        setCurrentDate(nowDate);

        // ViewPager2 설정 (오늘, 이주, 이달의 뉴스)
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(getActivity());
        ViewPager2 viewPager2 = view.findViewById(R.id.news_view_pager);
        viewPager2.setAdapter(viewPager2Adapter);

        // 리사이클러뷰 초기화
        recyclerView = view.findViewById(R.id.rv_interest_content);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        // 뉴스 아이템 리스트 초기화
        newsList = new ArrayList<>();

        // 어댑터 설정
        interestAdapter = new InterestAdapter(getActivity(), newsList, new InterestAdapter.OnNewsClickListener() {
            @Override
            public void onNewsClick(News news) {
                // 사용자 상호작용 기록 및 관심 카테고리 업데이트

                // Bundle 객체 생성 및 news_id 추가
                Bundle bundle = new Bundle();
                bundle.putInt("newsId", news.getNewsId());

                // contentFragment로 이동하면서 데이터 전달
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_homeFragment_to_contentFragment, bundle);
            }
        });

        recyclerView.setAdapter(interestAdapter);

        fetchNewsData(view); // 기존 뉴스 데이터 가져오는 메소드 호출
        fetchBreakingNewsData(); // 속보 뉴스 데이터 가져오는 메소드 호출 추가

        // 속보 뉴스 리사이클러뷰 초기화
        breakingNewsRecyclerView = view.findViewById(R.id.rv_breaking_news);
        breakingNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // 속보 뉴스 리스트 초기화
        breakingNewsList = new ArrayList<>();

        // 어댑터 설정
        breakingNewsAdapter = new BreakingNewsAdapter(getActivity(), breakingNewsList, new BreakingNewsAdapter.OnNewsClickListener() {
            @Override
            public void onNewsClick(News news) {

            }
        });

        breakingNewsRecyclerView.setAdapter(breakingNewsAdapter);

        // 드로어 열기 버튼 클릭 리스너 설정
        breakingNewsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(view.findViewById(R.id.right_drawer))) {
                    drawerLayout.closeDrawer(view.findViewById(R.id.right_drawer));
                } else {
                    fetchBreakingNewsData(); // 드로어가 열릴 때 속보 뉴스 데이터를 가져옴
                    drawerLayout.openDrawer(view.findViewById(R.id.right_drawer));
                }
            }
        });

        return view;
    }

    // 속보 뉴스 데이터를 가져오는 메소드 추가
    private void fetchBreakingNewsData() {

    }

    // 뉴스 데이터 가져오는 메소드
    private void fetchNewsData(View view) {

    }

    // 현재 날짜 불러오는 메소드
    private static void setCurrentDate(TextView nowDate) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul")); // 한국 표준시로 설정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String formattedDate = dateFormat.format(calendar.getTime());
        nowDate.setText(formattedDate);
    }
}
