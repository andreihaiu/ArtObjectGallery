package eu.andreihaiu.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String?.formatDate(): String? {
    return try {
        LocalDateTime
            .parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .atOffset(ZoneOffset.UTC)
            .format(DateTimeFormatter.RFC_1123_DATE_TIME)
    } catch (e: Exception) {
        ""
    }
}