package com.hyecheon.springaop.order

import mu.KotlinLogging
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/05
 */

@Service
class OrderService(
	private val orderRepository: OrderRepository
) {
	private val log = KotlinLogging.logger {}

	fun orderItem(itemId: String) = run {
		log.info { "[orderService] 실행" }
		orderRepository.save(itemId)
	}

}