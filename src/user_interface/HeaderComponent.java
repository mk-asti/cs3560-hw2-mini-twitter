/*
 * composite pattern (leaf ui component for section headers)
 * 	> renders header above sections
 */

package user_interface;

import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class HeaderComponent implements UIComponent {

	private String text;
	
	public HeaderComponent(String text) {
		this.text = text;
	}
	
	@Override
	public JComponent render() {
		JLabel header = new JLabel(text);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 16f));
        return header;
	}
	
}
