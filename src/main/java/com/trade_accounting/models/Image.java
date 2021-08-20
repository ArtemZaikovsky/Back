package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sort_number")
    private String sortNumber;

    public Image(String imageUrl, String sortNumber) {
        this.imageUrl = imageUrl;
        this.sortNumber = sortNumber;
    }

    public Image(Long id, String sortNumber) {
        this.id = id;
        this.sortNumber = sortNumber;
    }
}
