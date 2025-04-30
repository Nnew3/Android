package com.example.mz_focusnews.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun stringSplit(max: Int, s: String): String {
    if (s.length <= max) return s

    val words = s.split(" ")
    var result = ""

    for (word in words) {
        if (result.length + word.length + 1 > max) break
        result += "$word "
    }

    return result.trim() + "..."
}

// summary는 총 세 문장으로만 이루어져있다 (온점도 단 세개만 존재)
fun summaryToThree(content: String): List<String> {
    return content.split(".").map { it.trim() }
        .filter { it.isNotEmpty() }
}

@RequiresApi(Build.VERSION_CODES.O)
fun calculateRelativeDate(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    val inputDate = LocalDate.parse(dateString, formatter)
    val today = LocalDate.now()

    val daysDiff = ChronoUnit.DAYS.between(inputDate, today)
    val monthsDiff = ChronoUnit.MONTHS.between(inputDate, today)

    return when {
        daysDiff < 1 -> "오늘"
        daysDiff == 1L -> "하루 전"
        daysDiff in 2..6 -> "${daysDiff}일 전"
        daysDiff in 7..13 -> "1주 전"
        daysDiff in 14..20 -> "2주 전"
        monthsDiff == 1L -> "1개월 전"
        monthsDiff == 2L -> "2개월 전"
        monthsDiff == 3L -> "3개월 전"
        else -> dateString // 너무 오래된 건 그냥 날짜로 표시
    }
}