package online.pizzacrust.canegradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import online.pizzacrust.graphitemappings.GraphiteMappings;

public class GenMappingsTask extends DefaultTask {

    public GenMappingsTask() {
        this.setGroup("canegradle");
    }

    @TaskAction
    public void onDo() {
        CaneExt caneExt = getProject().getExtensions().getByType(CaneExt.class);
        try {
            GraphiteMappings.main("server", getProject().file(caneExt.originalJar).getAbsolutePath()
                    , getProject().file("output.srg").getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
