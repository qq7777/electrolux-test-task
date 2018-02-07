package com.electrolux.oventest.service;

import com.electrolux.oventest.domain.Mode;
import com.electrolux.oventest.domain.Oven;
import com.electrolux.oventest.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    @Override
    public Flux<Oven> getAll() {
        return applianceRepository.findAll();
    }

    @Override
    public Mono<Oven> save(Oven oven) {
        return applianceRepository.save(oven);
    }

    @Override
    public Mono<Oven> getById(String id) {
        return applianceRepository
                .findById(id);
    }

    @Override
    public Mono<Oven> turnOnById(String id) {
        Oven oven = applianceRepository.findById(id).block();
        oven.setRunning(true);
        return applianceRepository.save(oven);
    }

    @Override
    public Mono<Oven> turnOffById(String id) {
        Oven oven = applianceRepository.findById(id).block();
        oven.setRunning(false);
        return applianceRepository.save(oven);
    }

    @Override
    public Mono<Oven> updateOvenParameters(Oven oven) {
        Oven ovenFromRepository = applianceRepository.findById(oven.getId()).block();
        ovenFromRepository.setRunning(oven.isRunning());
        ovenFromRepository.setMode(oven.getMode());
        ovenFromRepository.setTemp(oven.getTemp());
        return applianceRepository.save(ovenFromRepository);
    }

    @Override
    public Mono<Oven> setMode(String id, Mode mode) {
        Oven ovenFromRepository = applianceRepository.findById(id).block();
        ovenFromRepository.setMode(mode);
        return applianceRepository.save(ovenFromRepository);
    }

    @Override
    public Mono<Oven> setTemp(String id, int temp) {
        Oven ovenFromRepository = applianceRepository.findById(id).block();
        ovenFromRepository.setTemp(temp);
        return applianceRepository.save(ovenFromRepository);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return applianceRepository.deleteById(id);
    }


}
