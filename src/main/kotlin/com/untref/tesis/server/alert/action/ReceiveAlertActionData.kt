package com.untref.tesis.server.alert.action

import com.untref.tesis.server.alert.domain.DetectionMethod

class ReceiveAlertActionData(val coordinates: CoordinatesActionData, val detectionMethods: List<DetectionMethod>,
                             val temperature: Float, val gas: Float)