package com.hyecheon.springaop.internalcall

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
@Component
class CallServiceV1 {
	private val log = KotlinLogging.logger {}

	var callServiceV1: CallServiceV1? = null
		set(@Autowired value) {
			field = value
		}

	fun external() = run {
		log.info { "call external" }
		this.callServiceV1?.internal()
	}

	fun internal() = run {
		log.info { "call internal" }
	}
}