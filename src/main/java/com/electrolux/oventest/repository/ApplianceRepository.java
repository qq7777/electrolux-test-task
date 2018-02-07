package com.electrolux.oventest.repository;

import com.electrolux.oventest.domain.Oven;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ApplianceRepository extends ReactiveMongoRepository<Oven, String> {
}
