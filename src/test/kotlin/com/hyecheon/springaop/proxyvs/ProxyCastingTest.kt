package com.hyecheon.springaop.proxyvs

import com.hyecheon.springaop.member.MemberService
import com.hyecheon.springaop.member.MemberServiceImpl
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.aop.framework.ProxyFactory

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */

class ProxyCastingTest {
	private val log = KotlinLogging.logger {}

	@Test
	fun jdkProxy() {
		val target = MemberServiceImpl()
		val proxyFactory = ProxyFactory(target)
		proxyFactory.isProxyTargetClass = false

		//프록시를 인터페이스로 캐스팅
		val memberService = proxyFactory.proxy as MemberService

		//JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패
		Assertions.assertThatThrownBy {
			val memberServiceImpl = proxyFactory.proxy as MemberServiceImpl
		}
	}

	@Test
	fun cglibProxy() {
		val target = MemberServiceImpl()
		val proxyFactory = ProxyFactory(target)
		proxyFactory.isProxyTargetClass = true

		//프록시를 인터페이스로 캐스팅
		val memberService = proxyFactory.proxy as MemberService

		//cglib 프록시를 구현 클래스로 캐스팅 시도 성공
		val memberServiceImpl = proxyFactory.proxy as MemberServiceImpl

	}
}