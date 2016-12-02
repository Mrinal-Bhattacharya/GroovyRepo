package com.groovy.annotations

class Number {
	def repetition(){
		def store=''
		10.times{ store+='x' }
		return store
	}

	def walkingUp(){
		def store=''
		1.upto(5){x-> store+=x}
		return store
	}

	def walkingDown(){
		def store=''
		5.downto(1){x-> store+=x}
		return store
	}

	def walkingWithStep(){
		def store=''
		1.step(10,2){x-> store+=x}
		return store
	}
}