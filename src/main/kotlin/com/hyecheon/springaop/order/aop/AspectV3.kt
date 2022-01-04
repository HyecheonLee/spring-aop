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
class AspectV3 {
	private val log = KotlinLogging.logger {}

	@Pointcut("execution(* com.hyecheon.springaop.order..*(..))")
	private fun allOrder() {
	}


	@Pointcut("execution(* *..*Service.*(..))")
	private fun allService() {
	}


	@Around("allOrder()")
	fun doLog(joinPoint: ProceedingJoinPoint) = run {
		log.info { "[log] ${joinPoint.signature}" }
		joinPoint.proceed()
	}

	@Around("allOrder() && allService()")
	fun doTransaction(joinPoint: ProceedingJoinPoint) = run {
		try {
			log.info { "[트랜잭션 시작] ${joinPoint.signature}" }
			val result = joinPoint.proceed()
			log.info { "[트랜잭션 커밋] ${joinPoint.signature}" }
			return@run result
		} catch (e: Exception) {
			log.info { "[트랜잭션 롤백] ${joinPoint.signature}" }
			throw e
		} finally {
			log.info { "[리소스 릴리즈] ${joinPoint.signature}" }
		}
	}
}