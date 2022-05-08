package com.idayrus.layout.swan

import java.io.Serializable

data class LinearConstraints(
    var gravity: String = START,
    var width: Int = WRAP_CONTENT,
    var height: Int = WRAP_CONTENT,
    var calculatedWidth: Int = 0,
    var calculatedHeight: Int = 0,
    var marginTop: Int = 0,
    var marginEnd: Int = 0,
    var marginBottom: Int = 0,
    var marginStart: Int = 0,
    private var marginValue :Int = 0,
    private var weightValue :Double = 0.0
) : Serializable {

    companion object {
        const val MATCH_PARENT = -1
        const val WRAP_CONTENT = -2
        const val START = "start"
        const val END = "end"
        const val TOP = "top"
        const val BOTTOM = "bottom"
        const val CENTER = "center"
    }

    var margin : Int
        get() = marginValue
        set(value) {
            marginTop = value
            marginEnd = value
            marginBottom = value
            marginStart = value
            marginValue = value
        }
    var weight : Double
        get() = weightValue
        set(value) {
            weightValue = if (value in 0.0..1.0) {
                value
            } else {
                1.0
            }
        }

    fun reset() {
        gravity = START
        width = WRAP_CONTENT
        height = WRAP_CONTENT
        marginValue = 0
        weightValue = 0.0
    }
}