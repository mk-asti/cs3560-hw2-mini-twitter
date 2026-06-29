package admin_service;

import user_functions.profile.*;

public interface Visitor {
	public void visit(User user);
	public void visit(UserGroup userGroup);
	
}
