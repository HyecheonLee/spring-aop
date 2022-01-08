package com.hyecheon.springaop.proxyvs.code

import com.hyecheon.springaop.member.MemberService
import com.hyecheon.springaop.member.MemberServiceImpl
import mu.KotlinLogging
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
//@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"])
@SpringBootTest(properties = ["spring.aop.proxy-target-class=true"])
@Import(ProxyDIAspect::class)
class ProxyIDTest {
	private val log = KotlinLogging.logger {}

	@Autowired
	lateinit var memberService: MemberService

	@Autowired
	lateinit var memberServiceImpl: MemberServiceImpl


	@DisplayName("1. ")
	@Test
	internal fun test_1() {
		log.info { "memberService class= ${memberService.javaClass}" }
		log.info { "memberServiceImpl class=${memberServiceImpl.javaClass}" }
		memberServiceImpl.hello("hello")
	}
}