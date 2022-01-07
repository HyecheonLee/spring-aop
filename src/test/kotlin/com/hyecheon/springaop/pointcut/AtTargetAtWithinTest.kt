package com.hyecheon.springaop.pointcut

import com.hyecheon.springaop.member.annotation.ClassAop
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
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
@Import(AtTargetAtWithinConfig::class)
@SpringBootTest
class AtTargetAtWithinTest {
	private val log = KotlinLogging.logger {}

	@Autowired
	lateinit var child: Child


	@DisplayName("1. ")
	@Test
	internal fun test_1() {

		log.info { "child Proxy=${child.javaClass}" }
		child.childMethod()
		child.parentMethod()

	}

	open class Parent {
		open fun parentMethod() = run {

		}
	}

	@ClassAop
	open class Child : Parent() {
		open fun childMethod() = run {

		}
	}

	@Aspect
	class AtTargetAtWithinAspect {
		private val log = KotlinLogging.logger {}

		@Around("execution(* com.hyecheon.springaop..*(..)) && @target(com.hyecheon.springaop.member.annotation.ClassAop)")
		fun atTarget(joinPoint: ProceedingJoinPoint): Any? = run {
			log.info { "[@target] ${joinPoint.signature}" }
			joinPoint.proceed()
		}

		@Around("execution(* com.hyecheon.springaop..*(..)) && @within(com.hyecheon.springaop.member.annotation.ClassAop)")
		fun atWithin(joinPoint: ProceedingJoinPoint): Any? = run {
			log.info { "[@within] ${joinPoint.signature}" }
			joinPoint.proceed()
		}
	}
}