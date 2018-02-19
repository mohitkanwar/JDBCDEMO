import users.User;
import users.UserController;
import users.UserControllerImpl;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args){
        User user = new User();
        user.setDateOfJoining(LocalDate.now());
        user.setFirstName("First");
        user.setLastName("Last");
        user.setUsername("myusername");

        UserController controller = new UserControllerImpl();
        controller.create(user);

        controller.getAllUsers().forEach(u->{
            System.out.println(u.getUsername());
        });
    }
}
