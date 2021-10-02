package com.example.trpo_classifier.data

data class ClassifierOutput(val name: String, val confidence: Float) {
    override fun toString(): String {
        val percent = confidence.toPercent()
        return "$name: $percent%"
    }
}

fun Float.toPercent(): Int = (this * 100).toInt()

