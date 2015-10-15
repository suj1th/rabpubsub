package com.suj1th.rabpubsub;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;

@Singleton
public class PublisherUtil {

	@Inject
	MapperService mapper;
	
	private static final Logger LOGGER = Logger.getLogger(Publisher.class);

	public PublishStatus publish(Message message, String exchange,boolean isDurable){
		try {

			/*Create Channel*/
			Channel channel = PublisherChannelFactory.getChannel();
			channel.exchangeDeclare(exchange, "topic", isDurable);

			/*Serialize Message*/
			String msg = this.mapper.serialize(message);

			/*Publish to Queue*/
			channel.basicPublish(exchange, "", null, msg.getBytes());
		} catch (Exception e) {
			LOGGER.error("Exception in Publishing Message",e);
			return PublishStatus.FAILURE;
		}

		return PublishStatus.SUCCESS;
	}
}