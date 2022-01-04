package com.hyecheon.springaop

import com.hyecheon.springaop.order.OrderRepository
import com.hyecheon.springaop.order.OrderService
import com.hyecheon.springaop.order.aop.AspectV4Pointcut
import com.hyecheon.springaop.order.aop.AspectV5Order
import com.hyecheon.springaop.order.aop.AspectV6Advice
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import java.lang.IllegalStateException

//@Import(value = [AspectV1::class])
//@Import(value = [AspectV2::class])
//@Import(value = [AspectV3::class])
//@Import(value = [AspectV4Pointcut::class])
//@Import(value = [AspectV5Order.LogAspect::class, AspectV5Order.TxAspect::class])
@Import(value = [AspectV6Advice::class])
@SpringBootTest
class SpringAopApplicationTests {
	private val log = KotlinLogging.logger {}

	@Autowired
	lateinit var orderService: OrderService

	@Autowired
	lateinit var orderRepository: OrderRepository


	@DisplayName("1. aopInfo")
	@Test
	internal fun test_1() {
		log.info { "isAopProxy, orderService = ${AopUtils.isAopProxy(orderService)}" }
		log.info { "isAopProxy, orderRepository = ${AopUtils.isAopProxy(orderRepository)}" }
	}


	@DisplayName("2. 성공")
	@Test
	internal fun test_2() {
		orderService.orderItem("itemA")
	}

	@DisplayName("3. 예외")
	@Test
	internal fun test_3() {
		Assertions.assertThatThrownBy {
			orderService.orderItem("ex")
		}.isInstanceOf(IllegalStateException::class.java)
	}
}
