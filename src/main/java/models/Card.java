package models;

import java.util.Date;
import java.util.List;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "card")
public class Card {
    private Long id;
    private String name;
    private String dateOfCreation;

    // в бд нет такого, у каждого таска есть ссылка на карточку
    private List<Task> tasks;
}
