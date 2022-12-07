package com.example.nbcu_ioc_showcase.mock_classes

import java.util.*

class GuidStore {
    private lateinit var uuid: UUID

    init {
        uuid = UUID.randomUUID()
    }

    fun getUuid(): UUID{
        return uuid
    }
}