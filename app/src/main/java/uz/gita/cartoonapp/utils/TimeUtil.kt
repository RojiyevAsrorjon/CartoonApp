package uz.gita.bbc_news_demo.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit

val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec")
fun getPublishedDay(date: String): String {
    val day = date.subSequence(0, 10)
    val numArray = day.split("-")
    val month = numArray[1].toInt()
    val newDay = "${numArray[2]} ${months[month-1]} ${numArray[0]}"
    return newDay
}

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
fun getDayDifference(date: String) : String {
    val day = date.subSequence(0, 10)
    val today = LocalDate.now().toString()
    val diff = ChronoUnit.DAYS.between(LocalDate.parse(day), LocalDate.parse(today))
    return "${diff}d"
}

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    getDayDifference("2021-12-18T13:00:00Z")
}