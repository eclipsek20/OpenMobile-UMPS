package demo.eclipsek20.umpsterminal

import android.content.Context
import android.media.MediaPlayer
import android.os.VibrationEffect
import android.os.Vibrator


fun playSinglePing(context: Context) {
    val vibrator = context.getSystemService(Vibrator::class.java)
    val mVibratePattern = longArrayOf(0, 200)
    val effect = VibrationEffect.createWaveform(mVibratePattern, -1)
    vibrator?.vibrate(effect)
    val mp: MediaPlayer = MediaPlayer.create(context, R.raw.notification_decorative_02)
    mp.start()
}

fun playDoublePing(context: Context) {
    val vibrator = context.getSystemService(Vibrator::class.java)
    val mVibratePattern = longArrayOf(0, 150, 100, 150)
    val effect = VibrationEffect.createWaveform(mVibratePattern, -1)
    vibrator?.vibrate(effect)
    val mp: MediaPlayer = MediaPlayer.create(context, R.raw.notification_decorative_01)
    mp.start()
}