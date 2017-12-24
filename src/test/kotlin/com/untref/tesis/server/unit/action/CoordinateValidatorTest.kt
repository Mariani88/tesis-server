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
    //TODO AGREGAR TESTS PARA NO NULOS

    @Test
    fun latitudeCanNotBeNull() {
        givenALongitude()
        givenACoordinates()

        whenTryValidate()

        thenThrowsExceptionWithMessage(latitudeCanNotBeNull)
    }

    @Test
    fun longitudeCanNotBeNull() {
        givenALatitude()
        givenACoordinates()

        whenTryValidate()

        thenThrowsExceptionWithMessage(longitudeCanNotBeNull)
    }

    @Test
    fun latitudeDegreeWithNullValueThrowsException() {
        givenALatitude(degree = null)
        thenExpectedExceptionWhenTryValidate(degreeCanNotBeNull)
    }

    @Test
    fun latitudeDegreeLowerThan0ThrowsException() {
        givenALatitude(degree = -1)
        thenExpectedExceptionWhenTryValidate(degreeCanNotBeLowerThan0)
    }

    @Test
    fun latitudeDegreeHigherThan90ThrowsException() {
        givenALatitude(degree = 91)
        thenExpectedExceptionWhenTryValidate(degreeCanNotBeHigherThan90ForLatitude)
    }

    @Test
    fun latitudeMinuteNullThrowsException() {
        givenALatitude(minute = null)
        thenExpectedExceptionWhenTryValidate(minuteCanNotBeNull)
    }

    @Test
    fun latitudeMinuteLowerThan0ThrowsException() {
        givenALatitude(minute = -1)
        thenExpectedExceptionWhenTryValidate(minuteCanNotLowerThanZero)
    }

    @Test
    fun latitudeMinuteHigherOrEqualsThan60ThrowsException() {
        givenALatitude(minute = 60)
        thenExpectedExceptionWhenTryValidate(minuteCanNotHigherOrEqualsThanSixteen)
    }

    @Test
    fun secondWithNullValueThrowsException() {
        givenALatitude(second = null)
        thenExpectedExceptionWhenTryValidate(secondCanNotBeNull)
    }

    @Test
    fun latitudeSecondsLowerThan0ThrowsException() {
        givenALatitude(second = -0.01f)
        thenExpectedExceptionWhenTryValidate(secondCanNotBeLowerThanZero)
    }

    @Test
    fun latitudeSecondsHigherOrEqualsThan60ThrowsException() {
        givenALatitude(second = 60f)
        thenExpectedExceptionWhenTryValidate(secondCanNotBeHigherOrEqualsThanSixteen)
    }


    private fun thenExpectedExceptionWhenTryValidate(message: String){
        givenALongitude()
        givenACoordinates()

        whenTryValidate()

        thenThrowsExceptionWithMessage(message)
    }

    @Test
    fun longitudeDegreeNullThrowsException() {
        givenALongitude(degree = null)
        givenALatitude()
        givenACoordinates()

        whenTryValidate()
        thenThrowsExceptionWithMessage(longitudeDegreeCanNotBeNull)
    }

    @Test
    fun longitudeDegreeLowerThan0ThrowsException() {
        givenALongitude(degree = -1)
        givenALatitude()
        givenACoordinates()

        whenTryValidate()
        thenThrowsExceptionWithMessage(longitudeDegreeCanNotLowerThanZero)
    }

    @Test
    fun longitudeDegreeHigherThan180ThrowsException() {
        givenALongitude(degree = 180)
        givenALatitude()
        givenACoordinates()

        whenTryValidate()
        thenThrowsExceptionWithMessage(longitudeDegreeCanNotHigherThanOneHundredEighteen)
    }

    /*@Test
    fun longitudeMinuteLowerThan0ThrowsException(){
        fail()
    }

    @Test
    fun longitudeMinuteHigherOrEqualsThan60ThrowsException(){
        fail()
    }

    @Test
    fun longitudeSecondsLowerThan0ThrowsException(){
        fail()
    }

    @Test
    fun longitudeSecondsHigherOrEqualsThan60ThrowsException(){
        fail()
    }

    @Test
    fun latitudeWithEastCardinalPointThrowsException(){
        fail()
    }

    @Test
    fun latitudeWithWestCardinalPointThrowsException(){
        fail()
    }

    @Test
    fun longitudeWithNorthCardinalPointThrowsException(){
        fail()
    }

    @Test
    fun longitudeWithSouthCardinalPointThrowsException(){
        fail()
    }*/

    private fun thenThrowsExceptionWithMessage(message: String) {
        Assert.assertNotNull(exception)
        Assert.assertEquals(message, exception?.message)
    }

    private fun givenACoordinates() {
        coordinatesDto = CoordinatesDto(latitude, longitude)
    }


    private fun givenALongitude(degree: Int? = this.degree, minute: Int? = this.minute,
                                second: Float? = this.second, cardinalPoint: CardinalPoint = east) {
        longitude = CoordinateDto(degree, minute, second, cardinalPoint)
    }

    private fun givenALatitude(degree: Int? = this.degree, minute: Int? = this.minute,
                               second: Float? = this.second, cardinalPoint: CardinalPoint = south) {
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