package demo.eclipsek20.umpsterminal

import demo.eclipsek20.umpsterminal.R.raw
import android.content.Context
import android.media.MediaPlayer


fun playSinglePing(context: Context) {
    val mp: MediaPlayer = MediaPlayer.create(context, raw.notification_decorative_02)
    mp.start()
}

fun playDoublePing(context: Context) {
    val mp: MediaPlayer = MediaPlayer.create(context, raw.notification_decorative_01)
    mp.start()
}