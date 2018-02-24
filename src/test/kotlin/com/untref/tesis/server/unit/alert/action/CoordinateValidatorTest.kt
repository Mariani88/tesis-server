package com.untref.tesis.server.unit.alert.action

import com.untref.tesis.server.alert.action.CoordinatesActionData
import com.untref.tesis.server.alert.action.validator.*
import com.untref.tesis.server.alert.domain.CardinalPoint
import com.untref.tesis.server.builders.*
import com.untref.tesis.server.resource.dto.CoordinateDto
import com.untref.tesis.server.resource.dto.CoordinatesDto
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CoordinateValidatorTest {

    private lateinit var coordinatesDto: CoordinatesDto
    private lateinit var validCoordinates: CoordinatesActionData
    private var exception: Exception? = null
    private var latitude: CoordinateDto? = null
    private var longitude: CoordinateDto? = null
    private lateinit var coordinatesValidator: CoordinateValidator

    @Before
    fun setUp() {
        coordinatesValidator = CoordinateValidator(CoordinateDtoPropertyValidator())
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
    fun latitudeMinuteNullThrowsException() {
        givenALatitude(minute = null)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, minuteCanNotBeNull)
    }

    @Test
    fun secondWithNullValueThrowsException() {
        givenALatitude(second = null)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, secondCanNotBeNull)
    }

    @Test
    fun latitudeWithNullCardinalPointThrowsException() {
        givenALatitude(cardinalPoint = null)
        thenExpectedExceptionWhenTryValidate({ givenALongitude() }, latitudeCardinalPointCanNotBeNull)
    }

    @Test
    fun longitudeDegreeNullThrowsException() {
        givenALongitude(degree = null)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeDegreeCanNotBeNull)
    }

    @Test
    fun longitudeMinuteNullThrowsException() {
        givenALongitude(minute = null)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeMinuteCanNotBeNull)
    }

    @Test
    fun longitudeSecondNullThrowsException() {
        givenALongitude(second = null)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeSecondCanNotNull)
    }

    @Test
    fun longitudeWithNullCardinalPointThrowsException() {
        givenALongitude(cardinalPoint = null)
        thenExpectedExceptionWhenTryValidate({ givenALatitude() }, longitudeCardinalPointCanNotBeNull)
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
        val latitude = validCoordinates.latitudeActionData
        val longitude = validCoordinates.longitudeActionData
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
                                second: Double? = defaultSecond, cardinalPoint: CardinalPoint? = east) {
        longitude = createLongitudeActionData(degree, minute, second, cardinalPoint)
    }

    private fun givenALatitude(degree: Int? = defaultDegree, minute: Int? = defaultMinute,
                               second: Double? = defaultSecond, cardinalPoint: CardinalPoint? = south) {
        latitude = createLatitudeActionData(degree, minute, second, cardinalPoint)
    }

    private fun whenTryValidate() {
        try {
            validCoordinates = coordinatesValidator.validate(coordinatesDto)
        } catch (e: RuntimeException) {
            exception = e
        }
    }
}