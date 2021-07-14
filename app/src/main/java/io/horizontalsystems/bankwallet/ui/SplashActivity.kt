package io.horizontalsystems.bankwallet.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import io.horizontalsystems.bankwallet.R
import io.horizontalsystems.bankwallet.modules.launcher.LauncherActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)
        app_icon.animation = AnimationUtils.loadAnimation(this, R.anim.pulse)
        val timmer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, LauncherActivity::class.java))
                finish()
            }
        }
        timmer.start()
    }
}