package com.suj1th.rabpubsub;

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
	public PublishStatus publish(Message message, String exchange,boolean isDurable){
		try {

			/*Create Channel*/
			Channel channel = publisherChannelFactory.getChannel();
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
