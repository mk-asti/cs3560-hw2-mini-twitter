/* 
 * singleton class
 * 	> represents admin control window
 * 	> extends JFrame to act as main window
 * 	> only one instance of the control panel can exist at a time
 */

package admin_service;

import javax.swing.JFrame;
import user_functions.profile.UserGroup;
import user_interface.ControlPanelWindow;
import user_interface.CP_AdminButtonsLogic;

public class ControlPanel extends JFrame{
	private static ControlPanel instance;

	// create one and only one instance of a control panel
	public static ControlPanel getInstance() {
		if(instance == null) {
			synchronized(ControlPanel.class) {
				if(instance == null) {
					instance = new ControlPanel();
				}
			}
		}
		
		return instance;
	}
	
	// initialize control panel instance
	private ControlPanel() {
		UserGroup.getRoot();
		setTitle("admin control panel");
		setSize(650, 480);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CP_AdminButtonsLogic buttonLogic = new CP_AdminButtonsLogic();
		
		new ControlPanelWindow(this, buttonLogic);
		setVisible(true);
	}
}
