package app.models;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Desk {
    private Long id;
    private Date dataOfCreation;
    private String name;
    private User creator;

    // в бд нет такого, у каждого пользователя есть ссылка на стол
    private List<User> users;
}
