/*******************************************************************************
 * Copyright (c) 2010 - 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Lars Vogel <lars.Vogel@gmail.com> - Bug 419770
 *******************************************************************************/
package cane.brothers.e4.test.preferences.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SamplePart {

	private Text filterText;
	private TableViewer tableViewer;

	private EasyViewerFilter filter;

	// not working
	@Inject
	@Preference("showFilter")
	public boolean prefShowFilterEasy;

	@Inject
	@Preference(value = "showFilter", nodePath = "cane.brothers.e4.test.preferences")
	public boolean prefShowFilterHard;

	@PostConstruct
	public void createComposite(Composite parent,
			@Preference("showFilter") boolean showFilter) {
		parent.setLayout(new GridLayout(1, false));

		filterText = new Text(parent, SWT.BORDER);
		filterText.setMessage("Enter text to filter table entries");
		// filterText.addModifyListener(new ModifyListener() {
		// @Override
		// public void modifyText(ModifyEvent e) {
		// // filter
		// System.out.println();
		// if (e.getSource() == filterText) {
		// if (filter != null && tableViewer != null
		// && filterText.getText() != null) {
		// filter.setSearchText(filterText.getText());
		// tableViewer.refresh();
		// }
		// }
		//
		// }
		// });

		// New to support the search
//		filterText.addKeyListener(new KeyAdapter() {
//			public void keyReleased(KeyEvent ke) {
//				filter.setSearchText(filterText.getText());
//				tableViewer.refresh();
//			}
//		});
		
		filterText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tableViewer = new TableViewer(parent);

		tableViewer.add("Sample item 1");
		tableViewer.add("Sample item 2");
		tableViewer.add("Sample item 3");
		tableViewer.add("Sample item 4");
		tableViewer.add("Sample item 5");
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

		//filter = new EasyViewerFilter();
		//tableViewer.addFilter(filter);

		reactOnPrefFilterChange(showFilter);
	}

	@Inject
	@Optional
	public void reactOnPrefFilterChange(
			@Preference("showFilter") boolean showFilter) {
		System.out.println("React on a change in preferences with filter = "
				+ showFilter);
		if ((filterText != null) && !filterText.isDisposed()) {
			filterText.setVisible(showFilter);
		}

	}

	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();

		System.out.println("prefShowFilterEasy is " + prefShowFilterEasy);
		System.out.println("prefShowFilterHard is " + prefShowFilterHard);
	}

}