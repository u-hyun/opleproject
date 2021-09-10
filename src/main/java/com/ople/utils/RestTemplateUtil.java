package com.ople.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateUtil {
	@Bean
	public RestTemplate getCustomRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory 
		= new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(2000);
		httpRequestFactory.setReadTimeout(60000);
		
		HttpClient httpClient 
		= HttpClientBuilder.create().setMaxConnTotal(200)
		.setMaxConnPerRoute(20).build();
		httpRequestFactory.setHttpClient(httpClient);
		return new RestTemplate(httpRequestFactory);
	}
}
/*
 * 
 * @Configuration
public class RestTemplateUtil {
	// http요청 처리하는 RestTemplate 객체 생성
	@Bean
	public RestTemplate getCustomRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(2000);
		httpRequestFactory.setReadTimeout(60000);

		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(20).build();
		httpRequestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;}}
 */






