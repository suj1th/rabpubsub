package com.suj1th.rabpubsub;

import java.io.IOException;

import javax.inject.Inject;


/**
 * @author suj1th
 *
 */
public class Publisher {
	
	/** 
	 * TODO: 
	 * decouple exchange name from this class. It should be 
	 * read from a properties file.
	 * */
	private final static String EXCHANGE = "logs";
	private final static boolean IS_DURABLE = false;

	
	private IPublisherUtil publisherUtil;

	@Inject
	public void setPublisherUtil(IPublisherUtil publisherUtil) {
		this.publisherUtil = publisherUtil;
	}

	
	/**
	 * publishes {@link Message} to RabbitMQ
	 * 
	 * @param message
	 * @return {@link PublishStatus}
	 * @throws IOException
	 */
	public PublishStatus publish(Message message) throws IOException{
		return publisherUtil.publish(message, EXCHANGE, IS_DURABLE);
	}

}
