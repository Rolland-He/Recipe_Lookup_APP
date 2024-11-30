package view.concrete_page;

import interface_adapter.user_profile.UserProfileState;
import view.PageView;

/**
 * Concrete user profile gui view.
 */
public class UserProfileConcrete implements PageView<UserProfileState> {

    @Override
    public void update(UserProfileState state) {
        System.out.println("User Profile View updated");
    }
}
