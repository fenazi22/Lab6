package com.example.employeemanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmpMod {

    @NotNull(message = "Cannot be 'id' null. \n")
    @Size(min = 2, message = "Length 'id' must be more than 2 characters. \n")
    private String id;


   // @NotNull(message = "Cannot be 'name' null. \n")
    @Size(min = 3, message = "Length name must be more than 4 characters. \n")
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;


//    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
//            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "plsea Enter Your Email.")
//
   @Email
    private String Email;

    @Pattern(regexp = "^05\\d{8}$", message = "Must phoneNumber start with and Must consists of exactly 10 digits")
    private String phoneNumber;

   // @NotNull
    @Digits(integer = 2, fraction = 0)
    @Min(26)    private int age;

    @NotEmpty(message = "Cannot be Postion Empty")
    @Pattern(regexp = "supervisor|coordinator")
    private String Position;

    @AssertFalse()
    private boolean onLeave;


    private LocalDate employmentYear;

    @NotNull
    @PastOrPresent
    private LocalDate hireDate;


    @NotNull
    @Positive
    private int AnnualLeave;

}
