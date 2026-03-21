package com.aydog4nn.dto;

import com.aydog4nn.model.Account;
import com.aydog4nn.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DtoCustomerIU {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String tckn;

    @NotEmpty
    private Date birthOfDate;

    @NotEmpty
    private Long addressId;

    @NotEmpty
    private Long accountId;

}
