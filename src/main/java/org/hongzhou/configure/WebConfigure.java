package org.hongzhou.configure;

import java.time.LocalDate;

import org.hongzhou.date.USLocalDateFormatter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebConfigure extends WebMvcConfigurerAdapter{
	
	@Override
	public void addFormatters(FormatterRegistry registry){
		registry.addFormatterForFieldType(LocalDate.class, new USLocalDateFormatter());
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
		EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer = new 
				EmbeddedServletContainerCustomizer(){
					@Override
					public void customize(ConfigurableEmbeddedServletContainer container) {
						container.addErrorPages(new ErrorPage(MultipartException.class, "/uploadError"));					
					}			
		};
		return embeddedServletContainerCustomizer;
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		return new SessionLocaleResolver();
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor(){
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}
	

}
