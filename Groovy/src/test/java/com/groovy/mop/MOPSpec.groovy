package com.groovy.mop

import static org.junit.Assert.*

import org.junit.Test

class MOPSpec {

	@Test
	public void methodMissing() {
		def bounce = new Pretender()
		assert bounce.hello('world') == 'called hello with [world]'
	}

	@Test
	public void gorm() {
		def people = new MiniGorm()
		def dierk = [first: 'Dierk', last:'Koenig']
		def paul = [first: 'Paul', last:'King']
		people.db << dierk << paul
		assert people.findByFirst('Dierk') == dierk
		assert people.findByLast('King') == paul
	}

	@Test
	void propertyMissing(){
		def bounce = new PropPretender()
		assert bounce.hello == 'accessed hello'
	}

	@Test
	void dynamicPropertyMissing(){
		def one = new DynamicPretender()
		assert one.hello == 'accessed hello'
		one.whatToDo = { name ->
			name.size()
		}
		assert one.hello == 5
	}

	@Test
	void metaClassMiscellany(){
		MetaClass mc = String.metaClass
		final Object[] NO_ARGS = []
		assert 1 == mc.respondsTo("toString", NO_ARGS).size()
		assert 3 == mc.properties.size()
		assert 76 == mc.methods.size()
		assert 176 == mc.metaMethods.size()
		assert "" == mc.invokeMethod("","toString", NO_ARGS)
		assert null == mc.invokeStaticMethod(String, "println", NO_ARGS)
		assert "" == mc.invokeConstructor(NO_ARGS)
	}

	@Test
	void stringExpandoMetaClass(){
		assert String.metaClass =~ /MetaClassImpl/
		String.metaClass.low = { -> delegate.toLowerCase() }
		assert String.metaClass =~ /ExpandoMetaClass/
		assert "DiErK".low() == "dierk"
	}

	@Test
	void modifyingMetaClassOfAClass(){
		def before = new MyGroovy1()
		MyGroovy1.metaClass.myProp = "MyGroovy prop"
		MyGroovy1.metaClass.test = {-> myProp }
		try {
			before.test()
			assert false, "should throw MME"
		} catch(mme) { }
		assert new MyGroovy1().test() == "MyGroovy prop"
	}
	@Test
	void modifyingMetaClassOfAGroovyInstance(){
		def myGroovy = new MyGroovy2()
		myGroovy.metaClass.myProp = "MyGroovy prop"
		myGroovy.metaClass.test = {-> myProp }
		try {
			new MyGroovy2().test()
			assert false, "should throw MME"
		} catch(mme) { }
		assert myGroovy.test() == "MyGroovy prop"
	}

	@Test
	void modifyingMetaClassOfAJavaInstance(){
		def myJava = new String()
		myJava.metaClass.myProp = "MyJava prop"
		myJava.metaClass.test = {-> myProp }
		try {
			new String().test()
			assert false, "should throw MME"
		} catch(mme) { }
		assert myJava.test() == "MyJava prop"
	}

	@Test
	void addStaticMethodInClass(){
		Integer.metaClass.static.answer = {-> 42}
		assert Integer.answer() == 42
	}

	@Test
	void metaClassChangesForSuperclassesAndInterfaces(){
		MySuperGroovy.metaClass.added = {-> true }
		assert new MySubGroovy().added()
		Map.metaClass.toTable = {
			->
			delegate.collect{ [it.key, it.value]}
		}
		assert [a:1, b:2].toTable() == [['a', 1], ['b', 2]]
	}
}
