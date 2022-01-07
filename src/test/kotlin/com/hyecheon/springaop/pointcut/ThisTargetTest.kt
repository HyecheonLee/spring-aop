package com.hyecheon.springaop.pointcut

import com.hyecheon.springaop.member.MemberService
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
@Import(ThisTargetTest.ThisTargetAspect::class)
@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"])
class ThisTargetTest {
	private val log = KotlinLogging.logger {}

	@Autowired
	lateinit var memberService: MemberService


	@DisplayName("1. success")
	@Test
	internal fun test_1() {
		log.info { "memberService Proxy=${memberService.javaClass}" }
		memberService.hello("helloA")
	}

	@Aspect
	class ThisTargetAspect {
		private val log = KotlinLogging.logger {}

		@Around("this(com.hyecheon.springaop.member.MemberService)")
		fun doThisInterface(joinPoint: ProceedingJoinPoint): Any? = run {
			log.info { "[this-interface] ${joinPoint.signature}" }
			joinPoint.proceed()
		}

		@Around("target(com.hyecheon.springaop.member.MemberService)")
		fun doTargetInterface(joinPoint: ProceedingJoinPoint) = run {
			log.info { "[target-interface] ${joinPoint.signature}" }
			joinPoint.proceed()
		}

		@Around("this(com.hyecheon.springaop.member.MemberServiceImpl)")
		fun doThis(joinPoint: ProceedingJoinPoint): Any? = run {
			log.info { "[this-interface] ${joinPoint.signature}" }
			joinPoint.proceed()
		}

		@Around("target(com.hyecheon.springaop.member.MemberServiceImpl)")
		fun doTarget(joinPoint: ProceedingJoinPoint) = run {
			log.info { "[target-impl] ${joinPoint.signature}" }
			joinPoint.proceed()
		}
	}
}