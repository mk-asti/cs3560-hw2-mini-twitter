/*
 * composite pattern (composite node)
 * 	> represents a group of UserComponent (Users and/or UserGroups)
 * 	> implements UserComponent and Visitable
 * 	> root group is a singleton
 */

package user_functions.profile;

import java.util.List;
import java.util.Map;

import admin_service.ID_Validator;
import admin_service.Visitable;
import admin_service.Visitor;

import java.util.ArrayList;

public class UserGroup implements UserComponent, Visitable {
	private static UserGroup mainRoot;
	private String groupID;
	private String groupName;
	private List<UserComponent> groupMembers;
	
	// project 3 additions
	private long creationTime;
	// - end
	
	// create a user group with a group name, unique group id, and an empty list of group members
	public UserGroup (String groupName){
		this.groupID = groupName;
		this.groupName = groupName;
		this.groupMembers = new ArrayList<>();
		this.creationTime = System.currentTimeMillis();
		
		ID_Validator.registerID(this.groupID);
	}
		
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
	
	// project 3 additions
	public long getCreationTime() {
	    return creationTime;
	}
	// - end
	
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
	    return groupName;
	}
	
}
