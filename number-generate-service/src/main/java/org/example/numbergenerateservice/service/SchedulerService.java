package org.example.numbergenerateservice.service;

import java.util.concurrent.TimeUnit;

public interface SchedulerService {
    void addTasksToDeleteAfterTime(Runnable task, long delay, TimeUnit timeUnit);
}
