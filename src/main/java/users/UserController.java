package users;

import java.util.List;

public interface UserController {
    public void create(User user);
    public List<User> getAllUsers();
}
