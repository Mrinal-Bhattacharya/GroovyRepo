package com.groovy.operator

import static org.junit.Assert.*

import org.junit.Test

class OperatorSpec {

	@Test
	public void overridding() {
		Money buck = new Money(1, 'USD')
		assert buck
		assert buck == new Money(1, 'USD')
		assert buck + buck == new Money(2, 'USD')
	}
	@Test
	public void overriddingWithInteger(){
		Money buck = new Money(1, 'USD')
		assert buck + 1 == new Money(2, 'USD')
	}
}
