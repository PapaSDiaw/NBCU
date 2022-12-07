package com.example.nbcu_ioc_showcase.mock_classes.datasource

import java.util.*

class MockDataSourceImplementation: MockDataSource {
    private var uuid: UUID = UUID.randomUUID()

    override fun printSomeData() {
        println("Random UUID: $uuid")
    }

}