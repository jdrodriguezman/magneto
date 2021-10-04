package com.meli.mutant.repository.impl;

import com.meli.mutant.model.entity.Human;
import com.meli.mutant.repository.IHumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HumanRepositoryImpl implements HumanRepository {

    private final IHumanRepository humanRepository;

    @Override
    public void saveDNA(Human human) {
        humanRepository.save(human);
    }

    @Override
    public long countHuman() {
        return humanRepository.count();
    }

    @Override
    public long countMutant() {
        return humanRepository.countHumanByMutantIsTrue();
    }
}
