package com.groovy.collections

class GroovyMap {

	Map frequency(textCorpus){

		def words = textCorpus.tokenize()
		def wordFrequency = [:]
		words.each { word ->
			wordFrequency[word] = wordFrequency.get(word,0) + 1
		}
		return wordFrequency
	}
}