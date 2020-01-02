package de.phib.tasket.web.dailylog;

import de.phib.tasket.config.InfoAppProperties;
import de.phib.tasket.data.collection.Collection;
import de.phib.tasket.data.collection.CollectionService;
import de.phib.tasket.data.item.task.Task;
import de.phib.tasket.data.item.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class DailyLogWebController {

    private CollectionService collectionService;

    private TaskRepository taskRepository;

    private String appVersion;

    @Autowired
    public DailyLogWebController(CollectionService collectionService, TaskRepository taskRepository, InfoAppProperties infoAppProperties) {
        this.collectionService = collectionService;
        this.taskRepository = taskRepository;
        this.appVersion = infoAppProperties.getVersion();
    }

    @GetMapping("/dailylog")
    public String renderDailyLog(Model model, @ModelAttribute("editTask") Task editTask) {
        LocalDate today = LocalDate.now();
        return this.renderDailyLog(model, editTask, today);
    }

    @GetMapping("/dailylog/{date}")
    public String renderDailyLog(Model model, @ModelAttribute("editTask") Task editTask, @PathVariable("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return renderDailyLog(model, editTask, localDate);
    }

    private String renderDailyLog(Model model, @ModelAttribute("editTask") Task editTask, LocalDate date) {
        // TODO: Remove editTask, if not really needed
        // TODO: Handle invalid date - DateTimeParseException
        model.addAttribute("appVersion", this.appVersion);
        model.addAttribute("localDate", date);
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("previousDay", date.minusDays(1));
        model.addAttribute("nextDay", date.plusDays(1));

        Optional<Collection> collection = collectionService.findByLocalDate(date);

        if (!collection.isPresent()) {
            model.addAttribute("dailyLog", new Collection(date));
            return "dailylog/emptyDailyLog";
        }

        collection.ifPresent(value -> {
            model.addAttribute("collection", value);

            // TODO: Maybe only need to add the collection to the model?
            model.addAttribute("events", value.getEvents());
            model.addAttribute("tasks", value.getTasks());
            model.addAttribute("notes", value.getNotes());
        });

        // TODO: Remove, if not needed
        //model.addAttribute("addTask", new Task());
        model.addAttribute("dailyLog", collection.get());

        return "dailylog/dailylog";
    }

    @PostMapping(path = "/dailylog")
    public String createDailyLog(/*@RequestBody*/Collection collection) {
        // FIXME: Check, if a collection with the given localDate already exists. If yes, then return a 409 error.
        this.collectionService.save(collection);
        return "redirect:/dailylog/" + collection.getLocalDate();
    }

    // TODO: Check, if following methods are still neded


    @GetMapping("/dashboard2")
    public String renderDashboard2(Model model, @ModelAttribute("editTask") Task editTask) {
        Iterable<Task> tasks = taskRepository.findAll();

        model.addAttribute("tasks", tasks);
        model.addAttribute("addTask", new Task());
        model.addAttribute("appVersion", this.appVersion);

        return "dashboard2";
    }

    @PostMapping(path = "/dailylog/add")
    public String saveTask(Task task) {
        taskRepository.save(task);
        return "redirect:/dashboard2";
    }

    @GetMapping(path = "/dailylog/edit/{id}")
    public String editTask(Model model, @PathVariable(value = "id") String id, RedirectAttributes attributes) {
        attributes.addFlashAttribute("editTask", taskRepository.findById(id));
        return "redirect:/dailylog";
    }

    @PostMapping(path = "/dailylog/edit/{id}")
    public String saveEditTask(Task task) {
        taskRepository.save(task);
        return "redirect:/dailylog";
    }

    @GetMapping(path = "/dailylog/delete/{id}")
    public String deleteTask(@PathVariable(name = "id") String id) {
        taskRepository.deleteById(id);
        return "redirect:/dailylog";
    }


}
