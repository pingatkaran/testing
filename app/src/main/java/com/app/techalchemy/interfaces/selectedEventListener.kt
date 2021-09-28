package com.app.techalchemy.interfaces

import com.app.techalchemy.model.EventDetails

interface selectedEventListener {
    fun event(event: EventDetails)
}