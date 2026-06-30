package user_interface;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

public class ButtonComponent implements UIComponent {
	
	private String label;
	private int length;
	private int height;
	private final ActionListener action;
	
	public ButtonComponent(String label, int length, int height, ActionListener action) {
		this.label = label;
		this.length = length;
		this.height = height;
		this.action = action;
	}

	@Override
	public JComponent render() {
    	JButton button = new JButton(label);
    	button.setPreferredSize(new Dimension(length, height));
    	
    	if (action != null) {
    		button.addActionListener(action);
    	}
        
        return button;
	}
	
}
