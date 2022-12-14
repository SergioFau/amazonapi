package com.svalero.amazonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOutDTO {

    private long id;
    private LocalDate date;
    private int quantity;
    private ProductDTO product;
    // TODO Añadir también la información del propietario del pedido (user)
}
