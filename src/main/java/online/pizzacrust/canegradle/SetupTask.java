package online.pizzacrust.canegradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.TaskAction;

public class SetupTask extends DefaultTask {

    public SetupTask() {
        this.setGroup("canegradle");
    }

    @TaskAction
    public void onTask() {
        CaneExt caneExt = getProject().getExtensions().getByType(CaneExt.class);
        try {
            new AutoJarRemapper(getProject().file(caneExt.originalJar), getProject().file(caneExt
                    .srgFile), getProject().file(caneExt.remappedJar)).process();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        getProject().getDependencies().add(JavaPlugin.COMPILE_CONFIGURATION_NAME, getProject()
                .file(caneExt.remappedJar));
    }

}
