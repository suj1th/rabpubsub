package com.suj1th.rabpubsub;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PublisherTest {
	
	private Injector injector;
	
	/*@Before
	public void configure(){
		injector = Guice.createInjector(new AbstractModule() {
            
            @Override
            protected void configure() {
                bind(IPublisherUtil.class).to(PublisherUtil.class);
                bind(IMapperService.class).to(MapperService.class);
            }
        });
	}
	
	@After
    public void tearDown() throws Exception {
        injector = null;
    }*/
	
	@Test
	public void testPublish() throws IOException{
		Publisher publisher = Publisher.getInstance();
		
		ExtendedMessage message = new ExtendedMessage();
		message.setId("007");
		message.setType(EventType.TEST);
		message.setExtension("Extension");
		
		PublishStatus status = publisher.publish(message);
		
		assert status !=null;
		assert status == PublishStatus.SUCCESS;
	}

}
