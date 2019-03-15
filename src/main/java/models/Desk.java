package models;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Desk {
    private Long id;
    private Date dataOfCreation;
    private String name;
    private User creator;

    // в бд нет такого, у каждого пользователя есть ссылка на стол
    private List<User> users;

    public Desk(Long id, Date dataOfCreation, String name, User creator) {
        this.id = id;
        this.dataOfCreation = dataOfCreation;
        this.name = name;
        this.creator = creator;
        this.users = new ArrayList<User>();
    }
}
