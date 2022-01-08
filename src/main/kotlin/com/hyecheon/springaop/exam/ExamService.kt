package com.hyecheon.springaop.exam

import com.hyecheon.springaop.exam.annotation.Trace
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2022/01/08
 */
@Service
class ExamService(
	private val examRepository: ExamRepository
) {

	@Trace
	fun request(itemId: String) = run {
		examRepository.save(itemId)
	}
}