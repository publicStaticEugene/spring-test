package com.spring.app1.logger;

import com.spring.app1.bean.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private final int cacheSize;
    private final List<Event> cache;

    public CacheFileEventLogger(final String fileName, final int cacheSize, final List<Event> cache) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = cache;
    }

    @Override
    public void logEvent(final Event event) {
        cache.add(event);
        if (cache.size() >= cacheSize) {
            for (final Event cachedEvent : cache) {
                super.logEvent(cachedEvent);
            }
            cache.clear();
        }
    }

    public void destroy() {
        for (final Event event : cache) {
            try {
                FileUtils.writeStringToFile(new File(getFileName()), event.toString() + "\n", "UTF-8", true);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
}
