package com.example.taskit;

import com.example.taskit.Task.Task;
import com.example.taskit.Task.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private final TaskRepository taskRepository;

    public HomeController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Napisz kontroler który obsługuje strone główną aplikacji //
    @RequestMapping
    public String home() {
        return "index";
    }

    @RequestMapping("/taskForm")
    public String taskForm() {
        return "taskForm";
    }

    @RequestMapping("/tasks")
    public String taskList(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

}
