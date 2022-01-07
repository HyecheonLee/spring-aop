package com.hyecheon.springaop.pointcut

import com.hyecheon.springaop.order.OrderService
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
private val log = KotlinLogging.logger {}

@Import(BeanTest.BeanAspect::class)
@SpringBootTest
class BeanTest {

	@Autowired
	lateinit var orderService: OrderService


	@DisplayName("1. success")
	@Test
	internal fun test_1() {
		orderService.orderItem("itemA")
	}

	@Aspect
	class BeanAspect {
		@Around("bean(orderService) || bean(*Repository)")
		fun doLog(joinPoint: ProceedingJoinPoint): Any? = run {
			log.info { "[bean] ${joinPoint.signature}" }
			joinPoint.proceed()
		}
	}

}