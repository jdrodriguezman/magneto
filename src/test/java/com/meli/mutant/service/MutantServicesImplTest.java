package com.meli.mutant.service;

import com.meli.mutant.model.Dna;
import com.meli.mutant.model.Stat;
import com.meli.mutant.repository.impl.HumanRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MutantServicesImplTest {

    @InjectMocks
    private MutantServicesImpl mutantServices;

    @Mock
    private HumanRepositoryImpl humanRepository;

    @Test
    public void noMutantTest(){
        Dna dna = new Dna();
        List<String> sec = new ArrayList<>();
        sec.add("ATGCGA");
        sec.add("CAGTGC");
        sec.add("TTATTT");
        sec.add("AGACGG");
        sec.add("GCGTCA");
        sec.add("TCACTG");
        dna.setDna(sec);
        Assertions.assertFalse(mutantServices.isMutant(dna));
    }

    @Test
    public void mutantDiagonalTest(){
        Dna dna = new Dna();
        List<String> sec = new ArrayList<>();
        sec.add("ATGCGA");
        sec.add("CAGTGC");
        sec.add("TTATTT");
        sec.add("AGAAGG");
        sec.add("GCGTAA");
        sec.add("TCACTG");
        dna.setDna(sec);
        Assertions.assertTrue(mutantServices.isMutant(dna));
    }

    @Test
    public void mutantHorizontalTest(){
        Dna dna = new Dna();
        List<String> sec = new ArrayList<>();
        sec.add("ATGCGA");
        sec.add("CAGTGC");
        sec.add("ATTTTT");
        sec.add("AGACGG");
        sec.add("GCGTCA");
        sec.add("TCACTG");
        dna.setDna(sec);
        Assertions.assertTrue(mutantServices.isMutant(dna));
    }

    @Test
    public void mutantVerticalTest(){
        Dna dna = new Dna();
        List<String> sec = new ArrayList<>();
        sec.add("ATGCGA");
        sec.add("CAGTGC");
        sec.add("TTATTT");
        sec.add("AGATGG");
        sec.add("GCGTCA");
        sec.add("TCACTG");
        dna.setDna(sec);
        Assertions.assertTrue(mutantServices.isMutant(dna));
    }

    @Test
    public void mutantReverseDiagonalTest(){
        Dna dna = new Dna();
        List<String> sec = new ArrayList<>();
        sec.add("ATGCGA");
        sec.add("CAGTAC");
        sec.add("TTAATT");
        sec.add("AGACGG");
        sec.add("GAGTCA");
        sec.add("TCACTG");
        dna.setDna(sec);
        Assertions.assertTrue(mutantServices.isMutant(dna));
    }

    @Test
    public void getStatsTest() {
        Mockito.when(humanRepository.countMutant()).thenReturn(40L);
        Mockito.when(humanRepository.countHuman()).thenReturn(100L);
        Stat stat = mutantServices.getStats();
        Assertions.assertNotNull(stat);
        Assertions.assertEquals(0.4, stat.getRatio());
    }

}