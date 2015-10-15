package com.suj1th.rabpubsub;

import java.util.HashMap;
import java.util.Map;

/**
 * The base class for all publish-able event-messages.
 * 
 * @author suj1th
 *
 */
public class Message {
	
	private String id;
	private EventType type;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=").append(id).append(", type=")
				.append(type).append(", additionalProperties=")
				.append(additionalProperties).append("]");
		return builder.toString();
	}
	
	

}
