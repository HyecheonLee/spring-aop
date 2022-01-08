package com.hyecheon.springaop.exam

import com.hyecheon.springaop.exam.annotation.Retry
import com.hyecheon.springaop.exam.annotation.Trace
import org.springframework.stereotype.Repository
import java.lang.IllegalStateException

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
@Repository
class ExamRepository {
	companion object {
		private var seq: Int = 0
	}

	@Retry(value = 4)
	@Trace
	fun save(itemId: String) = run {
		seq++
		if (seq % 5 == 0) {
			throw IllegalStateException("예외 발생")
		}
		"ok"
	}

}