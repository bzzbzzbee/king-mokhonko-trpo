package com.example.trpo_classifier.data

data class ClassifierOutput(val name: String, val confidence: Float) {
    override fun toString(): String {
        return "$name, confidence: $confidence"
    }
}