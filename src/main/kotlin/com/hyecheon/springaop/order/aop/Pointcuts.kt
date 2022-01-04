package com.hyecheon.springaop.order.aop

import org.aspectj.lang.annotation.Pointcut

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/05
 */
class Pointcuts {
	@Pointcut("execution(* com.hyecheon.springaop.order..*(..))")
	fun allOrder() {
	}


	@Pointcut("execution(* *..*Service.*(..))")
	fun allService() {
	}

	@Pointcut("allOrder() && allService()")
	fun orderAndService() {
	}


}