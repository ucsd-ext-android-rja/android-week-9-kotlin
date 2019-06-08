package com.ucsdextandroid1.kotlinlist

import android.graphics.Color
import androidx.annotation.ColorInt
import java.util.*

/**
 * Created by rjaylward on 2019-06-07
 */

object Utils {

    @JvmStatic
    @ColorInt
    fun randomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

}