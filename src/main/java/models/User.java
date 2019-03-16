package models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String login;
    private String password;
    private String name;
    private List<Desk> ownDesks;
    private List<Task> tasks;
}
