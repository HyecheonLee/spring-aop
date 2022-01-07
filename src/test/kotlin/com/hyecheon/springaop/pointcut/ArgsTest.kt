package com.hyecheon.springaop.pointcut

import com.hyecheon.springaop.member.MemberServiceImpl
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
class ArgsTest {
	lateinit var helloMethod: Method

	@BeforeEach
	internal fun setUp() {
		helloMethod = MemberServiceImpl::class.java.getMethod("hello", String::class.java)
	}

	fun pointcut(express: String) = run {
		val pointcut = AspectJExpressionPointcut()
		pointcut.expression = express
		pointcut
	}


	@DisplayName("1. args")
	@Test
	internal fun test_1() {
		Assertions.assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl::class.java)).isTrue
		Assertions.assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl::class.java)).isTrue
		Assertions.assertThat(pointcut("args()").matches(helloMethod, MemberServiceImpl::class.java)).isFalse()
		Assertions.assertThat(pointcut("args(..)").matches(helloMethod, MemberServiceImpl::class.java)).isTrue
		Assertions.assertThat(pointcut("args(*)").matches(helloMethod, MemberServiceImpl::class.java)).isTrue
		Assertions.assertThat(pointcut("args(String,..)").matches(helloMethod, MemberServiceImpl::class.java)).isTrue
	}


	@DisplayName("2. argsVsExecution")
	@Test
	internal fun test_2() {
		Assertions.assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl::class.java)).isTrue
		Assertions.assertThat(pointcut("args(java.io.Serializable)").matches(helloMethod, MemberServiceImpl::class.java)).isTrue
		Assertions.assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl::class.java)).isTrue()

		Assertions.assertThat(pointcut("execution(* *(String))").matches(helloMethod, MemberServiceImpl::class.java)).isTrue
		Assertions.assertThat(pointcut("execution(* *(java.io.Serializable))").matches(helloMethod, MemberServiceImpl::class.java)).isFalse()
		Assertions.assertThat(pointcut("execution(* *(Object))").matches(helloMethod, MemberServiceImpl::class.java)).isFalse()
	}
}