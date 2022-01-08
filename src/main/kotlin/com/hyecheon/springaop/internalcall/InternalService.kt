package com.hyecheon.springaop.internalcall

import mu.KotlinLogging
import org.springframework.stereotype.Component

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
@Component
class InternalService {
	private val log = KotlinLogging.logger {}

	fun internal() = run {
		log.info { "call internal" }
	}
}