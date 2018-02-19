package com.untref.tesis.server.notification.dto

import com.fasterxml.jackson.annotation.JsonProperty

//@get is necessary for serialize. if @get is removed, this not work
//see https://github.com/FasterXML/jackson-module-kotlin/issues/36

class NotificationBodyDto(

        @get:JsonProperty("body")
        val text: String?,

        @get:JsonProperty("title")
        val title: String?,

        @get:JsonProperty("myicon")
        val icon: String?
)