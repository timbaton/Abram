package models;

import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Desk {
    private int id;
    private String dataOfCreation;
    private String name;
    private int creator;

    // в бд нет такого, у каждого пользователя есть ссылка на стол
    private List<User> users;
}
