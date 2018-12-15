# tesis-server

![Build Status](https://travis-ci.org/Mariani88/tesis-server.svg?branch=master)


- Sobre esta aplicación

Esta aplicación es parte de un sistema de detección de incendios de tiempo real. Consiste en una API Rest, la cual recibe alertas de incendio de una alarma y las envía a Firebase, para que este último las transmita a dispositivos mobile.

La alarma que envía las alertas a esta API Rest se encuentra alojada en el siguiente repositorio: https://github.com/Mariani88/tesis-alarm

La aplicación Android, que recibe las alertas de incendio desde Firebase, se encuentra en el siguiente repositorio: https://github.com/Mariani88/tesis-android


- Dependencias del proyecto

Este proyecto requiere de Java 8 y Gradle 4.1


- Ejecución


Para levantar la API se requiere correr, en el directorio donde está el archivo build.gradle, el siguiente comando:

gradle bootrun
