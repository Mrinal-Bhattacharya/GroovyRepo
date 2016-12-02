package com.groovy.text

import static org.junit.Assert.*

import org.junit.Test

class StringBufferSpec {

	@Test
	public void append() {
		def greeting = 'Hello'
		greeting <<= ' Groovy'
		assert greeting instanceof java.lang.StringBuffer
		greeting << '!'
		assert greeting.toString() == 'Hello Groovy!'
		greeting[1..4] = 'i'
		assert greeting.toString() == 'Hi Groovy!'
	}
}
