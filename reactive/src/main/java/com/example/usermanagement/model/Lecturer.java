package reactive.src.main.java.com.example.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecturer {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private List<Certificate> certificate;
}
