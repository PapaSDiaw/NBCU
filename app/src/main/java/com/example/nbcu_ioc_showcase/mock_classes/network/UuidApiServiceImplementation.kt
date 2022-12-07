package com.example.nbcu_ioc_showcase.mock_classes.network

import java.util.*

class UuidApiServiceImplementation(): UuidApiService {
    override fun getUuid(): UUID {
        return UUID.randomUUID()
    }
}