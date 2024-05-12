package com.io.java.events.managers.application.controller.v1;

import com.io.java.events.managers.application.dto.request.UserRequestDto;
import com.io.java.events.managers.application.dto.response.UserResponse;
import com.io.java.events.managers.application.utils.URLS;
import com.io.java.events.managers.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
@RequestMapping(value = URLS.AUTH_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Login do funcionário", description = "Controlador para efetuar operações como login, registro,etc")
public class AuthController {

    private final UserService userService;

    @ApiResponse(description = "Login do usuário", responseCode = "200")
    @Operation(summary = "Efetua login do usuário no banco de dados", description = """
            # Retorna um token que permitirá que ele acesse diferentes tipos de rotas
            ---
           
            """)
    @PostMapping(value = URLS.AUTH_LOGIN)
    public ResponseEntity<UserResponse> loginUser(@RequestBody @Valid UserRequestDto userRequestDto){
         log.info("iniciando o processo de login do usuário...");
         var jwt = userService.loginUser(userRequestDto);
         log.info("processo de login do usuário finalizado com sucesso");
         return ResponseEntity.status(HttpStatus.OK).body(jwt);
    }

}
