package com.hyecheon.springaop.pointcut

import com.hyecheon.springaop.member.MemberService
import com.hyecheon.springaop.member.annotation.ClassAop
import com.hyecheon.springaop.member.annotation.MethodAop
import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
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

@Import(ParameterTest.ParameterAspect::class)
@SpringBootTest
class ParameterTest {
	@Autowired
	lateinit var memberService: MemberService


	@DisplayName("1. success")
	@Test
	internal fun test_1() {
		log.info { "memberService Proxy=${memberService.javaClass}" }
		memberService.hello("helloA")
	}

	@Aspect
	class ParameterAspect {
		@Pointcut("execution(* com.hyecheon.springaop.member..*.*(..))")
		fun allMember() = run {

		}

		@Around("allMember()")
		fun logArgs1(joinPoint: ProceedingJoinPoint): Any? = run {
			val arg1 = joinPoint.args[0]
			log.info { "[logArgs1]${joinPoint.signature}, arg=${arg1}" }
			joinPoint.proceed()
		}

		@Around("allMember() && args(arg,..)")
		fun logArgs2(joinPoint: ProceedingJoinPoint, arg: Any): Any? = run {
			log.info { "[logArgs1]${joinPoint.signature}, arg=${arg}" }
			joinPoint.proceed()
		}

		@Before("allMember() && args(arg,..)")
		fun logArgs2(joinPoint: JoinPoint, arg: Any) = run {
			log.info { "[this]${joinPoint.signature}, arg=${arg}" }
		}

		@Before("allMember() && this(obj)")
		fun thisArgs(joinPoint: JoinPoint, obj: MemberService) = run {
			log.info { "[this]${joinPoint.signature}, obj=${obj.javaClass}" }
		}

		@Before("allMember() && target(obj)")
		fun targetArgs(joinPoint: JoinPoint, obj: MemberService) = run {
			log.info { "[this]${joinPoint.signature}, obj=${obj.javaClass}" }
		}

		@Before("allMember() && @target(annotation)")
		fun targetArgs(joinPoint: JoinPoint, annotation: ClassAop) = run {
			log.info { "[@target]${joinPoint.signature}, obj=${annotation}" }
		}

		@Before("allMember() && @within(annotation)")
		fun atWithin(joinPoint: JoinPoint, annotation: ClassAop) = run {
			log.info { "[@within]${joinPoint.signature}, obj=${annotation}" }
		}

		@Before("allMember() && @annotation(annotation)")
		fun atAnnotation(joinPoint: JoinPoint, annotation: MethodAop) = run {
			log.info { "[@annotation]${joinPoint.signature}, annotationValue=${annotation.value}" }
		}
	}
}