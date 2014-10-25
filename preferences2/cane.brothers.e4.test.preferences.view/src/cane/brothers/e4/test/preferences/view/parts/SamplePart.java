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
package cane.brothers.e4.test.preferences.view.parts;

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

import cane.brothers.e4.test.preferences.PConstants;

public class SamplePart {

	private Text filterText;
	private TableViewer tableViewer;

	private EasyViewerFilter filter;

	// not working - wrong node path without set
	@Inject
	@Preference("showFilter")
	public boolean prefShowFilterEasy;

	// not working - wrong node path with set
	@Inject
	@Preference(value = "showFilter", nodePath = "cane.brothers.e4.test.preferences")
	public boolean prefShowFilterHard;
	
	// working 
	@Inject
	@Preference(value = "showFilter", nodePath = "cane.brothers.e4.test.preferences.conf")
	public boolean prefShowFilterConf;

	// working 
	@Inject
	@Preference(value = PConstants.P_SHOW_FILTER, nodePath = PConstants.CONF_NODE)
	public boolean prefShowFilterConst;

	@PostConstruct
	public void createComposite(Composite parent,
			@Preference(value = PConstants.P_SHOW_FILTER, 
			nodePath = PConstants.CONF_NODE) boolean showFilter) {
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

		// initial react on default filter value
		reactOnPrefFilterChange(showFilter);
	}

	/**
	 * preferences from another plug-in will works, if
	 * set property value and node path obviously.
	 * In other case injected preference will be null, 
	 * because default node path is current plug-in.
	 * 
	 * this function will be called immediately after any 
	 * changes of showFilter preference.
	 * 
	 * 
	 * @param showFilter
	 */
	@Inject
	@Optional
	public void reactOnPrefFilterChange(
			@Preference(value = PConstants.P_SHOW_FILTER, nodePath = PConstants.CONF_NODE) boolean showFilter) {
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
		System.out.println("prefShowFilterConf is " + prefShowFilterConf);
		System.out.println("prefShowFilterConst is " + prefShowFilterConst);
	}

}