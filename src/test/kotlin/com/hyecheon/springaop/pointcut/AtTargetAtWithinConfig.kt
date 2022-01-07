package com.hyecheon.springaop.pointcut

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
@Configuration
class AtTargetAtWithinConfig {
	@Bean
	fun parent() = run {
		AtTargetAtWithinTest.Parent()
	}

	@Bean
	fun child(): AtTargetAtWithinTest.Child {
		return AtTargetAtWithinTest.Child()
	}

	@Bean
	fun atTargetAtWithinAspect(): AtTargetAtWithinTest.AtTargetAtWithinAspect {
		return AtTargetAtWithinTest.AtTargetAtWithinAspect()
	}
}