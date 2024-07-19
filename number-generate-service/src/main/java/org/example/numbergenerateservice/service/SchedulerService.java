package org.example.numbergenerateservice.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface SchedulerService {
    void addTasksToDeleteAfterTime(List<Runnable> tasks, long delay, TimeUnit timeUnit);
}
