/*
 * composite pattern (composite node)
 * 	> represents a group of UserComponent (Users and/or UserGroups)
 * 	> implements UserComponent and Visitable
 * 	> root group is a singleton
 */

package user_functions.profile;

import java.util.List;
import admin_service.Visitable;
import admin_service.Visitor;

import java.util.ArrayList;

public class UserGroup implements UserComponent, Visitable {
	private static UserGroup mainRoot;
	private static int nextID = 1;
	private String groupID;
	private String groupName;
	private List<UserComponent> groupMembers;
	
	// create and/or navigate to main root
	public static UserGroup getRoot() {
		if(mainRoot == null) {
			synchronized(UserGroup.class) {
				if(mainRoot == null) {
					mainRoot = new UserGroup("main root");
				}
			}
		}
		
		return mainRoot;
	}
	
	// create a user group with a group name, unique group id, and an empty list of group members
	public UserGroup (String groupName){
		this.groupID = String.format("G%04d", nextID++);
		this.groupName = groupName;
		this.groupMembers = new ArrayList<>();
	}

	@Override
	public String getID() {
		return this.groupID;
	}

	@Override
	public String getName() {
		return this.groupName;
	}
	
	public List<UserComponent> getMembers() {
		return groupMembers;
	}
	private UserGroup currentParent;

	public UserGroup getCurrentParent() { 
		return currentParent; 
	}
	
	public void setCurrentParent(UserGroup parent) { 
		this.currentParent = parent; 
	}
	
	public void addMember(UserComponent member) {
	    groupMembers.add(member);
	    if(member instanceof UserGroup) {
	        ((UserGroup) member).setCurrentParent(this);
	    }
	}
	
	public void removeMember(UserComponent member) {
		groupMembers.remove(member);
	}
	
	// switch the group a member is in by removing them from their current group and adding them to the new group
	public void switchMember(User user) {
		user.getCurrentGroup().removeMember(user);
		this.addMember(user);
		user.setCurrentGroup(this);
	}

	
	// accepts visitor objects
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
	
	@Override
	public String toString() {
	    return groupName + " (" + groupID + ")";
	}
	
}
