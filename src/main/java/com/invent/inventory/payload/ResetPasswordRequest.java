package com.invent.inventory.payload;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String newPassword;
}