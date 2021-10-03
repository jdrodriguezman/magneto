package com.meli.mutant.service;

import java.util.Optional;

public interface IMutantService {

    Boolean isMutant(String[] dna);

    Optional getStats();

}
