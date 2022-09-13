package reactive.src.main.java.com.example.usermanagement.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Certificate {
    private Long id;
    private String issueId;
    private Lecturer graduate;
    private String department;
    private String institute;
    private LocalDate issueDate;
}
