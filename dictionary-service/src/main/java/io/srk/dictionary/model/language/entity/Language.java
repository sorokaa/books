package io.srk.dictionary.model.language.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "dictionary", name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
}
