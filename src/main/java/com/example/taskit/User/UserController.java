package com.example.taskit.User;


import com.example.taskit.Task.Task;
import com.example.taskit.Task.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public UserController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/task/{id}/assign")
    public String assignTask(@PathVariable Long id, Model model, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        // Pobieramy zadanie o podanym identyfikatorze z bazy danych:
        Task task = taskRepository.findById(id).orElse(null);

        // Jeśli zadanie nie istnieje, wyświetlamy błąd:
        if (task == null) {
            model.addAttribute("errorMessage", "Nie znaleziono zadania o podanym identyfikatorze.");
            return "error";
        }

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);


        System.out.println("Przypisuję zadanie o nazwie " + task.getName() + " do użytkownika " + user.getFirstName() + " " + user.getLastName());

        // Dodajemy zadanie do modelu i przekierowujemy na stronę profilu zadania:
        model.addAttribute("task", task);
        return "task";
    }
}
