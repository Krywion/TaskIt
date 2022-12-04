package com.example.taskit;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "priority")
    private String priority;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
}
