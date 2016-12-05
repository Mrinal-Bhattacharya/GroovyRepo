package com.groovy.collections

import static org.junit.Assert.*

import org.junit.Test

class ListSpec {

	@Test
	void miscellany(){
		List myList = [1, 2, 3]
		assert myList.size() == 3
		assert myList[0] == 1
		assert myList instanceof ArrayList
		List emptyList = []
		assert emptyList.size() == 0
		List longList = (0..1000).toList()
		assert longList[555] == 555
		List explicitList = new ArrayList()
		explicitList.addAll(myList)
		assert explicitList.size() == 3
		explicitList[0] = 10
		assert explicitList[0] == 10
		explicitList = new LinkedList(myList)
		assert explicitList.size() == 3
		explicitList[0] = 10
		assert explicitList[0] == 10
	}

	@Test
	void overloadedSubscriptOperator(){
		def myList = ['a', 'b', 'c', 'd', 'e', 'f']
		assert myList[0..2] == ['a', 'b', 'c']
		assert myList[0, 2, 4] == ['a', 'c', 'e']
		myList[0..2] = ['x', 'y', 'z']
		assert myList == ['x', 'y', 'z', 'd', 'e', 'f']
		myList[3..5] = []
		assert myList == ['x', 'y', 'z']
		myList[1..1] = [0, 1, 2]
		assert myList == ['x', 0, 1, 2, 'z']
	}

	@Test
	void addingAndRemovingItems(){
		def myList = []
		myList += 'a'
		assert myList == ['a']
		myList += ['b', 'c']
		assert myList == ['a', 'b', 'c']
		myList = []
		myList << 'a' << 'b'
		assert myList == ['a', 'b']
		assert myList - ['b']== ['a']
		assert myList * 2 == ['a', 'b', 'a', 'b']
	}

	@Test
	void controlStructures(){
		def myList = ['a', 'b', 'c']
		assert myList.isCase('a')
		assert 'b' in myList
		def candidate = 'c'
		switch(candidate){
			case myList : assert true; break
			default : assert false
		}
		assert ['x', 'a', 'z'].grep(myList) == ['a']
		myList = []
		if (myList) assert false
		// Lists can be iterated with a 'for' loop
		def expr = ''
		for (i in [1, '*', 5]){
			expr += i
		}
		assert expr == '1*5'
	}

	@Test
	void manipulateListContent(){
		assert [1, [2, 3]].flatten() == [1, 2, 3]
		assert [1, 2, 3].intersect([4, 3, 1])== [3, 1]
		assert [1, 2, 3].disjoint([4, 5, 6])
		def list = [1, 2, 3]
		def popped = list.pop()
		assert popped == 3
		assert list == [1, 2]
		assert [1, 2].reverse() == [2, 1]
		assert [3, 1, 2].sort() == [1, 2, 3]
		list = [[1, 0], [0, 1, 2]]
		list = list.sort { a,b -> a[0] <=> b[0] }
		assert list == [[0, 1, 2], [1, 0]]
		list = list.sort { item -> item.size() }
		assert list == [[1, 0], [0, 1, 2]]
		list = ['a', 'b', 'c']
		list.remove(2)
		assert list == ['a', 'b']
		list.remove('b')
		assert list == ['a']
		list = ['a', 'b', 'b', 'c']
		list.removeAll(['b', 'c'])
		assert list == ['a']
		def doubled = [1, 2, 3].collect{ item -> item*2 }
		assert doubled == [2, 4, 6]
		def odd = [1, 2, 3].findAll{ item ->
			item % 2 == 1
		}
		assert odd == [1, 3]
	}

	@Test
	void querying(){
		def list = [1, 2, 3]
		assert list.first() == 1
		assert list.head() == 1
		assert list.tail() == [2, 3]
		assert list.last() == 3
		assert list.count(2) == 1
		assert list.max() == 3
		assert list.min() == 1
		def even = list.find { item ->
			item % 2 == 0
		}
		assert even == 2
		assert list.every { item -> item < 5 }
		assert list.any { item -> item < 2 }
	}

	@Test
	void iterating(){
		def list = [1, 2, 3]
		def store = ''
		list.each { item -> store += item }
		assert store == '123'
		store = ''
		list.reverseEach { item -> store += item }
		assert store == '321'
		store = ''
		list.eachWithIndex { item, index ->
			store += "$index:$item "
		}
		assert store == '0:1 1:2 2:3 '
	}

	@Test
	void accumulating(){
		def list = [1, 2, 3]
		assert list.join('-') == '1-2-3'
		def result = list.inject(0) { clinks, guests ->
			clinks + guests
		}
		assert result == 0 + 1 + 2 + 3
		assert list.sum() == 6
		def factorial = list.inject(1) { fac, item ->
			fac * item
		}
		assert factorial == 1 * 1 * 2 * 3
	}

	@Test
	void quicksort(){
		GroovyList list=new GroovyList()
		assert list.quickSort([]) == []
		assert list.quickSort([1]) == [1]
		assert list.quickSort([1, 2]) == [1, 2]
		assert list.quickSort([2, 1]) == [1, 2]
		assert list.quickSort([3, 1, 2]) == [1, 2, 3]
		assert list.quickSort([3, 1, 2, 2]) == [1, 2, 2, 3]
		assert list.quickSort([1.0f, 'a', 10, null]) == [null, 1.0f, 10, 'a']
		assert list.quickSort('bca') == 'abc'.toList()
	}

	@Test
	void proccessUrls(){
		def urls = [new URL('http', 'myshop.com', 80, 'index.html'), new URL('https', 'myshop.com', 443, 'buynow.html'), new URL('ftp', 'myshop.com', 21, 'downloads')]
		assert urls
		.findAll{ it.port < 99 }
		.collect{ it.file.toUpperCase() }
		.sort()
		.join(', ') == 'DOWNLOADS, INDEX.HTML'
	}
}
