package com.cyberark.conjur.springboot.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import com.cyberark.conjur.springboot.core.env.ConjurPropertySource;
import com.cyberark.conjur.springboot.domain.ConjurAuthParam;
import com.cyberark.conjur.springboot.service.CustomPropertySourceChain;
import com.cyberark.conjur.springboot.service.DefaultPropertySourceChain;
import com.cyberark.conjur.springboot.service.PropertyProcessorChain;



/**
 * The ValueProcess class will be invoked on boot strap of the applicaiton and
 * will invoke the process chain based on the properties. It call the default
 * property chain if value is found or will call the Custome propertysource to
 * retrieve the value from the Conjur vault . This class in turn will invoke the
 * ConjurPropertySource to autowire the value for @Value annotation
 * 
 *
 */

@Configuration
@ConditionalOnExpression(value="${conjur.cloud.enabled}")
public class ConjurValueProcessor implements BeanPostProcessor, InitializingBean, EnvironmentAware, ApplicationContextAware {

	private Logger logger = LoggerFactory.getLogger(ConjurValueProcessor.class);

	private ApplicationContext context;

	private ConfigurableEnvironment environment;

	@Autowired
	private ConjurPropertySource propertySource;

	@Autowired
	private ConjurAuthParam conjurParam;

	private PropertyProcessorChain processorChain;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		logger.info("Start : afterPropertiesSet" + conjurParam);

		this.processorChain = new DefaultPropertySourceChain("DefaultPropertySource");
		CustomPropertySourceChain customPS = new CustomPropertySourceChain("CustomPropertySource");
		processorChain.setNextChain(customPS);

		// environment.getPropertySources().addLast(new ConjurPropertySource());
		environment.getPropertySources().addLast(processorChain);

		logger.info("End :afterPropertiesSet" + environment.getPropertySources());

	}

	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		if (environment instanceof ConfigurableEnvironment) {

			this.environment = (ConfigurableEnvironment) environment;
			logger.info("Available environment>>>" + environment);
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.context = applicationContext;

	}

}
