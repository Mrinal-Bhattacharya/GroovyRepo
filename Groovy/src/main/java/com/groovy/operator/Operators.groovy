package com.groovy.operator

import groovy.transform.Immutable
@Immutable
class Money {
	int amount
	String currency
	Money plus (Money other) {
		if (null == other) return this
		if (other.currency != currency) {
			throw new IllegalArgumentException(
			"cannot add $other.currency to $currency")
		}
		return new Money(amount + other.amount, currency)
	}
	Money plus (Integer more) {
		return new Money(amount + more, currency)
	}
}