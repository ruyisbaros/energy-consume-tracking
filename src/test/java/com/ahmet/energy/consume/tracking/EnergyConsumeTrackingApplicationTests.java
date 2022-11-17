package com.ahmet.energy.consume.tracking;

import com.ahmet.energy.consume.tracking.entity.Energy;
import com.ahmet.energy.consume.tracking.repository.EnergyRep;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.io.ObjectInputStream;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EnergyConsumeTrackingApplicationTests {
    @Autowired
    private EnergyRep energyRep;
    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setup() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/energies");
    }

    //JUnit test for CREATE a specific energy-counter
    @Test
    public void createCounter() {
        Energy toCreate = new Energy("12345677", "AXA", 77.8, "50min", "2020-05-17T00:00:00Z", "2020-05-17T00:00:00Z", "2020-05-17T00:00:00Z");

        Energy response = restTemplate.postForObject(baseUrl + "/create", toCreate, Energy.class);
        assert response != null;
        Assertions.assertThat(response.getId()).isEqualTo("12345677");
    }

    //JUnit test for GET a specific energy-counter
    @Test
    public void getCounter() {
        Energy energy = restTemplate.getForObject(baseUrl + "/get_specific/{id}", Energy.class,"aff8c6b2-3919-4847-b41d-9c4182832841");
        assert energy != null;
        Assertions.assertThat(energy.getId()).isEqualTo("aff8c6b2-3919-4847-b41d-9c4182832841");

    }

    //JUnit test for GET ALL energy-counters
    @Test
    public void getAllCounters() {
        List<Energy> energyList = restTemplate.getForObject(baseUrl + "/results", List.class);
        assert energyList != null;
        Assertions.assertThat(energyList.size()).isGreaterThan(0);

    }

   //JUnit test for UPDATE a specific energy-counter
    @Test
    public void updateCounter() {
        Energy energy = restTemplate.getForObject(baseUrl + "/get_specific/{id}", Energy.class,"aff8c6b2-3919-4847-b41d-9c4182832841");
        assert energy != null;
        energy.setPowerProduced(54.4);
        energyRep.save(energy);
        Assertions.assertThat(energy.getPowerProduced()).isEqualTo(54.4);

    }

}
