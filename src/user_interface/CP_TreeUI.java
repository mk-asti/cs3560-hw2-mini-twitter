package user_interface;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import user_functions.profile.User;
import user_functions.profile.UserComponent;
import user_functions.profile.UserGroup;

public class CP_TreeUI implements UIComponent {

    private JTree tree;

    @Override
    public JPanel render() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 440));
        panel.setBorder(BorderFactory.createLineBorder(java.awt.Color.GRAY));

        DefaultMutableTreeNode rootNode = buildTreeNode(UserGroup.getRoot());
        tree = new JTree(new DefaultTreeModel(rootNode));

        JScrollPane scrollPane = new JScrollPane(tree);
        
        scrollPane.setPreferredSize(new Dimension(190, 430));
        
        panel.add(scrollPane);

        return panel;
    }

    // recursively build the tree structure
    private DefaultMutableTreeNode buildTreeNode(UserComponent component) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(component);

        if(component instanceof UserGroup) {
            UserGroup group = (UserGroup) component;
            for(UserComponent member : group.getMembers()) {
                node.add(buildTreeNode(member));  // recursive call
            }
        }

        return node;
    }

    public JTree getTree() {
        return tree;
    }
    
    public void refresh() {
        DefaultMutableTreeNode rootNode = buildTreeNode(UserGroup.getRoot());
        tree.setModel(new DefaultTreeModel(rootNode));
    }
    
    public UserComponent getSelectedComponent() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        
        if(node == null) {
        	return null;
        }
        
        Object obj = node.getUserObject();
        
        if(obj instanceof UserComponent) {
            return (UserComponent) obj;
        }
        return null;
    }
}