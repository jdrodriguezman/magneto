package com.meli.mutant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stat {

    @JsonProperty("count_mutant_dna")
    Double countMutantDna;

    @JsonProperty("count_human_dna")
    Double countHumanDna;

    Double ratio;
}
