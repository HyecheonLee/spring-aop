package com.hyecheon.springaop.member.annotation

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/07
 */

@Target(allowedTargets = [AnnotationTarget.FUNCTION])
@Retention(value = AnnotationRetention.RUNTIME)
annotation class MethodAop(
	val value: String
) {
}
