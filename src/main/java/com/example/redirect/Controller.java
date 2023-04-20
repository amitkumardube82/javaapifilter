package com.example.redirect;

import com.example.redirect.BigBlueButton.impl.BaseBBBAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/data/")
@AllArgsConstructor
@RestController
public class Controller {
private final BaseBBBAPI api;

    @GetMapping("get")
    public ResponseEntity<String> createDepartment(){
        System.out.println("In Controller :- "+api.getUrl());


        final String uri = "https://sam1.gyanada.in/bigbluebutton/api";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);



        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
