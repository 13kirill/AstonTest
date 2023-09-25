package ru.zubkov.jwtauthenticated.dto;

import lombok.Data;

@Data
public class TransferMoneyDTO {
    private Long id1;
    private Long id2;
    private Long balance;
}
