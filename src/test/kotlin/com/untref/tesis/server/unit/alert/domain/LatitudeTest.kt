package com.untref.tesis.server.unit.alert.domain

import com.untref.tesis.server.alert.action.validator.*
import com.untref.tesis.server.alert.domain.CardinalPoint
import com.untref.tesis.server.alert.domain.Latitude
import com.untref.tesis.server.builders.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Test

class LatitudeTest {

    private var latitude: Latitude? = null
    private var degree: Int? = null
    private var minute: Int? = null
    private var second: Double? = null
    private var cardinalPoint: CardinalPoint? = null
    private var exception:Throwable? = null

    @Test
    fun latitudeWithEastCardinalPointThrowsException() {
        givenLatitudeParameters(cardinalPoint = CardinalPoint.EAST)

        whenCreateLatitude()

        thenExpectedException(latitudeCardinalPointCanNotBeEast)
    }

    @Test
    fun latitudeWithWestCardinalPointThrowsException() {
        givenLatitudeParameters(cardinalPoint = CardinalPoint.WEST)

        whenCreateLatitude()

        thenExpectedException(latitudeCardinalPointCanNotBeWest)
    }

    @Test
    fun latitudeSecondsHigherOrEqualsThan60ThrowsException() {
        givenLatitudeParameters(second = 60.0)

        whenCreateLatitude()

        thenExpectedException(secondCanNotBeHigherOrEqualsThanSixty)
    }

    @Test
    fun latitudeSecondsLowerThan0ThrowsException() {
        givenLatitudeParameters(second = -0.01)

        whenCreateLatitude()

        thenExpectedException( secondCanNotBeLowerThanZero)
    }

    @Test
    fun latitudeDegreeLowerThan0ThrowsException() {
        givenLatitudeParameters(degree = -1)

        whenCreateLatitude()

        thenExpectedException(degreeCanNotBeLowerThan0)
    }

    @Test
    fun latitudeDegreeHigherOrEqualsThan90ThrowsException() {
        givenLatitudeParameters(degree = 90)

        whenCreateLatitude()

        thenExpectedException(degreeCanNotBeHigherThan90ForLatitude)
    }

    @Test
    fun latitudeMinuteLowerThan0ThrowsException() {
        givenLatitudeParameters(minute = -1)

        whenCreateLatitude()

        thenExpectedException(minuteCanNotLowerThanZero)
    }

    @Test
    fun latitudeMinuteHigherOrEqualsThan60ThrowsException() {
        givenLatitudeParameters(minute = 60)

        whenCreateLatitude()

        thenExpectedException(minuteCanNotHigherOrEqualsThanSixteen)
    }

    @Test
    fun validParametersBuildLatitudeCorrectly(){
        givenLatitudeParameters()

        whenCreateLatitude()

        thenCreateCorrectly()
    }

    private fun thenCreateCorrectly() {
        assertThat(latitude).isNotNull()
        assertThat(latitude!!.cardinalPoint).isEqualTo(south)
        assertThat(latitude!!.degree).isEqualTo(defaultDegree)
        assertThat(latitude!!.minute).isEqualTo(defaultMinute)
        assertThat(latitude!!.second).isEqualTo(defaultSecond)
    }

    private fun givenLatitudeParameters(degree: Int? = defaultDegree, minute: Int? = defaultMinute,
                                        second: Double? = defaultSecond, cardinalPoint: CardinalPoint? = south) {
        this.degree = degree
        this.minute = minute
        this.second = second
        this.cardinalPoint = cardinalPoint
    }

    private fun thenExpectedException(message: String) {
        assertThat(exception).isInstanceOf(RuntimeException::class.java)
        assertThat(exception!!.message).isEqualTo(message)
    }

    private fun whenCreateLatitude(){
        exception = catchThrowable({latitude = Latitude.build(degree!!, minute!!, second!!, cardinalPoint!!) })
    }
}