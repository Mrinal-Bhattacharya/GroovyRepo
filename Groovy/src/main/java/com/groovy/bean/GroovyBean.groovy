package com.groovy.bean

class GroovyBean {
	String title
	public count=0
	Object get (String name) {
		return 'pretend value'
	}
	void set (String name, Object value) {
		count++
	}
}
class Summer {
	def sumWithDefaults(a, b, c=0){
		return a + b + c
	}
	def sumWithList(List args){
		return args.inject(0){sum,i -> sum += i}
	}
	def sumWithOptionals(a, b, Object[] optionals){
		return a + b + sumWithList(optionals.toList())
	}
	def sumNamed(Map args){
		['a', 'b', 'c'].each{args.get(it,0)}
		return args.a + args.b + args.c
	}
}
class VendorWithCtor {
	String name, product
	VendorWithCtor(name, product) {
		this.name = name
		this.product = product
	}
}
class SimpleVendor {
	String name, product
}

class ImplicitConstructor{
	String name
	String color
	ImplicitConstructor(Map map){
		println "if we dont craete map function grrovy creare automattically. Now we need to assign values from map other values will not init"
		name=map.name
		color=map.color
	}

	ImplicitConstructor(List list){
		println "if we dont craete list function grrovy creare automattically. Now we need to assign values from list other values will not init"
		name=(String)list.getAt(0)
		color=(String)list.getAt(1)
	}
}