package com.hyecheon.springaop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/05
 */
@Aspect
class AspectV2 {
	private val log = KotlinLogging.logger {}

	@Pointcut("execution(* com.hyecheon.springaop.order..*(..))")
	private fun allOrder() {
	}

	@Around("allOrder()")
	fun doLog(joinPoint: ProceedingJoinPoint) = run {
		log.info { "[log] ${joinPoint.signature}" }
		joinPoint.proceed()
	}
}