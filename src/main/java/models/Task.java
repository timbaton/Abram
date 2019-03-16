package models;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String description;
    private List<User> users;
    private Card card;
}
