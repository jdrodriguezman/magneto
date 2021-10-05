package com.meli.mutant.controller;

import com.meli.mutant.model.Dna;
import com.meli.mutant.model.Stat;
import com.meli.mutant.service.IMutantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MutantApiImplTest {

    @InjectMocks
    private MutantApiImpl mutantApi;

    @Mock
    private IMutantService mutantService;

    @Test
    public void isMutantTest(){
        Mockito.when(mutantService.isMutant(Mockito.any())).thenReturn(true);
        ResponseEntity responseEntity = mutantApi.isMutant(new Dna());
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void noMutantTest(){
        Mockito.when(mutantService.isMutant(Mockito.any())).thenReturn(false);
        ResponseEntity responseEntity = mutantApi.isMutant(new Dna());
        Assertions.assertEquals(HttpStatus.FORBIDDEN,responseEntity.getStatusCode());
    }

    @Test
    public void statsTest(){
        Stat stat = Stat.builder()
                .countMutantDna(40.0)
                .countHumanDna(30.0)
                .ratio(0.4)
                .build();
        Mockito.when(mutantService.getStats()).thenReturn(stat);
        ResponseEntity responseEntity = mutantApi.getStats();
        Assertions.assertEquals(stat,responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}