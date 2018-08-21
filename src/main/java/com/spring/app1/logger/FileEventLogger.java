package com.spring.app1.logger;

import com.spring.app1.bean.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Component("fileEventLogger")
public class FileEventLogger implements EventLogger {
    private final String fileName;

    public FileEventLogger(@Value("${fileName}")final String fileName) {
        this.fileName = fileName;
    }

    String getFileName() {
        return fileName;
    }

    public void logEvent(final Event event) {
        try {
            FileUtils.writeStringToFile(new File(fileName), event.toString() + "\n",
                    Charset.forName("UTF-8"), true);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored", "unused"})
    @PostConstruct
    protected void init() throws IOException {
        System.out.println("init() " + getClass().getName());
        final File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!file.canWrite()) {
            throw new IOException("can not write to the file: " + fileName);
        }
    }

}
