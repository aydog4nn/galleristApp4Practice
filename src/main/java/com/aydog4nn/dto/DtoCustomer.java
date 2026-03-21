package com.aydog4nn.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DtoCustomer extends DtoBase{
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String tckn;

    @NotEmpty
    private Date birthOfDate;

    @NotEmpty
    private DtoAddress address;

    @NotEmpty
    private DtoAccount account;


}
