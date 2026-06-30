package user_interface;

import user_functions.profile.User;

public class UV_UserButtonsLogic {
    
    private final User user;
    
    public UV_UserButtonsLogic(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }
}