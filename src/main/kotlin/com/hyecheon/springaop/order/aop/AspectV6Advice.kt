package com.hyecheon.springaop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.springframework.core.annotation.Order

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/05
 */
private val log = KotlinLogging.logger {}

@Aspect
class AspectV6Advice {

	@Around("com.hyecheon.springaop.order.aop.Pointcuts.orderAndService()")
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

	@Before("com.hyecheon.springaop.order.aop.Pointcuts.orderAndService()")
	fun doBefore(joinPoint: JoinPoint) = run {
		log.info { "[before] ${joinPoint.signature}" }
	}

	@AfterReturning("com.hyecheon.springaop.order.aop.Pointcuts.orderAndService()", returning = "result")
	fun doReturn(joinPoint: JoinPoint, result: Any) = run {
		log.info { "[return] ${joinPoint.signature} result=${result}" }
	}

	@AfterThrowing("com.hyecheon.springaop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
	fun doThrowing(joinPoint: JoinPoint, ex: Exception) = run {
		log.info { "[throwing] ${joinPoint.signature} message=${ex.message}" }
	}

	@After("com.hyecheon.springaop.order.aop.Pointcuts.orderAndService()")
	fun doAfter(joinPoint: JoinPoint) = run {
		log.info { "[after] ${joinPoint.signature}" }
	}


}