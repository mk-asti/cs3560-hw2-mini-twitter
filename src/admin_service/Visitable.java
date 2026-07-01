/*
 * visitor pattern (visitable interface)
 * 	> used by User and UserGroup classes
 * 	> allows implemented classes to traverse the composite tree
 */

package admin_service;

public interface Visitable {
	public void accept(Visitor visitor);
}
