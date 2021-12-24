package com.idayrus.layout.swan

import com.google.gson.Gson
import java.io.Serializable

class FrameConstraints : Serializable {

    companion object {
        const val MATCH_PARENT = -1
        const val WRAP_CONTENT = -2
        const val TOP_START = "top_start"
        const val TOP_END = "top_end"
        const val TOP_CENTER = "top_center"
        const val CENTER_START = "center_start"
        const val CENTER_END = "center_end"
        const val CENTER_CENTER = "center"
        const val BOTTOM_START = "bottom_start"
        const val BOTTOM_END = "bottom_end"
        const val BOTTOM_CENTER = "bottom_center"
        const val CENTER = "center"
    }

    var gravity = CENTER
    var width = WRAP_CONTENT
    var height = WRAP_CONTENT
    var marginTop = 0
    var marginEnd = 0
    var marginBottom = 0
    var marginStart = 0
    var margin = 0
        set(margin) {
            marginTop = margin
            marginEnd = margin
            marginBottom = margin
            marginStart = margin
            field = margin
        }

    fun clone(): FrameConstraints {
        return Gson().fromJson(toString(), FrameConstraints::class.java)
    }

    fun reset() {
        gravity = LinearConstraints.START
        width = LinearConstraints.WRAP_CONTENT
        height = LinearConstraints.WRAP_CONTENT
        margin = 0
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}