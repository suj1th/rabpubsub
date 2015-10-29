package com.suj1th.rabpubsub;

import com.google.inject.AbstractModule;

public class PublisherInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(IPublisherUtil.class).to(PublisherUtil.class);
		bind(IMapperService.class).to(MapperService.class);
	}

}
