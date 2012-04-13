package com.simplemad.web.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.simplemad.base.util.CollectionUtil;
import com.simplemad.base.util.PojoUtil;
import com.simplemad.web.config.editor.SimplemadPropertyEditor;

public class SimplemadArgumentResolver implements WebArgumentResolver {

	private final static Logger log = Logger.getLogger(SimplemadArgumentResolver.class);

	private List<SimplemadPropertyEditor> editors;

	public void setEditors(List<SimplemadPropertyEditor> editors) {
		this.editors = editors;
	}

	public void addEditor(SimplemadPropertyEditor editor) {
		if (CollectionUtil.isEmpty(editors))
			editors = new ArrayList<SimplemadPropertyEditor>();
		editors.add(editor);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		boolean isResolver = WebArgumentResolverFilter.isResolver(methodParameter);
		if (!isResolver)
			return WebArgumentResolver.UNRESOLVED;

		return resolveCustomArgument(methodParameter, webRequest);
	}
	
	private void registerEditor(BeanWrapper wrapper) {
		for (SimplemadPropertyEditor editor : editors) {
			wrapper.registerCustomEditor(editor.getEntityClass(), editor);
		}
	}

	private Object resolveCustomArgument(MethodParameter methodParameter, NativeWebRequest webRequest) {

		BeanWrapper wrapper = new BeanWrapperImpl(methodParameter.getParameterType());
		// 此处必须设为true
		wrapper.setAutoGrowNestedPaths(true);
		registerEditor(wrapper);
		try {
			String argName = methodParameter.getParameterName() + ".";
			Class<?> rootClass = methodParameter.getParameterType();
			Map<String, String[]> parameterMap = webRequest.getParameterMap();
			Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
			Iterator<Entry<String, String[]>> it = entrySet.iterator();
			while (it.hasNext()) {
				Entry<String, String[]> entry = it.next();
				String key = entry.getKey();
				String[] objArray = entry.getValue();
				if (key.startsWith(argName)) {
					key = key.substring(argName.length());
					String[] propertyKeys = key.split("\\.");
					if (objArray.length == 1) {
						PropertyValue pv = null;
						String methodName = PojoUtil.obtainGetterMethodName(propertyKeys[0]);
						Class<?> fieldType = null;
						try {
							fieldType = rootClass.getMethod(methodName).getReturnType();
							if (fieldType.isEnum()) {
								pv = new PropertyValue(propertyKeys[0], translate(fieldType, objArray[0]));
							} else {
								pv = new PropertyValue(key, translate(fieldType, objArray[0]));
							}
						} catch (NoSuchMethodException e) {
							log.error(String.format("Could not find the method: %s in the class[%s]", methodName, rootClass.getName()));
							pv = new PropertyValue(key, objArray[0]);
						} catch (SecurityException e) {
							log.error(String.format("Could not access the method: %s in the class[%s]", methodName, rootClass.getName()));
							pv = new PropertyValue(key, objArray[0]);
						}
						wrapper.setPropertyValue(pv);

					} else {
						PropertyValue pv = new PropertyValue(key, objArray);
						wrapper.setPropertyValue(pv);
					}
				}
			}
		} catch (Exception e) {
			// Continue
			e.printStackTrace();
		}
		return wrapper.getWrappedInstance();
	}

	private Object translate(Class<?> clazz, String target) {
		if(null == clazz)
			return target;
		for (SimplemadPropertyEditor editor : editors) {
			if (editor.getEntityClass().equals(clazz)) {
				editor.setAsText(target);
				return editor.getValue();
			}
		}
		return target;
	}

}
