package com.shadowhall.game

import android.app.Activity
import android.os.Bundle
import android.opengl.GLSurfaceView
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.graphics.Color
import android.graphics.Typeface

class MainActivity : Activity() {
    private lateinit var game: HallSurface
    private lateinit var hud: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = FrameLayout(this)
        game = HallSurface(this)
        root.addView(game, FrameLayout.LayoutParams(-1, -1))
        hud = TextView(this).apply {
            text = "SHADOW HALL  •  اجمع المفاتيح واهرب\nاسحب يسار الشاشة للحركة • اسحب يمينها لتدوير الكاميرا • اضغط اختباء قرب الأثاث"
            setTextColor(Color.WHITE); textSize = 13f; typeface = Typeface.DEFAULT_BOLD
            setBackgroundColor(0x990A0D18.toInt()); setPadding(18, 12, 18, 12)
        }
        root.addView(hud, FrameLayout.LayoutParams(-2, -2, Gravity.TOP or Gravity.START))
        val hide = TextView(this).apply {
            text = "اختباء"; textSize = 16f; typeface = Typeface.DEFAULT_BOLD; gravity = Gravity.CENTER
            setTextColor(Color.WHITE); setBackgroundColor(0xCC6D2635.toInt()); setPadding(28, 18, 28, 18)
            setOnClickListener { game.renderer.hidden = !game.renderer.hidden; text = if (game.renderer.hidden) "اخرج" else "اختباء" }
        }
        root.addView(hide, FrameLayout.LayoutParams(-2, -2, Gravity.BOTTOM or Gravity.END).apply { setMargins(0,0,28,22) })
        setContentView(root)
    }
    override fun onPause() { super.onPause(); game.onPause() }
    override fun onResume() { super.onResume(); game.onResume() }
}

class HallSurface(activity: Activity) : GLSurfaceView(activity) {
    val renderer = HallRenderer()
    private var downX = 0f; private var downY = 0f; private var lastX = 0f; private var lastY = 0f
    init { setEGLContextClientVersion(2); setRenderer(renderer); renderMode = RENDERMODE_CONTINUOUSLY }
    override fun onTouchEvent(e: MotionEvent): Boolean {
        val w = width.toFloat().coerceAtLeast(1f); val h = height.toFloat().coerceAtLeast(1f)
        when (e.action) {
            MotionEvent.ACTION_DOWN -> { downX=e.x; downY=e.y; lastX=e.x; lastY=e.y; return true }
            MotionEvent.ACTION_MOVE -> {
                val dx=(e.x-lastX)/w; val dy=(e.y-lastY)/h
                if (downX < w*0.48f) { renderer.moveX = ((e.x-downX)/(w*0.18f)).coerceIn(-1f,1f); renderer.moveZ=((e.y-downY)/(h*0.18f)).coerceIn(-1f,1f) }
                else renderer.cameraYaw += dx*220f
                lastX=e.x; lastY=e.y; return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> { renderer.moveX=0f; renderer.moveZ=0f; return true }
        }
        return true
    }
}