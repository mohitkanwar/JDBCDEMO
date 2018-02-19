package users;

import java.util.List;

public interface UserService {
    public void create(User user);
    public List<User> getAllUsers();
}
