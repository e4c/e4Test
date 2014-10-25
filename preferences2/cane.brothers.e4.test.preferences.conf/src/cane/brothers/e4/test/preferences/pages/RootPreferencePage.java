package cane.brothers.e4.test.preferences.pages;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;

public class RootPreferencePage extends FieldEditorPreferencePage {

	public RootPreferencePage() {
		super(GRID);
	}

	@Override
	protected void createFieldEditors() {
		addField(new BooleanFieldEditor("showFilter", "show filter in view", getFieldEditorParent()));
	}

}
