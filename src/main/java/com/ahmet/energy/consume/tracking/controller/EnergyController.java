package com.ahmet.energy.consume.tracking.controller;

import com.ahmet.energy.consume.tracking.DTO.EnergyPowerProducedUpdateDTO;
import com.ahmet.energy.consume.tracking.entity.Energy;
import com.ahmet.energy.consume.tracking.service.EnergyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/energies")
@AllArgsConstructor
public class EnergyController {

    private EnergyService energyService;

    @PostMapping("/create")
    public Energy createCounter(@RequestBody Energy energy) {
        return energyService.createNew(energy);
    }

    @GetMapping("/results")
    public List<Energy> results() {
        return energyService.results();
    }

    @GetMapping("/get_specific/{id}")
    public Energy getOneResult(@PathVariable String id) {
        return energyService.getOne(id);
    }

    //If you think the counter is faulty, you can manually update it here.
    @PutMapping("/update_counter/{id}")
    public Energy updateACounter(@PathVariable String id, @RequestBody EnergyPowerProducedUpdateDTO request) {
        return energyService.updateCounter(id, request);
    }
}
