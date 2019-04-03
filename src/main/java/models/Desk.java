package models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "desk")
public class Desk {
    private Long id;
    private String dataOfCreation;
    private String name;
    private int creator;

    // в бд нет такого, у каждого пользователя есть ссылка на стол
    private List<User> users;
}
