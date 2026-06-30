package user_interface;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class SectionComponent implements UIComponent {
	
	private final List<UIComponent> children = new ArrayList<>();

	@Override
	public JComponent render() {
		JPanel section = new JPanel();
		section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
		section.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            BorderFactory.createEmptyBorder(10, 20, 10, 10)
        ));
		
		for (UIComponent child : children) {
			JComponent renderedChild = child.render();
			renderedChild.setAlignmentX(Component.LEFT_ALIGNMENT);
			section.add(child.render());
		}
		
		return section;
	}
	
	public void add(UIComponent component) {
		children.add(component);
	}
	
	public void remove(UIComponent component) {
		children.remove(component);
	}
}