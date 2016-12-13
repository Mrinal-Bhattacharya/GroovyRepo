package com.groovy.multimethods

import static org.junit.Assert.*

import org.junit.Test

class MultimethodSpec {

	@Test
	public void test() {

		Object x = 1
		Object y = 'foo'
		assert 'object' == new Multimethod().oracle(x)
		assert 'string' == new Multimethod().oracle(y)
	}

	@Test
	void equals(){
		Object same = new Equalizer()
		Object other = new Object()
		assert new Equalizer().equals( same )
		assert ! new Equalizer().equals( other )
	}
}
