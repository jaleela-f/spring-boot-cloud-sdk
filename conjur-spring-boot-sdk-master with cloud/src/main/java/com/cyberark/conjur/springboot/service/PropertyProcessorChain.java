package com.cyberark.conjur.springboot.service;

import org.springframework.core.env.EnumerablePropertySource;

import com.cyberark.conjur.springboot.domain.ConjurAuthParam;


public abstract class PropertyProcessorChain extends EnumerablePropertySource<Object> {

	private PropertyProcessorChain processorChain;
	private ConjurAuthParam conjurParam;

	public PropertyProcessorChain(String name) {
		super("propertyProcessorChain");
		//this.conjurParam = conjurParam;

	}

	public void setNextChain(PropertyProcessorChain processChain) {
		this.processorChain = new DefaultPropertySourceChain("");

		CustomPropertySourceChain customPS = new CustomPropertySourceChain("");
		processorChain.setNextChain(customPS);
	}

	@Override
	public Object getProperty(String name) {
		return name;

	}

}
