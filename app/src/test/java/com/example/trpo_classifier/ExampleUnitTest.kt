package com.example.trpo_classifier

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.trpo_classifier.data.ClassifierOutput
import com.example.trpo_classifier.data.toPercent
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

//TODO test cases:
//Data class toString+
//Float toPercent+
//Classifier img test
//Classifier confidence test

private const val MIN_CONFIDENCE = 0.8f

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock
    private lateinit var context: Context

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun floatToPercentIsCorrect() {
        val test = 0.15f.toPercent()
        val testInt = 15
        assertEquals(testInt, test)
    }

    @Test
    fun dataClassToStringIsCorrect() {
        val testName = "test"
        val testConf = 0.15f
        val test = ClassifierOutput(testName, testConf)
        val testText = "$testName: ${testConf.toPercent()}%"
        assertEquals(testText, test.toString())
    }

    @Test
    fun confidenceClassifierTest() {
        val labeler = ImageLabeling.getClient(
            ImageLabelerOptions.Builder().setConfidenceThreshold(MIN_CONFIDENCE).build()
        )
        val img = InputImage.fromBitmap(getTestBitMap()!!, 0)

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
    @Test
    fun imgClassifierTest(){
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        val img = InputImage.fromBitmap(getTestBitMap()!!, 0)

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

    private fun getTestBitMap(): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, R.mipmap.test_img_foreground)
        drawable.let {
            val bitmap = Bitmap.createBitmap(
                drawable!!.intrinsicWidth,
                drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)

            return bitmap
        }
    }
}