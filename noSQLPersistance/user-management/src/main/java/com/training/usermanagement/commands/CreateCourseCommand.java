package com.example.usermanagement.commands;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateCourseCommand implements Command {

    String name;

    String lecturerName;

}
