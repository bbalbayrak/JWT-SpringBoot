package com.yena.webyena.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "works")
public class Works {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer order_number;
    private Integer project_number;
    private Integer vendor_id;
    private Integer customer_id;
    private Integer quality_responsible_id;
    private Integer inspector_id;
    private Integer foreman_id;
    private String work_type;
    private String state;
    private String status;
    private String creator_name;
    @CreationTimestamp
    private LocalDateTime creation_date;
}
