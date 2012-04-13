package com.simplemad.web.config.editor;

import java.util.ArrayList;
import java.util.List;

public class CustomEditorGenerator {

	public static List<SimplemadPropertyEditor> genEditors() {
		List<SimplemadPropertyEditor> editors = new ArrayList<SimplemadPropertyEditor>();
		
		editors.add(new StringToDateEditor(true));
		editors.add(new AdvertisementTypePropertyEditor());
//		editors.add(new GenderPropertyEditor());
		editors.add(new ObjectIdEditor());
//		editors.add(new JobPropertyEditor());
//		editors.add(new SalaryPropertyEditor());
		
		return editors;
	}
}
