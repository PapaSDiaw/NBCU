package com.example.nbcu_ioc_showcase.nbcu_ioc

import kotlin.reflect.KClass

/**
 * This will be the Helper data class that stores information about the objects created
 *
 * implementationType - implementation type
 * implementation     - implementation class
 * lifetime           - decides if we store the object as singleton or as transient
 * clazz              - the actual implementation
 */
data class ServiceDescriptor (
    var implementationType: KClass<*>?,
    var implementation: Any?,
    var lifetime: ServiceLifetime,
    var clazz: KClass<*>? = implementation?.let{it::class}
){

}