package org.example.numbergenerateservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.slf4j.SLF4JLogger;
import org.example.numbergenerateservice.exceptions.NoLeftNumberException;
import org.example.numbergenerateservice.models.entity.NumberEntity;
import org.example.numbergenerateservice.models.dto.response.NumberResponse;
import org.example.numbergenerateservice.repository.NumberRepository;
import org.example.numbergenerateservice.service.GeneratorNumberService;
import org.example.numbergenerateservice.service.SchedulerService;
import org.example.numbergenerateservice.utils.FormatDate;
import org.example.numbergenerateservice.utils.RandomNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class GeneratorNumberServiceImpl implements GeneratorNumberService {

    private static final TimeUnit TIME_UNIT = TimeUnit.DAYS;
    private static final Integer TIME_TO_LIVE = 1;
    private static final Long MAX_NUMBER_PER_DAY = 90000L;
    private static final String NO_LEFT_NUMBER = "no left number";

    //private static final Logger log = LoggerFactory.getLogger(SLF4JLogger.class);

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
        if (numberRepository.countByCreationDate(new Date()) == MAX_NUMBER_PER_DAY){
            log.error(NO_LEFT_NUMBER);
            throw  new NoLeftNumberException(NO_LEFT_NUMBER);
        }
        String number = getNumber();
        log.info("Generated number: {}", number);
        NumberEntity numberEntity = new NumberEntity(number);
        numberRepository.save(numberEntity);
        log.info("Save number: {} in mongodb", number);
        scheduler.addTasksToDeleteAfterTime(() -> numberRepository.deleteById(numberEntity.getId()), TIME_TO_LIVE, TIME_UNIT);
        log.info("Add ttl for number: {}", number);
        return new NumberResponse(number);
    }

    private String getNumber() {
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
