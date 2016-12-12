package com.groovy.closure

import static org.junit.Assert.*

import org.junit.Test

class DelegationSpec {

	@Test
	public void delegation() {
		def p = new Person(name: 'Norman')
		def t = new Thing(name: 'Teapot')
		def upperCasedName = { delegate.name.toUpperCase() }
		upperCasedName.delegate = p
		assert upperCasedName() == 'NORMAN'
		upperCasedName.delegate = t
		assert upperCasedName() == 'TEAPOT'
	}

	@Test
	void delegationStrategyOwnerFirst(){
		def p = new Person(name: 'Sarah')
		def t = new Thing(name: 'Teapot')

		assert p.toString() == 'Person name is Sarah'
		p.pretty.delegate = t
		assert p.toString() == 'Person name is Sarah'
	}

	@Test
	void delegationStrategyDelegateFirst(){
		def p = new Person(name: 'Sarah')
		def t = new Thing(name: 'Teapot')
		p.pretty.resolveStrategy = Closure.DELEGATE_FIRST
		assert p.toString() == 'Person name is Sarah'
		p.pretty.delegate = t
		assert p.toString() == 'Person name is Teapot'
	}

	@Test
	void delegationStrategyDelegateOnly(){
		def p = new Person(name:'Jessica', age:42)
		def t = new Thing(name:'Printer')
		def cl = p.fetchAge
		cl.delegate = p
		assert cl() == 42
		cl.delegate = t
		assert cl() == 42
		cl.resolveStrategy = Closure.DELEGATE_ONLY
		cl.delegate = p
		assert cl() == 42
		cl.delegate = t
		try {
			cl()
			assert false
		} catch (MissingPropertyException ex) {
			// "age" is not defined on the delegate
		}
	}
}
