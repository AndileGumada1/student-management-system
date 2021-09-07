package com.andile.student.management.application.model.payloads.request;

import com.andile.student.management.application.model.Facality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 *
**/
@AllArgsConstructor
@Data
@NoArgsConstructor
public class StudentRequest {
        @NotBlank
        @NotNull
        private String name;
        @NotBlank
        @NotNull
        private String address;
        @NotBlank
        @NotNull
        private String mobile;
        @Email
        private String email;

        @NotBlank
        @NotNull
        @Enumerated(EnumType.STRING)
        private Facality facality;
}
