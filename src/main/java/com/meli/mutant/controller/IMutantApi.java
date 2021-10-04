package com.meli.mutant.controller;

import com.meli.mutant.model.Dna;
import org.springframework.http.ResponseEntity;

public interface IMutantApi {
   ResponseEntity isMutant(Dna dna);

   ResponseEntity getStats();
}
