package uz.gita.cartoonapp.utils

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import uz.gita.cartoonapp.BuildConfig


fun OkHttpClient.Builder.addLoggingInterceptor(context: Context): OkHttpClient.Builder {
    HttpLoggingInterceptor.Level.HEADERS
    val logging = HttpLoggingInterceptor.Logger { message -> Timber.tag("HTTP").d(message) }
    if (BuildConfig.LOGGING) {
        addInterceptor(ChuckInterceptor(context))
        addInterceptor(HttpLoggingInterceptor(logging))
    }
    return this
}
