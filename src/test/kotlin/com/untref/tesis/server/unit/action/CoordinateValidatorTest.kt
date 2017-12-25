package com.untref.tesis.server.unit.action

import com.untref.tesis.server.action.validator.*
import com.untref.tesis.server.builders.*
import com.untref.tesis.server.domain.CardinalPoint
import com.untref.tesis.server.domain.Coordinates
import com.untref.tesis.server.resource.dto.CoordinateDto
import com.untref.tesis.server.resource.dto.CoordinatesDto
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CoordinateValidatorTest {

    private lateinit var coordinatesDto: CoordinatesDto
    private lateinit var validCoordinates: Coordinates
    private var exception: Exception? = null
    private var latitude: CoordinateDto? = null
    private var longitude: CoordinateDto? = null
    private lateinit var coordinatesValidator: CoordinateValidator

    @Before
    fun setUp() {
        coordinatesValidator = CoordinateValidator(LatitudeValidator(), LongitudeValidator())
    }

    @Test
    fun latitudeCanNotBeNull() {
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, latitudeCanNotBeNull)
    }

    @Test
    fun longitudeCanNotBeNull() {
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeCanNotBeNull)
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
    fun latitudeDegreeHigherOrEqualsThan90ThrowsException() {
        givenALatitude(degree = 90)
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
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, secondCanNotBeHigherOrEqualsThanSixty)
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
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeMinuteCanNotLowerZero)
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

    @Test
    fun validValuesShouldReturnCoordinates() {
        givenALatitude()
        givenALongitude()
        givenACoordinates()
        whenTryValidate()
        thenRetrieveCoordinates()
    }

    private fun thenRetrieveCoordinates() {
        val latitude = validCoordinates.latitude
        val longitude = validCoordinates.longitude
        Assert.assertEquals(this.latitude?.degree, latitude.degree)
        Assert.assertEquals(this.latitude?.minute, latitude.minute)
        Assert.assertEquals(this.latitude?.second, latitude.second)
        Assert.assertEquals(this.latitude?.cardinalPoint, latitude.cardinalPoint)
        Assert.assertEquals(this.longitude?.degree, longitude.degree)
        Assert.assertEquals(this.longitude?.minute, longitude.minute)
        Assert.assertEquals(this.longitude?.second, longitude.second)
        Assert.assertEquals(this.longitude?.cardinalPoint, longitude.cardinalPoint)
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

    private fun givenALongitude(degree: Int? = defaultDegree, minute: Int? = defaultMinute,
                                second: Float? = defaultSecond, cardinalPoint: CardinalPoint? = east) {
        longitude = createLongitude(degree, minute, second, cardinalPoint)
    }

    private fun givenALatitude(degree: Int? = defaultDegree, minute: Int? = defaultMinute,
                               second: Float? = defaultSecond, cardinalPoint: CardinalPoint? = south) {
        latitude = createLatitude(degree, minute, second, cardinalPoint)
    }

    private fun whenTryValidate() {
        try {
            validCoordinates = coordinatesValidator.validate(coordinatesDto)
        } catch (e: RuntimeException) {
            exception = e
        }
    }
}