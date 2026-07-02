/*
 * composite pattern (ui component for the admin panel tree view)
 * 	> renders a JTree to show full composite tree hierarchy
 */

package user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import user_functions.profile.User;
import user_functions.profile.UserComponent;
import user_functions.profile.UserGroup;

public class CP_TreeUI implements UIComponent {

    private JTree tree;

    @Override
    public JPanel render() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(200, 440));

        DefaultMutableTreeNode rootNode = buildTreeNode(UserGroup.getRoot());
        tree = new JTree(new DefaultTreeModel(rootNode));

        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public java.awt.Component getTreeCellRendererComponent(
                    JTree tree, Object value, boolean selected, boolean expanded,
                    boolean leaf, int row, boolean hasFocus) {

                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                Object obj = node.getUserObject();

                if(obj instanceof User) {
                    setIcon(UIManager.getIcon("FileView.fileIcon"));
                } else if(obj instanceof UserGroup) {
                    setIcon(UIManager.getIcon("FileView.directoryIcon"));
                }

                return this;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(190, 430));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private DefaultMutableTreeNode buildTreeNode(UserComponent component) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(component);

        if(component instanceof UserGroup) {
            UserGroup group = (UserGroup) component;
            for(UserComponent member : group.getMembers()) {
                node.add(buildTreeNode(member));
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
}