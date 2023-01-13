package com.cyberark.conjur.springboot.processor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.cyberark.conjur.sdk.endpoint.SecretsApi;

@Configuration

@ConditionalOnClass(ConjurRetrieveSecretService.class)
public class SpringBootConjurAutoConfiguration {

	@ConditionalOnMissingBean
	@Bean
	ConjurValueClassProcessor conjurSecretValueClassProcessor() {
		return new ConjurValueClassProcessor();
	}

	@ConditionalOnMissingBean
	@Bean
	ConjurRetrieveSecretService conjurRetrieveSecretService() {
		return new ConjurRetrieveSecretService();
	}

	@ConditionalOnMissingBean
	@Bean
	SecretsApi secretsApi() {
		return new SecretsApi();
	}
	
	@ConditionalOnMissingBean
	@Bean
	ConjurValuesClassProcessor conjurValuesClassProcessor() {
		return new ConjurValuesClassProcessor();
	}
	
	@ConditionalOnExpression(value="${conjur.cloud.enabled}")
	@Bean
	public ConjurValueProcessor conjurValueProcessor()
	{
		return new ConjurValueProcessor();
	}

}
