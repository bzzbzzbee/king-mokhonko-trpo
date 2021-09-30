package com.example.trpo_classifier.data

data class ClassifierOutput(val name: String, val confidence: Float) {
    override fun toString(): String {
        val percent = (confidence * 100).toInt()
        return "$name: $percent%"
    }
}