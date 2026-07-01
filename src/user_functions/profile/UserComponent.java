/*
 * composite pattern (component interface)
 * 	> common interface for User and UserGroup
 * 	> allows User and UserGroup to be treated equally in composite tree
 */

package user_functions.profile;

public interface UserComponent {
	
	public String getID();
	public String getName();
	
}
