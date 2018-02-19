package com.untref.tesis.server.alert.action

import com.untref.tesis.server.alert.domain.Coordinates
import com.untref.tesis.server.alert.domain.DetectionMethod

class ReceiveAlertActionData(val coordinates: Coordinates, val detectionMethods: List<DetectionMethod>,
                             val temperature: Float, val gas: Float)