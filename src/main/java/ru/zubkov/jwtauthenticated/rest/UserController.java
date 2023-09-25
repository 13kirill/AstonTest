package ru.zubkov.jwtauthenticated.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zubkov.jwtauthenticated.dto.MoneyDTO;
import ru.zubkov.jwtauthenticated.dto.TransferMoneyDTO;
import ru.zubkov.jwtauthenticated.dto.UserDTO;
import ru.zubkov.jwtauthenticated.model.User;
import ru.zubkov.jwtauthenticated.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDTO result = UserDTO.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("add")
    public ResponseEntity<User> addMoney(@RequestBody MoneyDTO dto) {
        User user = userService.findById(dto.getId());

        user.setBalance(user.getBalance() + dto.getBalance());
        User result = userService.save(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("take")
    public ResponseEntity<User> takeMoney(@RequestBody MoneyDTO dto) {
        User user = userService.findById(dto.getId());

        user.setBalance(user.getBalance() - dto.getBalance());
        User result = userService.save(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("transfer")
    public ResponseEntity<User> transferMoney(@RequestBody TransferMoneyDTO dto) {
        User user1 = userService.findById(dto.getId1());
        user1.setBalance(user1.getBalance() - dto.getBalance());
        User user2 = userService.findById(dto.getId2());
        user2.setBalance(user2.getBalance() + dto.getBalance());

        return new ResponseEntity<>(user2, HttpStatus.OK);
    }
}