package com.idayrus.layout.swan

import com.google.gson.Gson
import java.io.Serializable

class LinearConstraints : Serializable {

    companion object {
        const val MATCH_PARENT = -1
        const val WRAP_CONTENT = -2
        const val START = "start"
        const val END = "end"
        const val TOP = "top"
        const val BOTTOM = "bottom"
        const val CENTER = "center"
    }

    var gravity = START
    var width = WRAP_CONTENT
    var height = WRAP_CONTENT
    var calculatedWidth = 0
    var calculatedHeight = 0
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
    var weight = 0.0
        set(weight) {
            field = if (weight in 0.0..1.0) {
                weight
            } else {
                1.0
            }
        }

    fun clone(): LinearConstraints {
        return Gson().fromJson(toString(), LinearConstraints::class.java)
    }

    fun reset() {
        gravity = START
        width = WRAP_CONTENT
        height = WRAP_CONTENT
        margin = 0
        weight = 0.0
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}