package com.hyecheon.springaop.member

import com.hyecheon.springaop.member.annotation.ClassAop
import com.hyecheon.springaop.member.annotation.MethodAop
import org.springframework.stereotype.Component

@ClassAop
@Component
class MemberServiceImpl : MemberService {

	@MethodAop("test value")
	override fun hello(param: String): String {
		return "ok"
	}

	fun internal(param: String) = run {
		"ok"
	}
}