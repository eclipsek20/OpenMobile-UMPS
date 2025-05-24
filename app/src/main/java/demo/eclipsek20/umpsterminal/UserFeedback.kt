package demo.eclipsek20.umpsterminal

import android.content.Context
import android.media.MediaPlayer
import android.os.VibrationEffect
import android.os.Vibrator


fun playSinglePing(context: Context, startDelay: Long = 100) {
    val vibrator = context.getSystemService(Vibrator::class.java)
    val mVibratePattern = longArrayOf(startDelay, 200)
    val effect = VibrationEffect.createWaveform(mVibratePattern, -1)
    vibrator?.vibrate(effect)
    val mp: MediaPlayer = MediaPlayer.create(context, R.raw.notification_decorative_02)
    mp.start()
}

fun playDoublePing(context: Context, startDelay: Long = 100) {
    val vibrator = context.getSystemService(Vibrator::class.java)
    val mVibratePattern = longArrayOf(startDelay, 150, 100, 150)
    val effect = VibrationEffect.createWaveform(mVibratePattern, -1)
    vibrator?.vibrate(effect)
    val mp: MediaPlayer = MediaPlayer.create(context, R.raw.notification_decorative_01)
    mp.start()
}