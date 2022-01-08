package com.hyecheon.springaop.internalcall

import com.hyecheon.springaop.internalcall.aop.CallLogAspect
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
@Import(value = [CallLogAspect::class])
@SpringBootTest
internal class CallServiceV0Test {
	private val log = KotlinLogging.logger {}

	@Autowired
	lateinit var callServiceV0: CallServiceV0

	@Test
	fun external() = run {
		callServiceV0.external()
	}

	@Test
	fun internal() = run {
		callServiceV0.internal()
	}
}