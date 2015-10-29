package com.suj1th.rabpubsub;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;



/**
 * @author suj1th
 *
 */
@Singleton
public final class Publisher {
	
	private IPublisherUtil publisherUtil;


	@Inject
	public void setPublisherUtil(IPublisherUtil publisherUtil) {
		this.publisherUtil = publisherUtil;
	}

	/**
	 * no-args constructor.
	 *  
	 */
	 public Publisher(){}

	 


	 /**
	  * publishes {@link Message} to RabbitMQ
	  * 
	  * @param message
	  * @return {@link PublishStatus}
	  * @throws IOException
	  */
	 public PublishStatus publish(Message message) throws IOException{
		 return publisherUtil.publish(message);
	 }

}
