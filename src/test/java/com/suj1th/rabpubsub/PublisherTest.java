package com.suj1th.rabpubsub;

import java.io.IOException;

import org.junit.Test;

public class PublisherTest {

	
	@Test
	public void testPublish() throws IOException{
		Publisher publisher = new Publisher();
		
		ExtendedMessage message = new ExtendedMessage();
		message.setId("007");
		message.setType(EventType.TEST);
		message.setExtension("Extension");
		
		PublishStatus status = publisher.publish(message);
		
		assert status !=null;
		assert status == PublishStatus.SUCCESS;
	}

}
