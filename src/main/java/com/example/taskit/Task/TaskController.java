package com.example.taskit.Task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String addTask(@RequestParam String taskName, @RequestParam String taskDescription) {
        System.out.println("Dodaję zadanie o nazwie " + taskName + " i opisie " + taskDescription);
        Task task = new Task();

        task.setName(taskName);
        task.setDescription(taskDescription);

        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/task/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        // Pobieramy zadanie o podanym identyfikatorze z bazy danych:
        Task task = taskRepository.findById(id).orElse(null);

        // Jeśli zadanie nie istnieje, wyświetlamy błąd:
        if (task == null) {
            model.addAttribute("errorMessage", "Nie znaleziono zadania o podanym identyfikatorze.");
            return "error";
        }

        System.out.println("Wyświetlam zadanie o nazwie " + task.getName() + " i opisie " + task.getDescription());

        // Dodajemy zadanie do modelu i przekierowujemy na stronę profilu zadania:
        model.addAttribute("task", task);
        return "task";
    }


}
