package com.hyecheon.springaop.proxyvs.code

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
class ProxyDIAspect {
	private val log = KotlinLogging.logger {}

	@Before("execution(* com.hyecheon.springaop..*.*(..))")
	fun doTrace(joinPoint: JoinPoint) = run {
		log.info { "[proxyDIAdvice] ${joinPoint.signature}" }
	}
}