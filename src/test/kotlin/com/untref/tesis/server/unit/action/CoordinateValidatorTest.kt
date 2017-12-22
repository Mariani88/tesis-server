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
    private lateinit var exception: Exception
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
        givenALongitude()
        givenACoordinates()

        whenTryValidate()

        thenThrowsExceptionWithMessage(degreeCanNotBeNull)
    }

    @Test
    fun latitudeDegreeLowerThan0ThrowsException() {
        givenALatitude(degree = -1)
        givenALongitude()
        givenACoordinates()

        whenTryValidate()

        thenThrowsExceptionWithMessage(degreeCanNotBeLowerThan0)
    }

    /*@Test
    fun latitudeDegreeHigherThan90ThrowsException(){
        fail()
    }

    @Test
    fun latitudeMinuteLowerThan0ThrowsException(){
        fail()
    }

    @Test
    fun latitudeMinuteHigherOrEqualsThan60ThrowsException(){
        fail()
    }

    @Test
    fun latitudeSecondsLowerThan0ThrowsException(){
        fail()
    }

    @Test
    fun latitudeSecondsHigherOrEqualsThan60ThrowsException(){
        fail()
    }

    @Test
    fun longitudeDegreeLowerThan0ThrowsException(){
        fail()
    }

    @Test
    fun longitudeDegreeHigherThan180ThrowsException(){
        fail()
    }

    @Test
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
        Assert.assertEquals(message, exception.message)
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