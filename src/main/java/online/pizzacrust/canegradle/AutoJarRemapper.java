package online.pizzacrust.canegradle;

import net.md_5.specialsource.SpecialSource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AutoJarRemapper {

    private final File originalJar;
    private final File srg;
    private final File remappedJarTarget;

    public AutoJarRemapper(File originalJar, File srg, File remappedJarTarget) {
        this.originalJar = originalJar;
        this.srg = srg;
        this.remappedJarTarget = remappedJarTarget;
    }

    public void process() throws Exception {
        Files.copy(getClass().getClassLoader().getResourceAsStream("template.zip"),
                remappedJarTarget.toPath(), StandardCopyOption.REPLACE_EXISTING);
        if (!originalJar.exists() || !srg.exists()) {
            throw new RuntimeException();
        }
        SpecialSource.main(new String[] { "--srg-in", srg.getAbsolutePath(), "--live-remapped",
                "--in-jar",
                originalJar.getAbsolutePath(), "--out-jar", remappedJarTarget.getAbsolutePath(),
                "--quiet"
        });
    }

}
