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
private val log = KotlinLogging.logger {}

@Import(AtAnnotationTest.AtAnnotationAspect::class)
@SpringBootTest
class AtAnnotationTest {

	@Autowired
	lateinit var memberService: MemberService


	@DisplayName("1. success")
	@Test
	internal fun test_1() {
		log.info { "memberService proxy=${memberService.javaClass}" }
		memberService.hello("helloA")
	}


	@Aspect
	class AtAnnotationAspect {
		@Around("@annotation(com.hyecheon.springaop.member.annotation.MethodAop)")
		fun doAtAnnotation(joinPoint: ProceedingJoinPoint): Any? = run {
			log.info { "[@annotation] ${joinPoint.signature}" }
			joinPoint.proceed()
		}
	}
}