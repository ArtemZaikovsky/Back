package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String sortNumber;

    private String phone;

    @Pattern(regexp = "[0-9]{12}")
    private String inn;

    private String description;

    private String email;

    private String password;

    private DepartmentDto departmentDto;

    private PositionDto positionDto;

    private Set<RoleDto> roleDto;

    private ImageDto imageDto;

    public EmployeeDto(Long id,
                       String lastName,
                       String firstName,
                       String middleName,
                       String sortNumber,
                       String phone,
                       @Pattern(regexp = "([0-9]+){12}") String inn,
                       String description,
                       String email,
                       String password) {

        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.sortNumber = sortNumber;
        this.phone = phone;
        this.inn = inn;
        this.description = description;
        this.email = email;
        this.password = password;
    }


}