package com.hyecheon.springaop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/05
 */
@Aspect
class AspectV1 {
	private val log = KotlinLogging.logger {}

	@Around("execution(* com.hyecheon.springaop.order..*(..))")
	fun doLog(joinPoint: ProceedingJoinPoint) = run {
		log.info { "[log] ${joinPoint.signature}" }
		joinPoint.proceed()
	}
}