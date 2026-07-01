/*
 * logic class for the admin panel buttons
 * 	> controls all admin window actions
 */

package user_interface;

import javax.swing.JOptionPane;

import admin_service.UserStatsVisitor;
import user_functions.profile.User;
import user_functions.profile.UserComponent;
import user_functions.profile.UserGroup;

public class CP_AdminButtonsLogic {
	private CP_TreeUI treeUI;
	private CP_TreeLogic treeLogic;
	
	public void setTreeUI(CP_TreeUI treeUI) {
	    this.treeUI = treeUI;
	    this.treeLogic = new CP_TreeLogic(treeUI);
	}
	
	public void addUser(String username) {
        if(username == null || username.isBlank()) {
        	return;
        }
        
        System.out.println("adding user: " + username);
        new User(username);
        
        if(treeUI != null) {
        	treeUI.refresh();
        }
    }
	
	public void addGroup(String groupname) {
	    if(groupname == null || groupname.isBlank()) return;
	    
	    UserGroup newGroup = new UserGroup(groupname);
	    UserComponent selected = (treeLogic != null) ? treeLogic.getSelectedComponent() : null;
	    
	    if(selected instanceof UserGroup) {
	        ((UserGroup) selected).addMember(newGroup);
	    } else {
	        UserGroup.getRoot().addMember(newGroup);  // default to root
	    }
	    
	    if(treeUI != null) treeUI.refresh();
	}

	public void switchUserGroup(String targetGroupName) {
	    if (treeUI == null || targetGroupName == null || targetGroupName.isBlank()) {
	        return;
	    }
	    
	    UserComponent selected = treeLogic.getSelectedComponent();
	    UserGroup targetGroup = treeLogic.findGroupByName(targetGroupName);
	    
	    if(targetGroup == null) {
	        JOptionPane.showMessageDialog(null, "group '" + targetGroupName + "' not found.");
	        return;
	    }
	    
	    if(selected instanceof User) {
	        // move user to target group
	        targetGroup.switchMember((User) selected);
	        System.out.println("moved user " + selected.getName() + " to group: " + targetGroupName);
	        
	    } else if(selected instanceof UserGroup) {
	        // move group into target group
	        UserGroup selectedGroup = (UserGroup) selected;
	        selectedGroup.getCurrentParent().removeMember(selectedGroup);  // remove from current parent
	        targetGroup.addMember(selectedGroup);                          // add to target
	        System.out.println("moved group " + selected.getName() + " to group: " + targetGroupName);
	        
	    } else {
	        JOptionPane.showMessageDialog(null, "please select a user or group first.");
	        return;
	    }
	    
	    treeUI.refresh();
	}
	
    public void showUserView() {
    	if(treeUI == null) return;
       
    	System.out.println("opening user view window...");
    	
    	UserComponent selected = treeLogic.getSelectedComponent();
    	if(selected instanceof User) {
            new UserViewWindow((User) selected);
        } else {
            JOptionPane.showMessageDialog(null, "please select a user first.");
        }
    }

    public void showTotalUsers() {
        System.out.println("calculating total users...");
        
        UserStatsVisitor visitor = new UserStatsVisitor();
        UserGroup.getRoot().accept(visitor);
        JOptionPane.showMessageDialog(null, "total users: " + visitor.getUserCount());
    }
    
    public void showTotalGroups() {
        System.out.println("calculating total users...");
        
        UserStatsVisitor visitor = new UserStatsVisitor();
        UserGroup.getRoot().accept(visitor);
        JOptionPane.showMessageDialog(null, "total groups: " + visitor.getGroupCount());
    }
    
    public void showTotalPosts() {
    	System.out.println("calculating total posts...");
    	
    	UserStatsVisitor visitor = new UserStatsVisitor();
        UserGroup.getRoot().accept(visitor);
        JOptionPane.showMessageDialog(null, "total posts: " + visitor.getPostCount());
    }
    
    public void showPositivePercentage() {
    	System.out.println("calculating positive word post percentage...");
    	
    	UserStatsVisitor visitor = new UserStatsVisitor();
        UserGroup.getRoot().accept(visitor);
        JOptionPane.showMessageDialog(null, 
            String.format("positive post ratio: %.2f%%", visitor.calculatePercentage()));
    }
}
