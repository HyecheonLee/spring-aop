package com.hyecheon.springaop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.core.annotation.Order

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/05
 */
private val log = KotlinLogging.logger {}

class AspectV5Order {

	@Aspect
	@Order(2)
	class LogAspect {
		@Around("com.hyecheon.springaop.order.aop.Pointcuts.allOrder()")
		fun doLog(joinPoint: ProceedingJoinPoint) = run {
			log.info { "[log] ${joinPoint.signature}" }
			joinPoint.proceed()
		}
	}

	@Aspect
	@Order(1)
	class TxAspect {
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
	}
}