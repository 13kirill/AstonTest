package ru.zubkov.jwtauthenticated.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.zubkov.jwtauthenticated.model.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Long balance;
    private String email;
    private int account;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAccount(account);
        user.setBalance(balance);

        return user;
    }

    public static UserDTO fromUser(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}