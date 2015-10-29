package com.suj1th.rabpubsub;

public class ExtendedMessage extends Message{

	private String extension;

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExtendedMessage [extension=").append(extension)
				.append(", getId()=").append(getId()).append(", getType()=")
				.append(getType()).append(", getAdditionalProperties()=")
				.append(getAdditionalProperties()).append(", toString()=")
				.append(super.toString()).append(", getClass()=")
				.append(getClass()).append(", hashCode()=").append(hashCode())
				.append("]");
		return builder.toString();
	}
	
	
}
