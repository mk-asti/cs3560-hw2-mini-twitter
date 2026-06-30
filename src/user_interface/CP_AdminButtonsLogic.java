package user_interface;

import javax.swing.JOptionPane;

import admin_service.UserStatsVisitor;
import user_functions.profile.User;
import user_functions.profile.UserComponent;
import user_functions.profile.UserGroup;

public class CP_AdminButtonsLogic {
	private CP_TreeUI treeUI;
	
	public void setTreeUI(CP_TreeUI treeUI) {
        this.treeUI = treeUI;
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
		if(groupname == null || groupname.isBlank()) {
			return;
		}
		
		System.out.println("adding group: " + groupname);
		
		UserGroup newGroup = new UserGroup(groupname);
	    UserGroup.getRoot().addMember(newGroup);
	    
	    if(treeUI != null) {
        	treeUI.refresh();
        }
	}

	public void switchUserGroup(String targetGroupName) {
	    if (treeUI == null || targetGroupName == null || targetGroupName.isBlank()) {
	        return;
	    }
	    
	    UserComponent selected = treeUI.getSelectedComponent();
	    
	    
	    if (!(selected instanceof User)) {
	        JOptionPane.showMessageDialog(null, "please select a user first.");
	        return;
	    }
	    
	    User user = (User) selected;
	    
	    UserGroup targetGroup = findGroupByName(UserGroup.getRoot(), targetGroupName);
	    
	    if (targetGroup != null) {
	        targetGroup.switchMember(user);
	        System.out.println("moved " + user.getName() + " to group: " + targetGroupName);
	        
	        treeUI.refresh();
	    } else {
	        JOptionPane.showMessageDialog(null, "group '" + targetGroupName + "' not found.");
	    }
	}

	private UserGroup findGroupByName(UserGroup currentGroup, String name) {
	    if (currentGroup.getName().equalsIgnoreCase(name)) {
	        return currentGroup;
	    }
	    
	    for (UserComponent member : currentGroup.getMembers()) {
	        if (member instanceof UserGroup) {
	            UserGroup found = findGroupByName((UserGroup) member, name);
	            if (found != null) {
	                return found;
	            }
	        }
	    }
	    return null;
	}
	
    public void showUserView() {
    	if(treeUI == null) return;
       
    	System.out.println("opening user view window...");
    	
    	UserComponent selected = treeUI.getSelectedComponent();
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
