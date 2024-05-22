package com.io.java.events.managers.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @Schema(description = "Token")
    private String access_token;

    @Schema(description = "Quanto tempo o token dura até expirar (em segundos)")
    private Integer expires_in;

    @Schema(description = "Tipo do token")
    private String token_type;
}
