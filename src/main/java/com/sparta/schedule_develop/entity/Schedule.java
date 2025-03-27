package com.sparta.schedule_develop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

}
