/*
 * represents a single post made by a User
 * 	> stores text content and post maker identity
 * 	> distributed to followers via observer pattern
 */

package user_functions.feed;

import java.util.ArrayList;
import java.util.List;

import user_functions.profile.User;

public class NewPost {
	
	private String text;
	private User user;
	private static int totalPostCount = 0;
    private static int totalPositiveCount = 0;
	
    // increment total post count and positive content count upon post creation
	public void setText(String text) {
        this.text = text;
        totalPostCount++;
        if(checkPositive(text)) {
        	totalPositiveCount++;
        }
    }
    
	// return total post count
    public static int getTotalPostCount() { 
    	return totalPostCount; 
    }
    
    // return positive content count
    public static int getTotalPositiveCount() { 
    	return totalPositiveCount; 	
    }
    
    // helper method: check for positive words
 	private boolean checkPositive(String postText) {
 		String text = postText.toLowerCase();
 		for(String word : listPositiveWords()) {
 			if(text.contains(word)) {
 				return true;
 			}
 		}
 		
 		return false;
 	}
 	
 	// helper method: create list of positive words
 	private List<String> listPositiveWords(){
 		List<String> pos = new ArrayList<>();
 		
 		pos.add("good");
 		pos.add("happy");
 		pos.add("thank");
 		pos.add("thankful");
 		pos.add("great");
 		pos.add("fire");
 		pos.add("bet");
 		pos.add("sick");
 		pos.add("cool");
 		
 		return pos;
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
