package com.untref.tesis.server.unit.action

import com.untref.tesis.server.action.CoordinateValidator
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

    @JvmField
    @Rule
    val expectedException: ExpectedException = ExpectedException.none()

    //TODO AGREGAR TESTS PARA NO NULOS

    @Test
    fun latitudeCanNotBeNull(){
        val latitude = null
        val longitude = CoordinateDto(30, 50, 23.4f, CardinalPoint.EAST)
        coordinatesDto = CoordinatesDto(latitude, longitude)

        try{
            CoordinateValidator.validate(coordinatesDto)
        }catch (e: RuntimeException){
            exception = e
        }

        Assert.assertNotNull(exception)
        Assert.assertEquals("Degree can not null", exception.message)
    }

    @Test
    fun latitudeDegreeWithNullValueThrowsException(){
        val latitude = CoordinateDto(null, 50, 23.4f, CardinalPoint.SOUTH)
        val longitude = CoordinateDto(30, 50, 23.4f, CardinalPoint.EAST)
        coordinatesDto = CoordinatesDto(latitude, longitude)

        try{
            CoordinateValidator.validate(coordinatesDto)
        }catch (e: RuntimeException){
            exception = e
        }

        Assert.assertNotNull(exception)
        Assert.assertEquals("Degree can not null", exception.message)
    }



    @Test
    fun latitudeDegreeLowerThan0ThrowsException(){

        val latitude = CoordinateDto(-1, 50, 23.4f, CardinalPoint.SOUTH)
        val longitude = CoordinateDto(30, 50, 23.4f, CardinalPoint.EAST)
        coordinatesDto = CoordinatesDto(latitude, longitude)

        try{
            CoordinateValidator.validate(coordinatesDto)
        }catch (e: RuntimeException){
            exception = e
        }

        Assert.assertNotNull(exception)
        Assert.assertEquals("Degree can not lower than 0", exception.message)
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
}