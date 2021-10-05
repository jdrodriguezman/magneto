package com.meli.mutant.model;

import javax.validation.constraints.Pattern;
import java.util.List;

public class Dna {

    List<@Pattern(regexp = "^[ACGT]*$") String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        return "Dna{" +
                "dna=" + dna +
                '}';
    }

}
