/*
 * composite pattern (leaf ui component for an input field + button pair)
 * 	> renders a text field and button side by side
 * 	> text is sent via InputActionListener for processing
 */

package user_interface;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputComponent implements UIComponent {
	
	private String label;
	private int length;
	private int height;
	private int size;
	private final InputActionListener action;
	
	public InputComponent(String label, int length, int height, int size, InputActionListener action) {
		this.label = label;
		this.length = length;
		this.height = height;
		this.size = size;
		this.action = action;
	}

	@Override
	public JComponent render() {
		JPanel widget = new JPanel();
		widget.setLayout(new BoxLayout(widget, BoxLayout.X_AXIS));
		
        JTextField input = new JTextField(size);
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(length, height));
        
        button.addActionListener(e -> {
            String textData = input.getText();
            if (action != null) {
                action.actionPerformed(textData);
            }
        });
        
        widget.add(input);
        widget.add(button);
        return widget;
	}
	
}
