package com.meli.mutant.service;

import com.meli.mutant.model.Dna;
import com.meli.mutant.model.Stat;

public interface IMutantService {

    Boolean isMutant(Dna dna);

    Stat getStats();

}
