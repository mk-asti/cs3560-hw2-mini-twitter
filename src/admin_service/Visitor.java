/*
 * visitor pattern (visitor interface)
 * 	> defines visit() method for each visitable element in the tree
 * 	> implementing classes defines function during each visit
 */
package admin_service;

import user_functions.profile.*;

public interface Visitor {
	public void visit(User user);
	public void visit(UserGroup userGroup);
	
}
