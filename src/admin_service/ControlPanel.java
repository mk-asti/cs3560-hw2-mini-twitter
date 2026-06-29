package admin_service;

import javax.swing.JFrame;
import user_functions.profile.UserGroup;

public class ControlPanel extends JFrame {
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
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
