/*
 * represents a single post made by a User
 * 	> stores text content and post maker identity
 * 	> distributed to followers via observer pattern
 */

package user_functions.feed;

import user_functions.profile.User;

public class NewPost {
	
	private String text;
	private User user;
	
	public void setText (String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setUser (User user){
		this.user = user;
	}
	
	public User getUser (){
		return user;
	}

}
