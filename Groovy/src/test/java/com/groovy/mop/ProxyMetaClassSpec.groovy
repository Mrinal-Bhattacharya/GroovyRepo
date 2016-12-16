package com.groovy.mop

import static org.junit.Assert.*

import org.junit.Test

class ProxyMetaClassSpec {

	@Test
	public void tracer() {
		def tracer = new TracingInterceptor(writer: new StringWriter())
		def proxyMetaClass = ProxyMetaClass.getInstance(InspectMe)
		proxyMetaClass.interceptor = tracer
		InspectMe inspectMe = new InspectMe()
		inspectMe.metaClass = proxyMetaClass
		assert 1 == inspectMe.outer()
		println tracer.writer.toString()
		assert "\n"+tracer.writer.toString() != null
	}
}
