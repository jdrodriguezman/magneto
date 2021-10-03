package com.meli.mutant.controller;

import org.springframework.http.ResponseEntity;

public interface IMutantApi {
   ResponseEntity isMutant();

   ResponseEntity getStats();
}
