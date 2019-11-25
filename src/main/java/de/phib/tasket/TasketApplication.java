package de.phib.tasket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
