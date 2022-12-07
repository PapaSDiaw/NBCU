package com.example.nbcu_ioc_showcase

import com.example.nbcu_ioc_showcase.mock_classes.GuidStore
import com.example.nbcu_ioc_showcase.mock_classes.datasource.MockDataSource
import com.example.nbcu_ioc_showcase.mock_classes.datasource.MockDataSourceImplementation
import com.example.nbcu_ioc_showcase.mock_classes.network.UuidApiService
import com.example.nbcu_ioc_showcase.mock_classes.network.UuidApiServiceImplementation
import com.example.nbcu_ioc_showcase.mock_classes.repository.Repository
import com.example.nbcu_ioc_showcase.mock_classes.repository.RepositoryImplementation
import com.example.nbcu_ioc_showcase.nbcu_ioc.IoCContainer
import com.example.nbcu_ioc_showcase.nbcu_ioc.IoCServiceCollection

/**
 * Quick program to demonstrate functionality of the IoC
 * initialises the IoC services and provides container
 */

fun main(args: Array<String>) {

    var services = IoCServiceCollection()
    var container = services.generateContainer()

    test1(services, container)
    test2(services, container)
    test3(services, container)
    test4(services, container)

}

/**
 * This test will be using RandomGuidGenerator class and getting a Singleton.
 * it should result in the same uuid.
 */
fun test1(services: IoCServiceCollection, container: IoCContainer) {
    println("=======TEST 1 ======== ")
    services.registerSingleton<GuidStore>()
    var service1 = container.resolveService<GuidStore>()
    var service2 = container.resolveService<GuidStore>()

    println(service1?.getUuid())
    println(service2?.getUuid())

}

/**
 * This test will be using RandomGuidGenerator class and getting a Transient
 * it should result in different uuid
 */
fun test2(services: IoCServiceCollection, container: IoCContainer) {
    println("/n=======TEST 2 ======== ")
    services.registerTransient<GuidStore>()
    var service1 = container.resolveService<GuidStore>()
    var service2 = container.resolveService<GuidStore>()

    println(service1?.getUuid())
    println(service2?.getUuid())

}

/**
 * this test will be using the MockDataSource interface as well as its implementation and getting a Transient
 * it should result in different uuid
 */
fun test3(services: IoCServiceCollection, container: IoCContainer) {
    println("/n=======TEST 3 ======== ")
    services.registerInterface<MockDataSource, MockDataSourceImplementation>()
    var service1 = container.resolveService<MockDataSource>()
    var service2 = container.resolveService<MockDataSource>()

    service1?.printSomeData()
    service2?.printSomeData()
}

/**
 * This test will be using the Repository and its implementation and getting a Transient
 * it should be able to create an object and pass its dependency through the constructor.
 */

fun test4(services: IoCServiceCollection, container: IoCContainer) {
    println("/n=======TEST 4 ======== ")
    services.registerTransient<GuidStore>()
    services.registerInterface<Repository, RepositoryImplementation>()
    services.registerInterface<UuidApiService, UuidApiServiceImplementation>()

    var service1 = container.resolveService<Repository>()

    println("printing using UuidApiService: ${service1?.printUuidUsingUuidApiServices()}")
    println("printing using GuidStore: ${service1?.printUuidUsingGuidStore()}")
}
