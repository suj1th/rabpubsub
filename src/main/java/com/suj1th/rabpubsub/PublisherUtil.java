package com.suj1th.rabpubsub;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;


/** A utility class for {@link Publisher}
 * @author suj1th
 *
 */
@Singleton
public class PublisherUtil implements IPublisherUtil {

	
	private IMapperService mapper;
	private PublisherChannelFactory publisherChannelFactory;
	private static final Logger LOGGER = Logger.getLogger(Publisher.class);
	
	/** 
	 * TODO: 
	 * decouple exchange name from this class. It should be 
	 * read from a properties file.
	 * */
	private final static String EXCHANGE = "logs";
	private final static boolean IS_DURABLE = true;
	
	@Inject
	public void setMapper(IMapperService mapper) {
		this.mapper = mapper;
	}

	@Inject
	public void setPublisherChannelFactory(
			PublisherChannelFactory publisherChannelFactory) {
		this.publisherChannelFactory = publisherChannelFactory;
	}


	/* (non-Javadoc)
	 * @see com.suj1th.rabpubsub.IPublisherUtil#publish(com.suj1th.rabpubsub.Message, java.lang.String, boolean)
	 */
	@Override
	public PublishStatus publish(Message message){
		try {

			/*Create Channel*/
			Channel channel = publisherChannelFactory.getChannel();
			channel.exchangeDeclare(EXCHANGE, "direct", IS_DURABLE);
			
			String queueName = message.getType().toString();
			
			/*The routing key is the same the queueName, because we maintain a 
			 * one-to-one relation between message-type and queues*/
			String routingKey = queueName;
			
			try {
				channel.queueDeclarePassive(queueName);
			} catch (IOException e) {
				channel.queueDeclare(queueName, true, false, false, null);
			}
			channel.queueBind(queueName, EXCHANGE, routingKey);
			

			/*Serialize Message*/
			String msg = this.mapper.serialize(message);

			/*Publish to Queue*/
			channel.basicPublish(EXCHANGE, routingKey, null, msg.getBytes());
		} catch (Exception e) {
			LOGGER.error("Exception in Publishing Message",e);
			return PublishStatus.FAILURE;
		}

		return PublishStatus.SUCCESS;
	}
}
