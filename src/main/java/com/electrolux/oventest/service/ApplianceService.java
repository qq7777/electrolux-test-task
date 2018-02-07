package com.electrolux.oventest.service;

import com.electrolux.oventest.domain.Mode;
import com.electrolux.oventest.domain.Oven;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApplianceService {

    Flux<Oven> getAll();

    Mono<Oven> save(Oven oven);

    Mono<Oven> getById(final String id);

    Mono<Oven> turnOnById(final String id);

    Mono<Oven> turnOffById(final String id);

    Mono<Oven> updateOvenParameters(Oven oven);

    Mono<Oven> setMode(final String id,final Mode mode);

    Mono<Oven> setTemp(final String id, final int temp);
}
