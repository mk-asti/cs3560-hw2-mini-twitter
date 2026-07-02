/*
 * composite pattern (ui component for the admin panel right-side controls)
 * 	> renders admin control buttons, boxes, and borders
 * 	> delegates all button click actions to CP_AdminButtonsLogic
 */

package user_interface;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class CP_AdminButtonsUI implements UIComponent {
	
	private final CP_AdminButtonsLogic controller;

	public CP_AdminButtonsUI(CP_AdminButtonsLogic controller) {
		this.controller = controller;
	}

	@Override
	public JPanel render() {
	    JPanel window = new JPanel();
	    window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));
	    window.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    
	    // begin: admin controls section
	    SectionComponent adminSection = new SectionComponent();
	    adminSection.add(new HeaderComponent("admin controls"));
	    
	    // admin section grid layout
	    JPanel adminGrid = new JPanel(new GridLayout(4, 1, 10, 10));
	    adminGrid.setMaximumSize(new Dimension(370, 200));
	    adminGrid.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
	    
	    // add component to admin section
	    adminGrid.add(new InputComponent("new user", 150, 30, 15, text -> controller.addUser(text)).render());
	    adminGrid.add(new InputComponent("new group", 150, 30, 15, text -> controller.addGroup(text)).render());
	    adminGrid.add(new InputComponent("switch group", 150, 30, 15, text -> controller.switchUserGroup(text)).render());
	    
	    // open user view + validate IDs on the same row in admin panel
	    JPanel bottomRow = new JPanel(new GridLayout(1, 2, 10, 10));
	    bottomRow.add(new ButtonComponent("open user view", 150, 30, e -> controller.showUserView()).render());
	    bottomRow.add(new ButtonComponent("validate IDs", 150, 30, e -> controller.validateIDs()).render());
	    adminGrid.add(bottomRow);
	    
	    adminSection.add(() -> adminGrid);
	    
	    window.add(adminSection.render());
	    
	    window.add(Box.createVerticalStrut(20));
	    
	    // begin: stats controls section
	    SectionComponent statsSection = new SectionComponent();
	    statsSection.add(new HeaderComponent("service stats"));
	    
	    // stats section grid layout
	    JPanel statsGrid = new JPanel(new GridLayout(3, 2, 10, 10));
	    statsGrid.setMaximumSize(new Dimension(370, 150));
	    statsGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
	    // add component to stats section
	    statsGrid.add(new ButtonComponent("user total", 150, 30, e -> controller.showTotalUsers()).render());
	    statsGrid.add(new ButtonComponent("group total", 150, 30, e -> controller.showTotalGroups()).render());
	    statsGrid.add(new ButtonComponent("post total", 150, 30, e -> controller.showTotalPosts()).render());
	    statsGrid.add(new ButtonComponent("positivity ratio", 150, 30, e -> controller.showPositivePercentage()).render());
	    
	    // project 3 addition
	    statsGrid.add(new ButtonComponent("last updated user", 150, 30, e -> controller.showLastUpdatedUser()).render());
	    // - end
	    
	    statsSection.add(() -> statsGrid);
	    
	    window.add(statsSection.render());
	    
	    return window;
	}
}
