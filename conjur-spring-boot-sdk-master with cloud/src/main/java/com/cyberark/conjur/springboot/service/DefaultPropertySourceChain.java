package com.cyberark.conjur.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyberark.conjur.springboot.domain.ConjurAuthParam;



public class DefaultPropertySourceChain extends PropertyProcessorChain {

	private Logger logger = LoggerFactory.getLogger(DefaultPropertySourceChain.class);

	private PropertyProcessorChain chain;
	private ConjurAuthParam conjurParam;

	public DefaultPropertySourceChain(String name) {

		//super("defaultPropertySource", conjurParam);
		super("defaultPropertySource");
		logger.info("Calling CustomPropertysource Chain");
		//this.conjurParam = conjurParam;

	}

	@Override
	public void setNextChain(PropertyProcessorChain nextChain) {
		// TODO Auto-generated method stub
		this.chain = nextChain;

	}

	@Override
	public String[] getPropertyNames() {
		// TODO Auto-generated method stub
		return new String[0];
	}

	@Override
	public Object getProperty(String name) {
		// TODO Auto-generated method stub

		String value = null;

		if (value == null) {
			value = (String) this.chain.getProperty(name);

		}

		return value;
	}

}
