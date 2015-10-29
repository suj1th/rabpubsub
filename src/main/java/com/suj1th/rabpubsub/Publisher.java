package com.suj1th.rabpubsub;

import java.io.IOException;

import javax.inject.Inject;

import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * @author suj1th
 *
 */
public final class Publisher {
	
	private IPublisherUtil publisherUtil;
	private static Injector injector;

	private final static class PublisherHolder{
		static {
			configure();
		}
		private final static Publisher PUBLISHER = injector.getInstance(Publisher.class);
	}
	

	@Inject
	public void setPublisherUtil(IPublisherUtil publisherUtil) {
		this.publisherUtil = publisherUtil;
	}

	/**
	 * no-args constructor.
	 *  
	 */
	 Publisher(){}

	 
	 private static void configure(){
		 Publisher.injector = Guice.createInjector(new PublisherInjector());
	 }


	 public static Publisher getInstance(){
		 return PublisherHolder.PUBLISHER;
	 }

	 /**
	  * publishes {@link Message} to RabbitMQ
	  * 
	  * @param message
	  * @return {@link PublishStatus}
	  * @throws IOException
	  */
	 public PublishStatus publish(Message message) throws IOException{
		 System.out.println(publisherUtil);
		 return publisherUtil.publish(message);
	 }

}
