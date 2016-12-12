package com.groovy.bean

import static org.junit.Assert.*

import org.junit.Test

class GroovyBeanSpec {

	@Test
	public void accessFieldsWithAccessMethod() {
		def groovyBook = new GroovyBean()
		groovyBook.setTitle('Groovy in Action')
		assert groovyBook.getTitle() == 'Groovy in Action'
		groovyBook.title = 'Groovy conquers the world'
		assert groovyBook.title == 'Groovy conquers the world'
	}

	@Test
	void subscriptOperator(){
		def groovyBook = new GroovyBean()
		def fieldName = 'title'
		groovyBook[fieldName] = "Hi"
		assert groovyBook['title'] == "Hi"
	}

	@Test
	void generalFieldAccess(){
		def pretender = new GroovyBean()
		assert pretender.isNoField == 'pretend value'
		assert pretender.count == 0
		pretender.isNoFieldEither = 'just to increase counter'
		assert pretender.count == 1
	}

	@Test
	void parameterUse(){
		def summer = new Summer()
		assert 2 == summer.sumWithDefaults(1,1)
		assert 3 == summer.sumWithDefaults(1,1,1)
		assert 2 == summer.sumWithList([1, 1])
		assert 3 == summer.sumWithList([1, 1, 1])
		assert 2 == summer.sumWithOptionals(1,1)
		assert 3 == summer.sumWithOptionals(1,1,1)
		assert 2 == summer.sumNamed(a:1, b:1)
		assert 3 == summer.sumNamed(a:1, b:1, c:1)
		assert 1 == summer.sumNamed(c:1)
	}

	@Test
	void elvisOperator(){
		def map = [a:[b:[c:1]]]
		assert map.a.b.c == 1
		if (map && map.a && map.a.x){
			assert map.a.x.c == null
		}
		try {
			assert map.a.x.c == null
		} catch (NullPointerException ignore){
		}
		assert map?.a?.x?.c == null
	}

	@Test
	void positionalParameterConstructors(){
		def first = new VendorWithCtor('Canoo','ULC')
		def second = ['Canoo', 'ULC'] as VendorWithCtor
		VendorWithCtor third = ['Canoo', 'ULC']
	}

	@Test
	void namedParameterConstructors(){
		new SimpleVendor()
		new SimpleVendor(name: 'Canoo')
		new SimpleVendor(product: 'ULC')
		new SimpleVendor(name: 'Canoo', product: 'ULC')
		def vendor = new SimpleVendor(name: 'Canoo')
		assert 'Canoo' == vendor.name
	}

	@Test
	void implicitParameterConstructors(){
		def bean=new ImplicitConstructor(name:"bean1",color:"white")
		//def bean=new ImplicitConstructor(name:"bean1",color:"white")
		assert "bean1"==bean.name
		ImplicitConstructor bean2= ["bean2", "white"]
		assert "bean2"==bean2.name
	}
}
