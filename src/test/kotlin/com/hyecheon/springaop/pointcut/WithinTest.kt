package com.hyecheon.springaop.pointcut

import com.hyecheon.springaop.member.MemberServiceImpl
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import java.lang.reflect.Method

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
class WithinTest {

	private val log = KotlinLogging.logger {}

	val pointcut = AspectJExpressionPointcut()
	lateinit var helloMethod: Method

	@BeforeEach
	internal fun setUp() {
		helloMethod = MemberServiceImpl::class.java.getMethod("hello", String::class.java)
	}


	@DisplayName("1. withinExact")
	@Test
	internal fun test_1() {
		pointcut.expression = "within(com.hyecheon.springaop.member.MemberServiceImpl)"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}


	@DisplayName("2. withinStar")
	@Test
	internal fun test_2() {
		pointcut.expression = "within(com.hyecheon.springaop.member.*Service*)"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}

	@DisplayName("3. withinSubPackage")
	@Test
	internal fun test_3() {
		pointcut.expression = "within(com.hyecheon.springaop..*)"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}

	@DisplayName("4. 타켓의 타입에만 직업 적용, 인터페이스를 선정하면 안된다.")
	@Test
	internal fun test_4() {
		pointcut.expression = "within(com.hyecheon.springaop.member.MemberService)"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
	}

	@DisplayName("5. execution은 타입 기반, 인터페이스를 선정 가능.")
	@Test
	internal fun test_5() {
		pointcut.expression = "execution(* com.hyecheon.springaop.member.MemberService.*(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}


}