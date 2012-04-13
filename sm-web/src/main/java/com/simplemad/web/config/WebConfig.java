package com.simplemad.web.config;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.simplemad.msgserver.SimplemadServer;
import com.simplemad.web.config.editor.CustomEditorGenerator;
import com.simplemad.web.config.editor.SimplemadPropertyEditor;

@Configuration
//ImportResource导入xml时，只对属于spring bean的对象进行初始化，
//而对于@Configuration不作任何初始化，因此在cn.pinlog.base里声明了@Configuration的
//MongoConfiguration由此处导入是不起任何作用的，但用xml的import却起作用，
//因此@ImportResource使用时慎用
//@ImportResource(value={"classpath:application-base.xml"})
public class WebConfig {

	@Bean
	public AbstractHandlerMapping getHandlerMapping() {
		SimplemadServer.instance();
		return new ControllerClassNameHandlerMapping();
	}

	@Bean(name={"viewResolver"})
	public ViewResolver getJspViewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/page/");
		vr.setSuffix(".jsp");
		return vr;
	}

	/**
	 * @return <br/>
	 *         添加文件上传的支持
	 */
	@Bean(name = { "multipartResolver" })
	public MultipartResolver getMultipartResolver() {
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxInMemorySize(1);
		return mr;
	}

	@Bean
	public HandlerAdapter getHandlerAdapter() {
		AnnotationMethodHandlerAdapter adapter = new AnnotationMethodHandlerAdapter();
		// 添加json转换支持
		adapter.setMessageConverters(new HttpMessageConverter[] { new MappingJacksonHttpMessageConverter() });
		// 添加controller类方法参数处理支持
		SimplemadArgumentResolver resolver = new SimplemadArgumentResolver();
		resolver.setEditors(CustomEditorGenerator.genEditors());
		adapter.setCustomArgumentResolver(resolver);
		WebBindingInitializer initializer = getWebBinding();
		adapter.setWebBindingInitializer(initializer);
		return adapter;
	}
	
	@Bean
	public WebBindingInitializer getWebBinding() {
		return new SimplemadBindingInitializer();
	}
	
	@Bean(name = {"editorConfigurer"})
	public CustomEditorConfigurer getCustomEditorConfigurer() {
		CustomEditorConfigurer configurer = new CustomEditorConfigurer();
		Map<String, PropertyEditor> editors = new HashMap<String, PropertyEditor>();
		for(SimplemadPropertyEditor editor : CustomEditorGenerator.genEditors()) {
			editors.put(editor.getEntityClass().getName(), editor);
		}
		configurer.setCustomEditors(editors);
		return configurer;
	}

}
