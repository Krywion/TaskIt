package com.example.taskit.User;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserRegisterController {


    private UserRepository userRepository;

    public UserRegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName,
                                             @RequestParam("email") String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        User newUser = userRepository.save(user);
        System.out.println("Dodaję użytkownika o imieniu " + newUser.getFirstName() + " i nazwisku " + newUser.getLastName());
        return ResponseEntity.ok(newUser);
    }

}
