/*
 * functional interface
 * 	> handles user inputs
 * 	> used by InputComponent to pass the user's text input when triggered
 */

package user_interface;

public interface InputActionListener {
	public void actionPerformed(String inputText);
}
