package com.omfgdevelop.adminLogBook.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode
public class Organization extends BaseEntity {
    @Column(name = "organization_name")
    private String name;
    @Column(name = "user_id")
    private Long userId;
}
