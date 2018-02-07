package com.electrolux.oventest;

import com.electrolux.oventest.domain.Mode;
import com.electrolux.oventest.domain.Oven;
import com.electrolux.oventest.service.ApplianceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OvenApplicationTests {

    @Autowired
    private ApplianceService applianceService;

    private static final String DEFAULT_ID = "id_1";
    private static final String DEFAULT_ID_2= "id_2";

    private Oven oven1;
    private Oven oven2;

    @Before
    public void createParameters() {

        oven1 = Oven.builder()
                .id(DEFAULT_ID)
                .running(false)
                .mode(Mode.DEFAULT)
                .temp(100)
                .build();

        oven2 = Oven.builder()
                .id(DEFAULT_ID_2)
                .running(true)
                .mode(Mode.GRILL)
                .temp(222)
                .build();

    }

    @Test
    public void contextLoads() {
    }

    @Test
    @Rollback
    public void shouldSaveAndGet() {

        applianceService.save(oven1).block();

        Oven ovenFound = applianceService.getById(DEFAULT_ID).block();
        assert ovenFound.getId().equals(DEFAULT_ID);
        assert ovenFound.getTemp() == 100;
        assert ovenFound.getMode().equals(Mode.DEFAULT);
    }

    @Test
    public void shouldSaveAndGetAll() {
        applianceService.save(oven1).block();
        applianceService.save(oven2).block();
        Mono<List<Oven>> ovens = applianceService.getAll().collectList();
        List<String> ovenIds = ovens.flux().blockFirst().stream().map(Oven::getId).collect(Collectors.toList());
        assert ovenIds.contains(oven1.getId());
        assert ovenIds.contains(oven2.getId());
    }

    @Test
    @Rollback
    public void shouldChangeMode() {
        Oven ovenFound = applianceService.getById(DEFAULT_ID).block();
        Oven ovenFoundAfterChanging = applianceService.setMode(ovenFound.getId(), Mode.BOTH_SIDES).block();
        assert ovenFoundAfterChanging.getMode().equals(Mode.BOTH_SIDES);
    }

    @Test
    @Rollback
    public void shouldChangeTemp() {
        Oven ovenFound = applianceService.getById(DEFAULT_ID).block();
        Oven ovenFoundAfterChanging = applianceService.setTemp(ovenFound.getId(), 77).block();
        assert ovenFoundAfterChanging.getTemp() == 77;
    }

    @Test
    @Rollback
    public void shouldUpdateParameters() {
        Oven ovenFound = applianceService.getById(DEFAULT_ID).block();
        ovenFound.setTemp(33);
        ovenFound.setMode(Mode.TOP_SIDE);
        ovenFound.setRunning(true);
        applianceService.updateOvenParameters(ovenFound);
    }
}
