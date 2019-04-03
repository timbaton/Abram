package models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    private Long id;
    private String description;
    private Long user;
    private Long card;
    private String name;
}
