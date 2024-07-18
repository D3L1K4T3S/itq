package org.example.numbergenerateservice.service;

import java.util.concurrent.TimeUnit;

public interface SchedulerService {
    void addTaskToDeleteAfterTime(Runnable task, long delay, TimeUnit timeUnit);
}
