package com.hyecheon.springaop.internalcall

import com.hyecheon.springaop.internalcall.aop.CallLogAspect
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
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
internal class CallServiceV2Test {
	@Autowired
	lateinit var callServiceV2: CallServiceV2

	@Test
	fun external() {
		callServiceV2.external()
	}

	@Test
	fun internal() {
		callServiceV2.internal()
	}
}