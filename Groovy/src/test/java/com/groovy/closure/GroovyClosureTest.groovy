package com.groovy.closure

import static org.junit.Assert.*

import org.junit.Test

class GroovyClosureTest {

	@Test
	public void logWithVaribale() {
		def GroovyClosure groovyClosure=new GroovyClosure()
		assert groovyClosure.logWithCounter() == '12345678910'
	}

	@Test
	public void logWithIt() {
		def GroovyClosure groovyClosure=new GroovyClosure()
		assert groovyClosure.logWithMagicalIT() == '12345678910'
	}

	@Test
	void declaringClosure(){
		def GroovyClosure groovyClosure=new GroovyClosure()
		assert groovyClosure.printer("Mrinal") == 'Hello Mrinal'
		assert groovyClosure.printer.call ("Mrinal") == 'Hello Mrinal'
	}

	@Test
	void closureAsParamter(){
		Map map = ['a':1, 'b':2]
		map.each{ key, value ->
			map[key] = value * 2
		}
		assert map == ['a':2, 'b':4]
	}

	@Test
	void passExternally(){
		Map map = ['a':1, 'b':2]
		Closure doubler = {key, value -> map[key] = value * 2 }
		map.each(doubler)
		assert map == ['a':2, 'b':4]
	}

	@Test
	void methodDeclaration(){
		Map map = ['a':1, 'b':2]
		GroovyClosure closure=new GroovyClosure()
		def doubler = closure.&doubleMethod
		map.each(doubler)
		assert map == ['a':2, 'b':4]
	}

	@Test
	void closureInformation(){
		GroovyClosure closure=new GroovyClosure()
		assert closure.numParams { one -> } == 1
		assert closure.numParams { one, two -> } == 2
		assert closure.paramTypes { String s -> } == [String]
		assert closure.paramTypes { Number n, Date d -> } == [Number, Date]
	}

	@Test
	void leftCurrying(){
		def mult = { x, y -> return x * y }
		def twoTimes = mult.curry(2)
		assert twoTimes(5) == 10
		def twoTime = { y -> mult 2, y }
		assert twoTime(5) == 10
	}

	@Test
	void rightCurrying(){
		def nCopies = { int n, String str -> str*n }
		def blah = nCopies.rcurry('bla')
		assert blah(2) == 'blabla'
		assert blah(2) == nCopies(2, 'bla')
	}

	@Test
	void indexCurrying(){
		def volume = { double l, double w, double h -> l*w*h }
		def fixedWidthVolume = volume.ncurry(1, 2d)
		assert volume(3d, 2d, 4d) == fixedWidthVolume(3d, 4d)
		def fixedWidthAndHeight = volume.ncurry(1, 2d, 4d)
		assert volume(3d, 2d, 4d) == fixedWidthAndHeight(3d)
	}

	@Test
	void composition(){
		def plus2  = { it + 2 }
		def times3 = { it * 3 }

		def times3plus2 = plus2 << times3
		assert times3plus2(3) == 11
		assert times3plus2(4) == plus2(times3(4))

		def plus2times3 = times3 << plus2
		assert plus2times3(3) == 15
		assert plus2times3(5) == times3(plus2(5))

		// reverse composition
		assert times3plus2(3) == (times3 >> plus2)(3)
	}

	@Test
	void priceCalutionCurrying(){
		def bk = new Book(name:'Groovy',
		author:'KenB',
		price:25,
		category:'CompSci')
		def discountRate = 0.1
		def taxRate = 0.17
		//  book closures
		def calcDiscountedPrice = Functor.rMultiply.curry(1 - discountRate)
		def calcTax = Functor.rMultiply.curry(1 + taxRate)
		def calcNetPrice =
				Functor.composition.curry(calcTax, calcDiscountedPrice)
		//  now calculate net prices
		def netPrice = calcNetPrice(bk.price)
		assert 26.325==netPrice
		//println "netPrice: ${netPrice}"
	}

	@Test
	void memoization(){
		def fib
		fib = { long n -> n<2?n:fib(n-1)+fib(n-2) }
		long start=System.currentTimeMillis()
		assert fib(15) == 610
		long end=System.currentTimeMillis()
		long firstCallTime=end-start
		start=System.currentTimeMillis()
		fib = { long n -> n<2?n:fib(n-1)+fib(n-2) }.memoize()
		end=System.currentTimeMillis()
		assert fib(25) == 75025
		long secondCallTime=end-start
		assert secondCallTime<firstCallTime
	}
}
