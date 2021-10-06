package com.meli.mutant.repository.impl;

import com.meli.mutant.model.entity.Human;
import com.meli.mutant.repository.IHumanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class HumanRepositoryImplTest {

    @InjectMocks
    private HumanRepositoryImpl humanRepositoryImpl;

    @Mock
    private IHumanRepository humanRepository;

    @Test
    public void countHumanTest(){
        Long expected = 40L;
        Mockito.when(humanRepository.count()).thenReturn(expected);
        Long res = humanRepositoryImpl.countHuman();
        Assertions.assertNotNull(res);
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void countMutantTest(){
        Long expected = 20L;
        Mockito.when(humanRepository.countHumanByMutantIsTrue()).thenReturn(expected);
        Long res = humanRepositoryImpl.countMutant();
        Assertions.assertNotNull(res);
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void saveHumanTest(){
        Human human = new Human();
        human.setId(1);
        human.setDna("dna");
        human.setMutant(true);
        Mockito.when(humanRepository.save(human)).thenReturn(human);
        humanRepositoryImpl.saveDNA(human);
    }

}