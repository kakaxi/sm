package com.simplemad.web.config.editor;

import java.beans.PropertyEditorSupport;

public abstract class SimplemadPropertyEditor extends PropertyEditorSupport {

	public abstract Class<?> getEntityClass();
}
