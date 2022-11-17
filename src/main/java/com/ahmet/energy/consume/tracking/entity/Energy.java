package com.ahmet.energy.consume.tracking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Energy {
    @Id
    private String id;
    private String windPark;
    private Double powerProduced;
    private String period;
    private String timestamp;
    private String createdOn;
    private String updatedOn;


}
