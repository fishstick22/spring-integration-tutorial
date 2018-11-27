package com.intertech.lab8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Startup {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/si-components.xml");

		// MessageChannel channel = context.getBean("requestChannel",
		// MessageChannel.class);
		// Message<String> message =
		// MessageBuilder.withPayload("Hello brave new world").build();
		// channel.send(message);

		PigLatinService service = context.getBean("latinService",
				PigLatinService.class);
		Future<String> future = service.translate("Hello brave new world");
		// do more work here in a real application
		System.out.println("do more work here in a real application");
		String serviceOutput;
		try {
			serviceOutput = future.get(5000, TimeUnit.SECONDS);
			System.out.println(serviceOutput);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		context.close();
	}
}
