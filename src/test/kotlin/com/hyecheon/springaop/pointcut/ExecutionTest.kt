package com.hyecheon.springaop.pointcut

import com.hyecheon.springaop.member.MemberServiceImpl
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import java.lang.reflect.Method
import kotlin.reflect.full.functions

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/07
 */

class ExecutionTest {
	private val log = KotlinLogging.logger {}

	val pointcut = AspectJExpressionPointcut()
	lateinit var helloMethod: Method

	@BeforeEach
	internal fun setUp() {

		helloMethod = MemberServiceImpl::class.java.getMethod("hello", String::class.java)

	}


	@DisplayName("1. ")
	@Test
	internal fun test_1() {
		log.info { "helloMethod=${helloMethod}" }
	}


	@DisplayName("2. exactMatch")
	@Test
	internal fun test_2() {
		pointcut.expression =
			"execution(public String com.hyecheon.springaop.member.MemberServiceImpl.hello(java.lang.String))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}


	@DisplayName("3. allMatch")
	@Test
	internal fun test_3() {
		pointcut.expression = "execution(* *(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}


	@DisplayName("4. nameMatch")
	@Test
	internal fun test_4() {
		pointcut.expression = "execution(* hello(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}

	@DisplayName("5. nameMatchStart1")
	@Test
	internal fun test_5() {
		pointcut.expression = "execution(* hel*(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}

	@DisplayName("6. nameMatchStart2")
	@Test
	internal fun test_6() {
		pointcut.expression = "execution(* *el*(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}


	@DisplayName("7. nameMatchFalse")
	@Test
	internal fun test_7() {
		pointcut.expression = "execution(* nono(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
	}

	@DisplayName("8. packageExactMatch1")
	@Test
	internal fun test_8() {
		pointcut.expression = "execution(* com.hyecheon.springaop.member.MemberServiceImpl.hello(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}


	@DisplayName("9. packageExactMatch2")
	@Test
	internal fun test_9() {
		pointcut.expression = "execution(* com.hyecheon.springaop.member.*.*(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}

	@DisplayName("10. packageExactMatchFalse")
	@Test
	internal fun test_10() {
		pointcut.expression = "execution(* com.hyecheon.springaop.*.*(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
	}

	@DisplayName("11. packageExactMatchSubPackage1")
	@Test
	internal fun test_11() {
		pointcut.expression = "execution(* com.hyecheon.springaop.member..*.*(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}

	@DisplayName("12. packageExactMatchSubPackage2")
	@Test
	internal fun test_12() {
		pointcut.expression = "execution(* com.hyecheon.springaop..*.*(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}

	@DisplayName("13. typeExactMatch")
	@Test
	internal fun test_13() {
		pointcut.expression = "execution(* com.hyecheon.springaop.member.MemberServiceImpl.*(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}

	@DisplayName("14. typeMatchSuperType")
	@Test
	internal fun test_14() {
		pointcut.expression = "execution(* com.hyecheon.springaop.member.MemberService.*(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}


	@DisplayName("15. typeMatchInternal")
	@Test
	internal fun test_15() {
		pointcut.expression = "execution(* com.hyecheon.springaop.member.MemberServiceImpl.*(..))"
		val internalMethod = MemberServiceImpl::class.java.getMethod("internal", String::class.java)
		Assertions.assertThat(pointcut.matches(internalMethod, MemberServiceImpl::class.java)).isTrue()
	}

	@DisplayName("16. typeMatchNoSuperTypeMethodFalse")
	@Test
	internal fun test_16() {
		pointcut.expression = "execution(* com.hyecheon.springaop.member.MemberService.*(..))"
		val internalMethod = MemberServiceImpl::class.java.getMethod("internal", String::class.java)
		Assertions.assertThat(pointcut.matches(internalMethod, MemberServiceImpl::class.java)).isFalse
	}


	@DisplayName("17. argsMatch")
	@Test
	internal fun test_17() {
		pointcut.expression = "execution(* *(String))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue()
	}

	@DisplayName("18. argsMatchNoArgs")
	@Test
	internal fun test_18() {
		pointcut.expression = "execution(* *())"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse()
	}


	@DisplayName("19. argsMatchStar")
	@Test
	internal fun test_19() {
		pointcut.expression = "execution(* *(*))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue()
	}

	@DisplayName("20. argsMatchAll")
	@Test
	internal fun test_20() {
		pointcut.expression = "execution(* *(..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue()
	}


	@DisplayName("21. argsMatchComplex")
	@Test
	internal fun test_21() {
		pointcut.expression = "execution(* *(String,..))"
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue()
	}
}