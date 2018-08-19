package com.spring.app1.logger;

import com.spring.app1.bean.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger implements EventLogger {
    private final String fileName;

    public FileEventLogger(final String fileName) {
        this.fileName = fileName;
    }

    String getFileName() {
        return fileName;
    }

    public void logEvent(final Event event) {
        try {
            FileUtils.writeStringToFile(new File(fileName), event.toString(), Charset.forName("UTF-8"), true);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

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
