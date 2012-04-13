package com.simplemad.msgserver.jmeter;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.simplemad.msgserver.CommandFactory;
import com.simplemad.msgserver.IMessageCommand;
import com.simplemad.msgserver.runnable.LogonRunnable;

public class TestLogonSamplerClient extends AbstractJavaSamplerClient {
	private static long start = 0;
	private static long end = 0;

	@Override
	public void setupTest(JavaSamplerContext context) {
		start = System.currentTimeMillis();
		super.setupTest(context);
		IMessageCommand command = CommandFactory.create(new LogonRunnable().createMessage());
		command.execute();
	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult result = new SampleResult();
		result.sampleStart();
		result.sampleEnd();
		result.setSuccessful(true);
		return result;
	}
	
	@Override
	public void teardownTest(JavaSamplerContext context) {
		super.teardownTest(context);
		
		end = System.currentTimeMillis();
		
		System.out.println("cost time:" + (end - start));
	}
	
}
