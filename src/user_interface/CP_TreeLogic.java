/*
 * logic class for tree selection operations in the admin panel
 */

package user_interface;

import user_functions.profile.User;
import user_functions.profile.UserComponent;
import user_functions.profile.UserGroup;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class CP_TreeLogic {

    private final CP_TreeUI treeUI;

    public CP_TreeLogic(CP_TreeUI treeUI) {
        this.treeUI = treeUI;
    }

    public UserComponent getSelectedComponent() {
        JTree tree = treeUI.getTree();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if(node == null) return null;

        Object obj = node.getUserObject();
        if(obj instanceof UserComponent) {
            return (UserComponent) obj;
        }
        return null;
    }

    public User getSelectedUser() {
        UserComponent selected = getSelectedComponent();
        if(selected instanceof User) return (User) selected;
        return null;
    }

    public UserGroup getSelectedGroup() {
        UserComponent selected = getSelectedComponent();
        if(selected instanceof UserGroup) return (UserGroup) selected;
        return null;
    }

    public UserGroup findGroupByName(String name) {
        return searchGroup(UserGroup.getRoot(), name);
    }

    private UserGroup searchGroup(UserGroup current, String name) {
        if(current.getName().equalsIgnoreCase(name)) return current;
        for(UserComponent member : current.getMembers()) {
            if(member instanceof UserGroup) {
                UserGroup found = searchGroup((UserGroup) member, name);
                if(found != null) return found;
            }
        }
        return null;
    }
}