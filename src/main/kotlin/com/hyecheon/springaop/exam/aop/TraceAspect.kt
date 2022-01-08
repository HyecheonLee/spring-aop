package com.hyecheon.springaop.exam.aop

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
private val log = KotlinLogging.logger {}

@Aspect
class TraceAspect {
	@Before("@annotation(com.hyecheon.springaop.exam.annotation.Trace)")
	fun doTrace(joinPoint: JoinPoint) = run {
		val args = joinPoint.args
		log.info { "[trace] ${joinPoint.signature} args=${args.joinToString(",", "[", "]")}" }
	}
}