package com.example.trpo_classifier

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
private const val MIN_CONFIDENCE = 0.8f

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.trpo_classifier", appContext.packageName)
    }
}

@RunWith(AndroidJUnit4::class)
class ClassifierLabelingTest {
    @Test
    fun imgClassifierTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        val img = InputImage.fromBitmap(getTestBitMap(appContext), 0)

        labeler.process(img)
            .addOnSuccessListener { labels ->
                labels.forEach { label ->
                    assertEquals("Cat", label.text)
                }
            }
            .addOnFailureListener {
                Log.e("Test", "Classifier ex", it.cause)
            }
    }
}

@RunWith(AndroidJUnit4::class)
class ClassifierConfidenceTest {
    @Test
    fun confidenceClassifierTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val labeler = ImageLabeling.getClient(
            ImageLabelerOptions.Builder().setConfidenceThreshold(MIN_CONFIDENCE).build()
        )
        val img = InputImage.fromBitmap(getTestBitMap(appContext), 0)

        labeler.process(img)
            .addOnSuccessListener { labels ->
                labels.forEach { label ->
                    assertEquals(true, MIN_CONFIDENCE <= label.confidence)
                }
            }
            .addOnFailureListener {
                Log.e("Test", "Classifier ex", it.cause)
            }
    }
}


fun getTestBitMap(context: Context): Bitmap =
    BitmapFactory.decodeResource(context.resources, R.mipmap.test_img_foreground);
