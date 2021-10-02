package com.example.trpo_classifier

import com.example.trpo_classifier.data.ClassifierOutput
import com.example.trpo_classifier.data.toPercent
import org.junit.Assert.assertEquals
import org.junit.Test

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
class ExampleUnitTest {
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
        val testText = "$testName ${testConf.toPercent()}%"
        assertEquals(testText, test.toString())
    }
}