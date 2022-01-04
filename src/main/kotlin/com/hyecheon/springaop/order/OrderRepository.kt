package com.hyecheon.springaop.order

import mu.KotlinLogging
import org.springframework.stereotype.Repository

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/05
 */

@Repository
class OrderRepository {
	private val log = KotlinLogging.logger {}

	fun save(itemId: String) = run {
		log.info { "[orderRepository] 실행" }

		if (itemId == "ex") {
			throw IllegalStateException("예외 발생!")
		}
		"ok"
	}
}