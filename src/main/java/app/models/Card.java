package app.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    private Long id;
    private String name;
    private Date dateOfCreation;

    // в бд нет такого, у каждого таска есть ссылка на карточку
    private List<Task> tasks;
}
