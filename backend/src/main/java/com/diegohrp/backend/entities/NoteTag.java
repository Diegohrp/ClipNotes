package com.diegohrp.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notes_tags")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NoteTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Note note;
    @ManyToOne
    private Tag tag;
}
