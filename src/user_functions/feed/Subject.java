/*
 * observer pattern (abstract Subject class)
 * 	> maintains list of observers
 * 	> can attach, detach, and notify observers
 * 	> extended by User (observer of Subject) to watch for new posts
 */

package user_functions.feed;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers(NewPost post){
		for(Observer observer : observers) {
			observer.update(post);
		}
	}
}
