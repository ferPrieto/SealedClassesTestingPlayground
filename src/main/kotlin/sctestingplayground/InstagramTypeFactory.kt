package sctestingplayground

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation

internal class InstagramTypeFactory : DefaultTypeFactory() {
    override fun KParameter.createArgument(): Any? =
            when (type.classifier) {
                Int::class -> 0
                Byte::class -> 0.toByte()
                Short::class -> 0.toShort()
                String::class -> ""
                Float::class -> 0f
                Long::class -> 0L
                List::class -> emptyList<String>()
                else -> if ((type.classifier as KClass<out Any>).findAnnotation<InstagramErrorCode>() != null) ErrorCode.TimeOut else null
            }
}

@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class InstagramErrorCode