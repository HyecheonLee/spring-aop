package com.hyecheon.springaop.exam.aop

import com.hyecheon.springaop.exam.annotation.Retry
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
private val log = KotlinLogging.logger {}

@Aspect
class RetryAspect {
	@Around("@annotation(retry)")
	fun doRetry(joinPoint: ProceedingJoinPoint, retry: Retry): Any? {
		log.info { "[retry] ${joinPoint.signature} args=${retry}" }

		val maxRetry = retry.value
		for (i in 1..(maxRetry + 1)) {
			try {
				log.info { "[retry] try count=${i} / $maxRetry" }
				return joinPoint.proceed()
			} catch (e: Exception) {
				if (i > maxRetry) throw e
			}
		}
		return null
	}
}