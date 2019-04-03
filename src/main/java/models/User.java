package models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private String name;

    @OneToMany
    @JoinColumn(name = "desk_")
    private List<Desk> ownDesks;
    private List<Task> tasks;
}
