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
class CallServiceV2(
//	private val applicationContext: ApplicationContext
	private val callServiceProvider: ObjectProvider<CallServiceV2>
) {
	private val log = KotlinLogging.logger {}


	fun external() = run {
		log.info { "call external" }
//		val callServiceV2 = applicationContext.getBean(CallServiceV2::class.java)
		val callServiceV2 = callServiceProvider.`object`
		callServiceV2.internal()
	}

	fun internal() = run {
		log.info { "call internal" }
	}
}