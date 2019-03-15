package models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {
    private Long id;
    private String login;
    private String password;
    private String name;
    private List<Desk> ownDesks;
    private List<Task> tasks;

    public User(Long id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.ownDesks = new ArrayList<Desk>();
        this.tasks = new ArrayList<Task>();
    }
}
