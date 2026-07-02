/*
 * holds flag for User and UserGroup ID validation
 * 	> referenced by User and UserGroup during object creation to check for
 * 	> id validation (no spaces; all ids are unique by default)
 * 	> flag never resets to true once set to false
 * 	> keeps a list of all IDs created (User and UserGroup)
 */

package admin_service;

import java.util.ArrayList;
import java.util.List;

public class ID_Validator {
    public static boolean allIDsNoSpaces = true;
    public static List<String> allIDs = new ArrayList<>();
    
    // registers a new id upon creation and checks for criteria compliance
    public static void registerID(String id) {
    	if(allIDsNoSpaces && id.contains(" ")) {
            allIDsNoSpaces = false;
        }
    	
        allIDs.add(id);
    }
    
    // returns true if all IDs have no spaces
    public static boolean isValid() {
        return allIDsNoSpaces;
    }
}
