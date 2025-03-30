package com.example.demo.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AttendeeRequest {
    @NotBlank(message = "Attendee Name cannot be blank")
    private String attendeeName;

    @Schema(example = "example@gmail.com")
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;
}
