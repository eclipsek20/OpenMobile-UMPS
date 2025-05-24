package demo.eclipsek20.umpsterminal

import demo.eclipsek20.umpsterminal.R.raw
import android.content.Context
import android.media.MediaPlayer
import android.os.VibrationEffect
import android.os.Vibrator


fun playSinglePing(context: Context) {
    val vibrator = context.getSystemService(Vibrator::class.java)
    vibrator?.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
    val mp: MediaPlayer = MediaPlayer.create(context, raw.notification_decorative_02)
    mp.start()
}

fun playDoublePing(context: Context) {
    val vibrator = context.getSystemService(Vibrator::class.java)
    vibrator?.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
    val mp: MediaPlayer = MediaPlayer.create(context, raw.notification_decorative_01)
    mp.start()
}