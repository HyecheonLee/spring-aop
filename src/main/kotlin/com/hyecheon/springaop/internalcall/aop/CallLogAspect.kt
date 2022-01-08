package com.hyecheon.springaop.internalcall.aop

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
@Aspect
class CallLogAspect {
	private val log = KotlinLogging.logger {}

	@Before("execution(* com.hyecheon.springaop.internalcall..*(..))")
	fun doLog(joinPoint: JoinPoint) = run {
		log.info { "aop = ${joinPoint.signature}" }
	}
}