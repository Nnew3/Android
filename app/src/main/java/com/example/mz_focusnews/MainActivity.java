package com.example.mz_focusnews;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final AtomicBoolean isDestroyed = new AtomicBoolean(false);

    private NotificationService notificationService; // 속보 알림 기능
    private Handler handler = new Handler();
    private final int POLLING_INTERVAL = 30000; // 30 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 속보 푸시 알림 초기화
        notificationService = new NotificationService(this);
        startNotificationPolling();

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        // 특정 프래그먼트에서 네비게이션 바 숨기기
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int dest_id = destination.getId();
                if(dest_id == R.id.loginFragment) {
                    bottomNavigationView.setVisibility(View.GONE); // 네비게이션 바 숨기기
                } else {
                    bottomNavigationView.setVisibility(View.VISIBLE); // 네비게이션 바 표시하기
                }
            }
        });

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    private void startNotificationPolling() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                notificationService.fetchNotifications();
                handler.postDelayed(this, POLLING_INTERVAL);
            }
        }, POLLING_INTERVAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed.set(true);
        executorService.shutdownNow(); // 앱 종료 시 스레드 풀 종료
        handler.removeCallbacksAndMessages(null); // 핸들러의 모든 콜백 및 메시지 제거
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (executorService != null) {
            executorService.shutdownNow();  // 모든 작업을 즉시 중지하고 스레드 풀을 종료
        }
    }
}
