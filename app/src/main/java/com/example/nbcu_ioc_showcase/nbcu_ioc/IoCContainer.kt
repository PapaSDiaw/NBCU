package com.example.nbcu_ioc_showcase.nbcu_ioc

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * This IoC Container contains the list of registered services.
 * It will provide the functionality to resolve dependencies and injections
 */

class IoCContainer(val serviceDescriptorList:List<ServiceDescriptor>) {

    inline fun <reified T> resolveService(clazz: KClass<*>): T? {

        var descriptor = serviceDescriptorList.firstOrNull {
            it.clazz == clazz
        }

        descriptor ?: throw Exception("Service of type ${T::class} does not exist")

        //if implementation exists then return the implementation
        descriptor.implementation?.let {
            return it as T
        }

        var implementation: T? = null

        //ToDo: Check if type is abstract or interface, then cannot instantiate so throw exception


        //If implementationType != null then instantiate child class
        implementation = descriptor.implementationType?.let {
            makeInstance(it) as T
        }


        //if implementation type is null, then use class type to instantiate
        return implementation ?: descriptor.clazz?.let {
            implementation = construct(it)//makeRandomInstance(it) as T

            if (descriptor.lifetime == ServiceLifetime.SINGLETON) {
                descriptor.implementation = implementation
            }

            return implementation
        }
    }

    inline fun <reified T> resolveService(clazz: KClass<*>, type: T): Any? {
        return resolveService(clazz)
    }

    inline fun <reified T> resolveService(): T? {
        return resolveService<T>(T::class)
    }

    fun <T : Any> construct(kClass: KClass<*>): T {
        return kClass.createInstance() as T
    }

    fun makeInstance(clazz: KClass<*>): Any? {
        val constructor = clazz.constructors
            .minByOrNull {
                it.parameters.size
            } ?: throw Exception()

        val arguments = constructor.parameters
            .map {
                it.type.classifier as KClass<*>
                resolveService(it.type.classifier as KClass<*>, it.type)
            }
            .toTypedArray()
        return constructor.call(*arguments)
    }
}