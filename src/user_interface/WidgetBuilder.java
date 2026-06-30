package user_interface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public interface WidgetBuilder {
    
    default JPanel buildButtonWidget(String label, int len, int hi) {
    	JPanel widget = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	JButton button = new JButton(label);
    	button.setPreferredSize(new Dimension(len, hi));
        
        widget.add(button);
        return widget;
    }
    
    default JPanel buildInputWidget(String label, int size, int len, int hi) {
    	JPanel widget = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField input = new JTextField(size);
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(len, hi));
        
        widget.add(input);
        widget.add(button);
        return widget;
    }
    
    default JLabel buildHeader(String text) {
        JLabel header = new JLabel(text);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 16f));
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        return header;
    }

    default JPanel buildSection(JLabel header, JPanel content) {
        content.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 20, 10, 10)
        ));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);
        section.add(header);
        section.add(content);
        return section;
    }
}