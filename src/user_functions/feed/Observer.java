/*
 * observer pattern (observer interface)
 * 	> used by User for live notification implementation
 */

package user_functions.feed;

public interface Observer {

	public void update(NewPost post);
	
}
