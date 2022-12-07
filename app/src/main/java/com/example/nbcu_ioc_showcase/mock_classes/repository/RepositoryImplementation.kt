package com.example.nbcu_ioc_showcase.mock_classes.repository

import com.example.nbcu_ioc_showcase.mock_classes.GuidStore
import com.example.nbcu_ioc_showcase.mock_classes.network.UuidApiService

class RepositoryImplementation (val uuidApiService: UuidApiService, val guidStore: GuidStore):
    Repository {
    override fun printUuidUsingUuidApiServices() {
        println(uuidApiService.getUuid())
    }

    override fun printUuidUsingGuidStore() {
        println(guidStore.getUuid())
    }

}