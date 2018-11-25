package de.phib.tasket;

import de.phib.tasket.task.Task;
import de.phib.tasket.task.TaskRepository;
import de.phib.tasket.task.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

/**
 * The SpringBootApplication to start the application.
 */
@SpringBootApplication
public class TasketApplication {

    private static final Logger LOG = LoggerFactory.getLogger(TasketApplication.class);

    @Value("${tasket.generate-test-data}")
    private boolean generateTestData = false;

    /**
     * The main method.
     *
     * @param args the arguments of the main method
     */
    public static void main(String[] args) {
        TasketApplication.setupDatabase();

        SpringApplication.run(TasketApplication.class, args);
    }

    /**
     * Sets the folder for the database.
     */
    private static void setupDatabase() {
        String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = userHomeDir + File.separator + ".tasket";
        System.setProperty("derby.system.home", systemDir);
    }

    /**
     * CommandLineRunner to generate some test data.
     *
     * @param repository the TaskRepository
     * @return a CommandLineRunner
     */
    @Bean
    public CommandLineRunner generateTestData(TaskRepository repository) {
        return (args) -> {
            if (this.generateTestData) {
                LOG.info("Generating test data");

                repository.save(new Task("Go shopping", TaskStatus.TODO));
                repository.save(new Task("Clean apartment", TaskStatus.TODO));
                repository.save(new Task("Do dishes", TaskStatus.TODO));
                repository.save(new Task("Read book", TaskStatus.TODO));
                repository.save(new Task("Play with cat", TaskStatus.TODO));
            }
        };
    }

}
