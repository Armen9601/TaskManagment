package model;

import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    private int id;
    private String title;
    private String description;
    private int userId;
    private Status status;
    private String deadline;


}
