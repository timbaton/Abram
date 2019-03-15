package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Card {
    private Long id;
    private String name;
    private Date dateOfCreation;

    // в бд нет такого, у каждого таска есть ссылка на карточку
    private List<Task> tasks;

    public Card(Long id, String name, Date dateOfCreation) {
        this.id = id;
        this.name = name;
        this.dateOfCreation = dateOfCreation;
        this.tasks = new ArrayList<Task>();
    }
}
