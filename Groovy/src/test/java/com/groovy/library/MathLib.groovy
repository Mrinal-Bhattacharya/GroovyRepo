package com.groovy.library

import com.groovy.typealias.MathLib as OrigMathLib
class MathLib extends OrigMathLib {
	Integer twice(Integer value) {
		return value * 2
	}
}
