package com.groovy.library

import static org.junit.Assert.*

import org.junit.Test
import com.groovy.typealias.MathLib as Lib
class MathLibSpec {

	@Test
	void wrongCalculation(){
		def math=new Lib()
		assert 15==math.twice(5)
	}

	@Test
	public void writeCalulation() {
		def mathlib = new MathLib()
		assert 10 == mathlib.twice(5)
		assert 2 == mathlib.half(5)
	}
}
