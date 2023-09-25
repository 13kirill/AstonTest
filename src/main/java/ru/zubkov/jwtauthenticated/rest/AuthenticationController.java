package ru.zubkov.jwtauthenticated.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zubkov.jwtauthenticated.dto.AuthenticationRequestDTO;
import ru.zubkov.jwtauthenticated.dto.UserDTO;
import ru.zubkov.jwtauthenticated.model.User;
import ru.zubkov.jwtauthenticated.security.jwt.JwtTokenProvider;
import ru.zubkov.jwtauthenticated.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            String userName = authenticationRequestDTO.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (userName, authenticationRequestDTO.getPassword()));
            User user = userService.findByUserName(userName);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + userName + " not found");
            }

            String token = jwtTokenProvider.createToken(userName, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("userName", userName);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody UserDTO dto) {
        userService.register(dto.toUser());
        return ResponseEntity.ok(dto);
    }
}
