package com.untref.tesis.server.unit.alert.domain

import com.untref.tesis.server.alert.domain.*
import com.untref.tesis.server.builders.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LongitudeTest {

    private var exception:Throwable? = null
    private var longitude: Longitude? = null
    private var degree: Int? = null
    private var minute: Int? = null
    private var second: Double? = null
    private var cardinalPoint: CardinalPoint? = null



    @Test
    fun longitudeWithNorthCardinalPointThrowsException() {
        givenLongitudeParameters(cardinalPoint = CardinalPoint.NORTH)

        whenCreateLongitude()

        thenExpectedException(longitudeCardinalPointCanNotBeNorth)
    }

    @Test
    fun longitudeWithSouthCardinalPointThrowsException() {
        givenLongitudeParameters(cardinalPoint = CardinalPoint.SOUTH)

        whenCreateLongitude()

        thenExpectedException(longitudeCardinalPointCanNotBeSouth)
    }

    @Test
    fun longitudeSecondsLowerThan0ThrowsException() {
        givenLongitudeParameters(second = -0.1)

        whenCreateLongitude()

        thenExpectedException(longitudeSecondCanNotLowerThan0)
    }

    @Test
    fun longitudeSecondsHigherOrEqualsThan60ThrowsException() {
        givenLongitudeParameters(second = 60.0)

        whenCreateLongitude()

        thenExpectedException(longitudeSecondCanNotHigherOrEqualsThan60)
    }

    @Test
    fun longitudeMinuteLowerThan0ThrowsException() {
        givenLongitudeParameters(minute = -1)

        whenCreateLongitude()

        thenExpectedException(longitudeMinuteCanNotLowerZero)
    }

    @Test
    fun longitudeMinuteHigherOrEqualsThan60ThrowsException() {
        givenLongitudeParameters(minute = 60)

        whenCreateLongitude()

        thenExpectedException(longitudeMinuteCanNotHigherOrEqualsThan60)
    }

    @Test
    fun longitudeDegreeLowerThan0ThrowsException() {
        givenLongitudeParameters(degree = -1)

        whenCreateLongitude()

        thenExpectedException(longitudeDegreeCanNotLowerThanZero)
    }

    @Test
    fun longitudeDegreeHigherThan180ThrowsException() {
        givenLongitudeParameters(degree = 180)

        whenCreateLongitude()

        thenExpectedException(longitudeDegreeCanNotHigherThanOneHundredEighteen)
    }

    @Test
    fun buildWithValidParametersCreateLongitudeCorrectly(){
        givenLongitudeParameters()

        whenCreateLongitude()

        thenCreateCorrectly()
    }

    private fun thenCreateCorrectly() {
        assertThat(longitude).isNotNull()
        assertThat(longitude!!.degree).isEqualTo(degree)
        assertThat(longitude!!.minute).isEqualTo(minute)
        assertThat(longitude!!.second).isEqualTo(second)
        assertThat(longitude!!.cardinalPoint).isEqualTo(cardinalPoint)
    }

    private fun givenLongitudeParameters(degree: Int? = defaultDegree, minute: Int? = defaultMinute,
                                        second: Double? = defaultSecond, cardinalPoint: CardinalPoint? = east) {
        this.degree = degree
        this.minute = minute
        this.second = second
        this.cardinalPoint = cardinalPoint
    }

    private fun thenExpectedException(message: String) {
        Assertions.assertThat(exception).isInstanceOf(RuntimeException::class.java)
        Assertions.assertThat(exception!!.message).isEqualTo(message)
    }

    private fun whenCreateLongitude(){
        exception = Assertions.catchThrowable({ longitude = Longitude.build(degree!!, minute!!, second!!, cardinalPoint!!) })
    }
}