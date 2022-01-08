package com.hyecheon.springaop.exam

import com.hyecheon.springaop.exam.aop.RetryAspect
import com.hyecheon.springaop.exam.aop.TraceAspect
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.*
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
@Import(value = [TraceAspect::class, RetryAspect::class])
@SpringBootTest
internal class ExamTest {
	private val log = KotlinLogging.logger {}

	@Autowired
	lateinit var examService: ExamService


	@DisplayName("1. ")
	@Test
	internal fun test_1() {
		for (i in 1..5) {
			log.info { "client request i=$i" }
			examService.request("data$i")
		}
	}
}