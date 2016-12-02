package com.groovy.text

import static org.junit.Assert.*
import static java.util.Calendar.*
import org.junit.Test

class GStringSpec {

	@Test
	public void placeHolder() {
		def nick = 'ReGina'
		def book = 'Groovy in Action, 2nd ed.'
		assert "$nick is $book" == 'ReGina is Groovy in Action, 2nd ed.'
	}

	@Test
	public void placHolderWithMap(){
		TimeZone.default = TimeZone.getTimeZone('GMT')
		def date = new Date(0)
		def dateMap = [y:date[YEAR]-1900, m:date[MONTH], d:date[DAY_OF_MONTH]]
		def out = "Year $dateMap.y Month $dateMap.m Day $dateMap.d"
		assert out == 'Year 70 Month 0 Day 1'
	}

	@Test
	public void placHolderWithFunction(){
		def date = new Date(0)
		def tz = TimeZone.getTimeZone('GMT')
		def format = 'd MMM YYYY HH:mm:SS z'
		def out = "Date is ${date.format(format, tz)} !"
		assert out == 'Date is 1 Jan 1970 00:00:00 GMT !'
	}

	@Test
	public void multiline(){
		def date = new Date(0)
		def dateMap = [y:date[YEAR]-1900, m:date[MONTH], d:date[DAY_OF_MONTH]]
		def sql = """
SELECT FROM MyTable
WHERE Year = $dateMap.y
"""
		assert sql == """
SELECT FROM MyTable
WHERE Year = 70
"""
	}

	@Test
	void dynamicParts(){
		def me = 'Tarzan'
		def you = 'Jane'
		def line = "me $me - you $you"
		assert line == 'me Tarzan - you Jane'
		assert line instanceof GString
		assert line.strings[0] == 'me '
		assert line.strings[1] == ' - you '
		assert line.values[0] == 'Tarzan'
		assert line.values[1] == 'Jane'
	}

	@Test
	void miscellany(){
		String greeting = 'Hello Groovy!'
		assert greeting.startsWith('Hello')
		assert greeting.getAt(0) == 'H'
		assert greeting[0] == 'H'
		assert greeting.indexOf('Groovy') >= 0
		assert greeting.contains('Groovy')
		assert greeting[6..11] == 'Groovy'
		assert 'Hi' + greeting - 'Hello' == 'Hi Groovy!'
		assert greeting.count('o') == 3
		//println 'x'.padLeft(3)
		assert 'x'.padLeft(3) == '  x'
		assert 'x'.padRight(3,'_') == 'x__'
		assert 'x'.center(3) == ' x '
		assert 'x' * 3 == 'xxx'
	}
}
