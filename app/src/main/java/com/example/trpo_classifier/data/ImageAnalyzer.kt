package com.example.trpo_classifier.data

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage

class ImageAnalyzer(private val listener: (InputImage) -> Unit) :
    ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        mediaImage.let {
            val image: InputImage = InputImage.fromMediaImage(
                mediaImage!!,
                imageProxy.imageInfo.rotationDegrees
            )
            listener(image)
        }
        imageProxy.close()
    }
}