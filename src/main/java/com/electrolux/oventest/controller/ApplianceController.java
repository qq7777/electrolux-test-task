package com.electrolux.oventest.controller;

import com.electrolux.oventest.domain.Mode;
import com.electrolux.oventest.domain.Oven;
import com.electrolux.oventest.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ovens")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @PostMapping
    public Mono<Oven> post(@RequestBody Oven oven) {
        return applianceService.save(oven);
    }

    @GetMapping
    public Flux<Oven> getAll() {
        return applianceService.getAll();
    }

    @GetMapping("{id}")
    public Mono<Oven> getById(@PathVariable(name = "id") String id) {
        return applianceService.getById(id);
    }

    @PostMapping("update")
    public Mono<Oven> update(@RequestBody Oven oven) {
        return applianceService.updateOvenParameters(oven);
    }

    @PostMapping("temp")
    public Mono<Oven> setTemp(@RequestParam String id, @RequestParam int temp) {
        return applianceService.setTemp(id, temp);
    }

    @PostMapping("mode")
    public Mono<Oven> setMode(@RequestParam String id, @RequestParam String mode) {
        return applianceService.setMode(id, Mode.valueOf(mode));
    }

    @GetMapping("turnOn/{id}")
    public Mono<Oven> turnOn(@PathVariable(name = "id") String id) {
        return applianceService.turnOnById(id);
    }

    @GetMapping("turnOff/{id}")
    public Mono<Oven> turnOff(@PathVariable(name = "id") String id) {
        return applianceService.turnOffById(id);
    }

    @GetMapping("delete/{id}")
    public Mono<Void> delete(@PathVariable(name = "id") String id) {
        return applianceService.deleteById(id);
    }

}
