package com.simplemad.web.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.simplemad.web.config.editor.CustomEditorGenerator;
import com.simplemad.web.config.editor.SimplemadPropertyEditor;

public class SimplemadBindingInitializer implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
//		binder.registerCustomEditor(String.class,
//				new StringTrimmerEditor(false));
		for(SimplemadPropertyEditor editor : CustomEditorGenerator.genEditors()) {
			binder.registerCustomEditor(editor.getEntityClass(), editor);
		}
	}

}
