package com.groovy.collections

class GroovyList {
	def quickSort(list) {
		if (list.size() < 2) return list
		def pivot = list[list.size().intdiv(2)]
		def left = list.findAll { item -> item < pivot }
		def middle = list.findAll { item -> item == pivot }
		def right = list.findAll { item -> item > pivot }
		return quickSort(left) + middle + quickSort(right)
	}
}
