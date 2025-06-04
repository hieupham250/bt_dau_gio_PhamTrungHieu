package com.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerDTO {
    private int id;

    @NotBlank(message = "Họ không được để trống")
    private String firstName;

    @NotBlank(message = "Tên không được để trống")
    private String lastName;

    private String phone;

    private String address;

    private String image;
}
