package org.example.numbergenerateservice.service.impl;

import org.example.numbergenerateservice.enums.ErrorCode;
import org.example.numbergenerateservice.exceptions.NoLeftNumberException;
import org.example.numbergenerateservice.models.entity.NumberEntity;
import org.example.numbergenerateservice.models.dto.response.NumberResponse;
import org.example.numbergenerateservice.repository.NumberRepository;
import org.example.numbergenerateservice.service.GeneratorNumberService;
import org.example.numbergenerateservice.service.SchedulerService;
import org.example.numbergenerateservice.utils.FormatDate;
import org.example.numbergenerateservice.utils.RandomNumber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
public class GeneratorNumberServiceImpl implements GeneratorNumberService {

    private static final TimeUnit timeUnit = TimeUnit.DAYS;
    private static final Integer timeToLive = 1;

    private static Integer count = 0;
    private static final Integer maxCount = 90000;

    private final NumberRepository numberRepository;
    private final SchedulerService scheduler;
    private final RandomNumber random;
    private final FormatDate formatDate;

    public GeneratorNumberServiceImpl(
            NumberRepository numberRepository,
            SchedulerService scheduler,
            RandomNumber random,
            FormatDate formatDate
    )
    {
        this.numberRepository = numberRepository;
        this.scheduler = scheduler;
        this.random = random;
        this.formatDate = formatDate;
    }

    public NumberResponse generate() throws NoLeftNumberException {
        if (isNotNumberLeft()){
            throw new NoLeftNumberException("no left number");
        }
        String number = getNumber();
        NumberEntity numberEntity = new NumberEntity(number);
        numberRepository.save(numberEntity);
        incrementCount();
        List<Runnable> tasks = new ArrayList<>();
        tasks.add(() -> numberRepository.deleteById(numberEntity.getId()));
        tasks.add(this::decrementCount);
        scheduler.addTasksToDeleteAfterTime(tasks, timeToLive, timeUnit);

        return new NumberResponse(number);
    }

    private String getNumber(){
        Optional<NumberEntity> numberEntity;
        String number;
        do {
            number = random.generateRandomNumber();
            number += formatDate.getDate();
            numberEntity = Optional.ofNullable(numberRepository.findByNumber(number));
        } while (numberEntity.isPresent());
        return number;
    }

    private void incrementCount(){
        count += 1;
    }

    private void decrementCount(){
        if (count > 0){
            count -= 1;
        }
    }

    private Boolean isNotNumberLeft(){
        return count + 1 > maxCount;
    }

}
