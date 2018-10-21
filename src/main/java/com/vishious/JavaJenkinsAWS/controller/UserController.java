package com.vishious.JavaJenkinsAWS.controller;


import com.vishious.JavaJenkinsAWS.model.User;
import com.vishious.JavaJenkinsAWS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> addUser(@RequestHeader HttpHeaders httpHeaders, @RequestBody String string) {
        User user = new Gson().fromJson(string, User.class);

        System.out.println("body= " + string);
        System.out.println("user= " + user.getLastName());
        if (user.getFirstName() != null && user.getLastName() != null) {
            UUID uuid = UUID.randomUUID();
            user.setId(uuid.toString());
            if (userService.saveUser(user)) {
                return new ResponseEntity<String>(uuid.toString(), HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/lookup/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@RequestHeader HttpHeaders httpHeader, @PathVariable final String uuid) {

        if (uuid != null) {
            return new ResponseEntity<User>(userService.getUser(uuid), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
