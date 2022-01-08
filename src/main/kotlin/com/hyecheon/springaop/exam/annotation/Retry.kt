package com.hyecheon.springaop.exam.annotation

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
@Target(allowedTargets = [AnnotationTarget.FUNCTION])
@Retention(AnnotationRetention.RUNTIME)
annotation class Retry(
	val value: Int = 3
)
