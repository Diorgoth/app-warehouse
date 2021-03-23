package com.example.demo.entity;

import com.example.demo.constants.ExpireStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InputProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Double amount;


    private Double price;

    private Date expireDate;

    @ManyToOne
    private Input input;

    @Column(name = "expire_status")
    @Enumerated(EnumType.ORDINAL)
    private ExpireStatus expireStatus;


}
