package com.untref.tesis.server.action

import com.untref.tesis.server.domain.Coordinates
import com.untref.tesis.server.domain.DetectionMethod

class ReceiveAlertActionData(val coordinates: Coordinates, val detectionMethods: List<DetectionMethod>,
                             val temperature: Float, val gas: Float)