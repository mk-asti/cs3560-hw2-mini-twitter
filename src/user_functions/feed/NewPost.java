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
