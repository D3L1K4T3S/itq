package org.example.numbergenerateservice.service;

import org.example.numbergenerateservice.exceptions.NoLeftNumberException;
import org.example.numbergenerateservice.models.dto.response.NumberResponse;
import org.example.numbergenerateservice.models.entity.NumberEntity;
import org.example.numbergenerateservice.repository.NumberRepository;
import org.example.numbergenerateservice.service.impl.GeneratorNumberServiceImpl;
import org.example.numbergenerateservice.utils.FormatDate;
import org.example.numbergenerateservice.utils.RandomNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GeneratorNumberServiceTest {
    @Mock
    NumberRepository numberRepository;

    @Mock
    RandomNumber randomNumber;

    @Mock
    FormatDate formatDate;

    @Mock
    SchedulerService schedulerService;

    @InjectMocks
    GeneratorNumberServiceImpl generatorNumberService;

    @Test
    @DisplayName("Успешная генерация номера")
    void testGenerateSuccess() {
        when(numberRepository.countByCreationDate(any(Date.class))).thenReturn(1L);

        NumberResponse response = generatorNumberService.generate();

        verify(numberRepository, times(1)).countByCreationDate(any(Date.class));
        verify(numberRepository, times(1)).save(any(NumberEntity.class));
        verify(schedulerService, times(1)).addTasksToDeleteAfterTime(any(Runnable.class), anyLong(), any(TimeUnit.class));
    }

    @Test
    @DisplayName("Неуспешная генерация номера")
    void testGenerateBad() {
        when(numberRepository.countByCreationDate(any(Date.class))).thenReturn(90000L);

        assertThrows(NoLeftNumberException.class, () -> generatorNumberService.generate());
        verify(numberRepository, times(1)).countByCreationDate(any(Date.class));
    }
}
