package com.hyecheon.springaop.internalcall

import mu.KotlinLogging
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
@Component
class CallServiceV3(
	private val internalService: InternalService
) {
	private val log = KotlinLogging.logger {}


	fun external() = run {
		log.info { "call external" }
		internalService.internal()
	}

	fun internal() = run {
		internalService.internal()
	}
}