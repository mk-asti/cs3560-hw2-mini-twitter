package app_mini_twitter;

import javax.swing.SwingUtilities;
import admin_service.ControlPanel;

public class TwitterDriver {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			ControlPanel.getInstance().setVisible(true);
		});
	}
}
