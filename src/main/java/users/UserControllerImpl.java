package users;

import java.util.List;

public class UserControllerImpl implements UserController {
    private UserService service = new UserServiceImpl();
    public void create(User user) {
        service.create(user);
    }

    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
}
