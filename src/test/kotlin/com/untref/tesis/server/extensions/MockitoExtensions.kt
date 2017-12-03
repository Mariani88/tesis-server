package com.untref.tesis.server.extensions

import org.mockito.Mockito

class MockitoExtensions {

    companion object {
        fun <T> anyObjectOf(clazz: Class<T>): T {
            return Mockito.any(clazz) as T
        }
    }
}