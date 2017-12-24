package com.untref.tesis.server.unit.action

import com.untref.tesis.server.action.validator.CoordinateValidator
import com.untref.tesis.server.domain.CardinalPoint
import com.untref.tesis.server.resource.dto.CoordinateDto
import com.untref.tesis.server.resource.dto.CoordinatesDto
import org.junit.Assert

import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class CoordinateValidatorTest {

    private lateinit var coordinatesDto: CoordinatesDto
    private var exception: Exception? = null
    private var latitude: CoordinateDto? = null
    private var longitude: CoordinateDto? = null

    private val degree = 30
    private val minute = 50
    private val second = 23.4f
    private val east = CardinalPoint.EAST
    private val south = CardinalPoint.SOUTH

    @JvmField
    @Rule
    val expectedException: ExpectedException = ExpectedException.none()

    private val degreeCanNotBeNull = "Degree can not null"
    private val latitudeCanNotBeNull = "Latitude can not null"
    private val degreeCanNotBeLowerThan0 = "Degree can not lower than 0"
    private val longitudeCanNotBeNull = "Longitude can not be null"
    private val degreeCanNotBeHigherThan90ForLatitude = "degree can not be higher than 90 for latitude"
    private val minuteCanNotBeNull = "minute can not be null"
    private val minuteCanNotLowerThanZero = "minute can not lower than 0"
    private val minuteCanNotHigherOrEqualsThanSixteen = "minute can not higher or equals than 60"
    private val secondCanNotBeNull = "second can not be null"
    private val secondCanNotBeLowerThanZero = "second can not be lower than zero"
    private val secondCanNotBeHigherOrEqualsThanSixteen = "second can not be higher or equals than 60"
    private val longitudeDegreeCanNotBeNull = "longitude degree can not be null"
    private val longitudeDegreeCanNotLowerThanZero = "longitude degree can not lower than 0"
    private val longitudeDegreeCanNotHigherThanOneHundredEighteen = "longitude degree can not higher  than one hundred eighteen"
    private val longitudeMinuteCanNotBeNull = "longitude minute can not be null"
    private val longitudeMinuteCanNotLower0 = "longitude minute can not lower than 0"
    private val longitudeMinuteCanNotHigherOrEqualsThan60 = "longitude minute can not higher or equals than 60"
    private val longitudeSecondCanNotNull = "longitude second can not be null"
    private val longitudeSecondCanNotLowerThan0 = "longitude second can not lower than 0"
    private val longitudeSecondCanNotHigherOrEqualsThan60 = "longitude second can not higher or equals than 60"
    private val latitudeCardinalPointCanNotBeNull = "latitude cardinal point can not be null"
    private val latitudeCardinalPointCanNotBeEast = "latitude cardinal point can not be east"
    private val latitudeCardinalPointCanNotBeWest = "latitude cardinal point can not be west"
    private val longitudeCardinalPointCanNotBeNull = "longitude cardinal point can not be null"
    private val longitudeCardinalPointCanNotBeNorth = "longitude cardinal point can not be north"
    private val longitudeCardinalPointCanNotBeSouth = "longitude cardinal point can not be south"

    @Test
    fun latitudeCanNotBeNull() {
        thenExpectedExceptionWhenTryValidate({ givenALongitude() },latitudeCanNotBeNull)
    }

    @Test
    fun longitudeCanNotBeNull() {
        thenExpectedExceptionWhenTryValidate({ givenALatitude() },longitudeCanNotBeNull)
    }

    @Test
    fun latitudeDegreeWithNullValueThrowsException() {
        givenALatitude(degree = null)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, degreeCanNotBeNull)
    }

    @Test
    fun latitudeDegreeLowerThan0ThrowsException() {
        givenALatitude(degree = -1)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, degreeCanNotBeLowerThan0)
    }

    @Test
    fun latitudeDegreeHigherThan90ThrowsException() {
        givenALatitude(degree = 91)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, degreeCanNotBeHigherThan90ForLatitude)
    }

    @Test
    fun latitudeMinuteNullThrowsException() {
        givenALatitude(minute = null)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, minuteCanNotBeNull)
    }

    @Test
    fun latitudeMinuteLowerThan0ThrowsException() {
        givenALatitude(minute = -1)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, minuteCanNotLowerThanZero)
    }

    @Test
    fun latitudeMinuteHigherOrEqualsThan60ThrowsException() {
        givenALatitude(minute = 60)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, minuteCanNotHigherOrEqualsThanSixteen)
    }

    @Test
    fun secondWithNullValueThrowsException() {
        givenALatitude(second = null)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, secondCanNotBeNull)
    }

    @Test
    fun latitudeSecondsLowerThan0ThrowsException() {
        givenALatitude(second = -0.01f)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, secondCanNotBeLowerThanZero)
    }

    @Test
    fun latitudeSecondsHigherOrEqualsThan60ThrowsException() {
        givenALatitude(second = 60f)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, secondCanNotBeHigherOrEqualsThanSixteen)
    }

    @Test
    fun latitudeWithNullCardinalPointThrowsException() {
        givenALatitude(cardinalPoint = null)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, latitudeCardinalPointCanNotBeNull)
    }

    @Test
    fun latitudeWithEastCardinalPointThrowsException() {
        givenALatitude(cardinalPoint = CardinalPoint.EAST)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, latitudeCardinalPointCanNotBeEast)
    }

    @Test
    fun latitudeWithWestCardinalPointThrowsException() {
        givenALatitude(cardinalPoint = CardinalPoint.WEST)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, latitudeCardinalPointCanNotBeWest)
    }

    @Test
    fun longitudeDegreeNullThrowsException() {
        givenALongitude(degree = null)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeDegreeCanNotBeNull)
    }

    @Test
    fun longitudeDegreeLowerThan0ThrowsException() {
        givenALongitude(degree = -1)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeDegreeCanNotLowerThanZero)
    }

    @Test
    fun longitudeDegreeHigherThan180ThrowsException() {
        givenALongitude(degree = 180)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeDegreeCanNotHigherThanOneHundredEighteen)
    }

    @Test
    fun longitudeMinuteNullThrowsException() {
        givenALongitude(minute = null)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeMinuteCanNotBeNull)
    }

    @Test
    fun longitudeMinuteLowerThan0ThrowsException() {
        givenALongitude(minute = -1)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeMinuteCanNotLower0)
    }

    @Test
    fun longitudeMinuteHigherOrEqualsThan60ThrowsException() {
        givenALongitude(minute = 60)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeMinuteCanNotHigherOrEqualsThan60)
    }

    @Test
    fun longitudeSecondNullThrowsException() {
        givenALongitude(second = null)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeSecondCanNotNull)
    }

    @Test
    fun longitudeSecondsLowerThan0ThrowsException() {
        givenALongitude(second = -0.1f)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeSecondCanNotLowerThan0)
    }

    @Test
    fun longitudeSecondsHigherOrEqualsThan60ThrowsException() {
        givenALongitude(second = 60f)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeSecondCanNotHigherOrEqualsThan60)
    }

    @Test
    fun longitudeWithNullCardinalPointThrowsException() {
        givenALongitude(cardinalPoint = null)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeCardinalPointCanNotBeNull)
    }

    @Test
    fun longitudeWithNorthCardinalPointThrowsException() {
        givenALongitude(cardinalPoint = CardinalPoint.NORTH)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeCardinalPointCanNotBeNorth)
    }

    @Test
    fun longitudeWithSouthCardinalPointThrowsException() {
        givenALongitude(cardinalPoint = CardinalPoint.SOUTH)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeCardinalPointCanNotBeSouth)
    }

    private fun thenExpectedExceptionWhenTryValidate(validCoordinate: () -> Unit, message: String) {
        validCoordinate.invoke()
        givenACoordinates()

        whenTryValidate()

        thenThrowsExceptionWithMessage(message)
    }

    private fun thenThrowsExceptionWithMessage(message: String) {
        Assert.assertNotNull(exception)
        Assert.assertEquals(message, exception?.message)
    }

    private fun givenACoordinates() {
        coordinatesDto = CoordinatesDto(latitude, longitude)
    }


    private fun givenALongitude(degree: Int? = this.degree, minute: Int? = this.minute,
                                second: Float? = this.second, cardinalPoint: CardinalPoint? = east) {
        longitude = CoordinateDto(degree, minute, second, cardinalPoint)
    }

    private fun givenALatitude(degree: Int? = this.degree, minute: Int? = this.minute,
                               second: Float? = this.second, cardinalPoint: CardinalPoint? = south) {
        latitude = CoordinateDto(degree, minute, second, cardinalPoint)
    }

    private fun whenTryValidate() {
        try {
            CoordinateValidator.validate(coordinatesDto)
        } catch (e: RuntimeException) {
            exception = e
        }
    }
}