package online.pizzacrust.canegradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CanePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().add("cane", CaneExt.class);
        project.getTasks().create("setup", SetupTask.class);
    }
}
