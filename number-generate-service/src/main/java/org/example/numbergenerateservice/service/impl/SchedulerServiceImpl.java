package org.example.numbergenerateservice.service.impl;

import org.example.numbergenerateservice.service.SchedulerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    private final Integer corePoolSize = 1;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(corePoolSize);
    public void addTasksToDeleteAfterTime(List<Runnable> tasks, long delay, TimeUnit timeUnit) {
        for (Runnable task : tasks) {
            scheduler.schedule(task, delay, timeUnit);
        }
    }
}
