package com.example.nbcu_ioc_showcase.nbcu_ioc

/**
 * This class will provide the methods needed for initializing the IoCContainer and will allow
 * registration of the objects used by the program
 * it will also generate the container that will be used for resolving the classes when needed.
 * the list stores ServiceDescriptor objects
 */

class IoCServiceCollection {

    var serviceDescriptorList = mutableListOf<ServiceDescriptor>()

    /** registering a Singleton object **/
    fun <TService> registerSingleton(implementation: TService){
        serviceDescriptorList.add(
            ServiceDescriptor(
                implementation = implementation,
                implementationType = null,
                lifetime = ServiceLifetime.SINGLETON
            )
        )
    }

    /** registering a Singleton object using Generics **/
    inline fun<reified T> registerSingleton(){
        serviceDescriptorList.add(
            ServiceDescriptor(
                implementation = null,
                implementationType = null,
                lifetime = ServiceLifetime.SINGLETON,
                clazz = T::class
            )
        )
    }

    /** registering a transient object using Generics **/
    inline fun<reified T> registerTransient(){
        serviceDescriptorList.add(
            ServiceDescriptor(
                implementation = null,
                implementationType = null,
                lifetime = ServiceLifetime.TRANSIENT,
                clazz = T::class
            )
        )
    }

    /** registering an interface and the implementation object **/
    inline fun <reified TService, reified Implementation> registerInterface(){
        serviceDescriptorList.add(
            ServiceDescriptor(
                implementation = null,
                implementationType = Implementation::class,
                lifetime = ServiceLifetime.TRANSIENT,
                clazz = TService::class
            )
        )
    }

    /** Generate IoCContainer and pass it the list of ServiceDescriptors **/
    fun generateContainer(): IoCContainer {
        return IoCContainer(serviceDescriptorList)
    }


}