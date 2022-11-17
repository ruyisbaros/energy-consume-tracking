package com.ahmet.energy.consume.tracking.service;

import com.ahmet.energy.consume.tracking.DTO.EnergyPowerProducedUpdateDTO;
import com.ahmet.energy.consume.tracking.entity.Energy;
import com.ahmet.energy.consume.tracking.repository.EnergyRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnergyService {


    private EnergyRep energyRep;

    public List<Energy> results() {
        return energyRep.findAll();
    }

    public Energy getOne(String id) {
        return energyRep.findById(id)
                .orElseThrow(() -> new RuntimeException("No result with this ID"));
    }

    public Iterable<Energy> save(List<Energy> energyList) {
        return energyRep.saveAll(energyList);
    }

    public Energy updateCounter(String id, EnergyPowerProducedUpdateDTO request) {
        Energy toUpdate = this.getOne(id);
        toUpdate.setPowerProduced(request.getPowerProduced());
        return energyRep.save(toUpdate);
    }

    public Energy createNew(Energy energy) {
        Energy toCreate=new Energy();
        toCreate.setId(energy.getId());
        toCreate.setPowerProduced(energy.getPowerProduced());
        toCreate.setPeriod(energy.getPeriod());
        toCreate.setCreatedOn(energy.getCreatedOn());
        toCreate.setWindPark(energy.getWindPark());
        toCreate.setTimestamp(energy.getTimestamp());
        toCreate.setUpdatedOn(energy.getUpdatedOn());
        return energyRep.save(toCreate);
    }
}
