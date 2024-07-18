package org.example.numbergenerateservice.service.impl;

import org.example.numbergenerateservice.models.entity.NumberEntity;
import org.example.numbergenerateservice.models.dto.response.NumberResponse;
import org.example.numbergenerateservice.repository.NumberRepository;
import org.example.numbergenerateservice.service.GeneratorNumberService;
import org.example.numbergenerateservice.service.SchedulerService;
import org.example.numbergenerateservice.utils.FormatDate;
import org.example.numbergenerateservice.utils.RandomNumber;
import org.springframework.stereotype.Service;

import java.awt.event.FocusAdapter;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
public class GeneratorNumberServiceImpl implements GeneratorNumberService {

    private static final TimeUnit timeUnit = TimeUnit.DAYS;
    private static final Integer timeToLive = 1;

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

    public NumberResponse generate() {
        String number = getNumber();
        NumberEntity numberEntity = new NumberEntity(number);
        numberRepository.save(numberEntity);
        scheduler.addTaskToDeleteAfterTime(() -> numberRepository.deleteById(numberEntity.getId()), timeToLive, timeUnit);
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

}
