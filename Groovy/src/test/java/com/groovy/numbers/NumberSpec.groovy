package com.groovy.numbers

import static org.junit.Assert.*

import org.junit.Test

import com.groovy.annotations.Number

class NumberSpec {

	@Test
	public void numbersAreObject() {
		def x = 1
		def y = 2
		assert x + y == 3
		assert x.plus(y) == 3
		assert x instanceof Integer
	}

	@Test
	void repetions(){
		Number number=new Number()
		assert 'xxxxxxxxxx' == number.repetition()
	}

	@Test
	void loopInInc(){
		Number num=new Number()
		assert '12345' == num.walkingUp()
	}

	@Test
	void loopInDec(){
		Number num=new Number()
		assert '54321' == num.walkingDown()
	}

	@Test
	void loopInStep(){
		Number num=new Number()
		assert '13579' == num.walkingWithStep()
	}
}
