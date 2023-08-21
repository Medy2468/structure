package com.cgl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150, nullable = false)
    private String label;
    @Column(length = 500, nullable = false)
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(length = 255, nullable = false)
    @Transient
    private String photoFile;
    private boolean isArchived = false;

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }

        if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
        {
            return false;
        }

        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
