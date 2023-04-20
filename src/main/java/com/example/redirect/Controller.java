package com.example.redirect;

import com.example.redirect.BigBlueButton.api.BBBException;
import com.example.redirect.BigBlueButton.api.BBBMeeting;
import com.example.redirect.BigBlueButton.impl.BaseBBBAPI;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping("/bigbluebutton/")
@AllArgsConstructor
@RestController
public class Controller {
private final BaseBBBAPI api;

    @GetMapping("api/join")
    public ResponseEntity<String> createDepartment() throws BBBException {

         String bbbUrl = "https://sam1.gyanada.in/bigbluebutton/api";
        /** BBB security salt */
         String bbbSalt = "cJgF1zxc8MjkiLO6FFfqquBL8rL3c9H9vlrfNyZUbL8";
        api.setBbbSalt(bbbSalt);
        api.setBbbUrl(bbbUrl);
        System.out.println("In Controller :- "+api.getUrl());
        BBBMeeting bbbMeeting =new BBBMeeting("897234324723-4lkjljk22");
        bbbMeeting.setName("testeing meting");
        BBBMeeting meeting = api.createMeeting(bbbMeeting);

        String  url = api.getJoinMeetingURL(meeting.getMeetingID(),meeting.getModeratorPW(),"Amitkumar");

        System.out.println("Meeting URL :- "+url);


        final String uri = "https://sam1.gyanada.in/bigbluebutton/api";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);

        browse(url);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

   /* @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("Application started ... launching browser now");
        browse("www.google.com");
    }*/

    public static void browse(String url) {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
