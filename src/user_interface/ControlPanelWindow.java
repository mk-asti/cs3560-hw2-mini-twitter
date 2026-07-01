/*
 * assembles the admin control panel window
 * 	> combines left (tree view) and right (admin control) panels
 */

package user_interface;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import admin_service.ControlPanel;

public class ControlPanelWindow {
	
	// constructor
	public ControlPanelWindow(ControlPanel window, CP_AdminButtonsLogic buttonLogic) {
		CP_TreeUI treeView = new CP_TreeUI();
		UIComponent buttonView = new CP_AdminButtonsUI(buttonLogic);
		
		window.add(treeView.render(), BorderLayout.WEST);
		window.add(buttonView.render(), BorderLayout.CENTER);
		
		buttonLogic.setTreeUI(treeView);
	}
}
