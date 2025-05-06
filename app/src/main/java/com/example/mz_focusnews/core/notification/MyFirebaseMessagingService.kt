package com.example.mz_focusnews.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mz_focusnews.MainActivity
import com.example.mz_focusnews.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    // 메세지 수신 시 호출
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // 메세지 안에 페이로드(전송된 데이터)가 포함되어 있는지 확인
        if (message.data.isNotEmpty()) {
            Log.d("FCM", "Message data payload: ${message.data}")
            sendNotification(
                message.data["title"].toString(),
                message.data["body"].toString(),
            )
        } else {
            // 메시지에 알림 페이로드가 포함되어 있는지 확인
            message.notification?.let {
                sendNotification(
                    message.notification!!.title.toString(),
                    message.notification!!.body.toString(),
                )
            }
        }
    }

    // 새 토큰이 생성될 때마다 onNewToken 콜백이 호출됨
    // 등록 토큰이 처음 생성되므로 여기서 토큰을 검색할 수 있다
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendRegistrationToServer(token)
    }

    // 메세지에 데이터 페이로드가 포함되어있을 떄 실행
    // 장시간 실행 작업의 경우 비동기 작업 예약
    fun scheduleJob() {
        val work = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build()
        WorkManager.getInstance(this)
            .beginWith(work)
            .enqueue()
    }

    // 메시지에 데이터 페이로드가 포함 되어 있을 때 실행되는 메서드
    // 10초 이내로 걸릴 때 메시지를 처리한다.
    private fun handleNow() {
        Log.d("FCM", "Short lived task is done.")
    }

    // 타사 서버에 토큰을 유지해주는 메서드이다.
    private fun sendRegistrationToServer(token: String?) {
        Log.d("FCM", "sendRegistrationTokenToServer($token)")
    }

    // 알림 생성 및 표시
    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification(title: String, body: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "fcm_default_channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.img_loading_kitty)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

    internal class MyWorker(context: Context, params: WorkerParameters) :
        Worker(context, params) {
        override fun doWork(): Result {
            return Result.success()
        }
    }
}
