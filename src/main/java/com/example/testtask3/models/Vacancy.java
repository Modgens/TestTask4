package com.example.testtask3.models;

import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.Text;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobPageUrl;
    private String positionName;
    private String organizationUrl;
    private String logoUrl;
    private String organizationTitle;
    private String laborFunction;
    private String address;
    private String postedDate;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String tagsNames;

    public Vacancy() {
        this.jobPageUrl = "NOT_FOUND";
        this.positionName = "NOT_FOUND";
        this.organizationUrl = "NOT_FOUND";
        this.logoUrl = "NOT_FOUND";
        this.organizationTitle = "NOT_FOUND";
        this.laborFunction = "NOT_FOUND";
        this.address = "NOT_FOUND";
        this.postedDate = "NOT_FOUND";
        this.description = "NOT_FOUND";
        this.tagsNames = "NOT_FOUND";
    }

}