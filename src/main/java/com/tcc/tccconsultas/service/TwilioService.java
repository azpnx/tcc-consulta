package com.tcc.tccconsultas.service;

import com.twilio.Twilio;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.twilio.rest.video.v1.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TwilioService {

    @Value("${account-sid}")
    private String ACCOUNT_SID;
    @Value("${auth_token}")
    private String AUTH_TOKEN;
    @Value("${key_sid}")
    private String KEY_SID;
    @Value("${key_secret}")
    private String KEY_SECRET;

    public String criaSala(String nomeSala){

        if (String.valueOf(nomeSala).isEmpty()) throw new IllegalArgumentException("Nome da sala está vazia!.");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Room room = Room.creator()
                .setRecordParticipantsOnConnect(true)
                .setMaxParticipants(2)
                .setUniqueName(nomeSala).create();

        log.info("Sala criada com sucesso! RoomSid: " + room.getSid());

        return room.getSid();
    }

    public String geraToken(String userId, String roomSid) {

        if (String.valueOf(userId).isEmpty()) throw new IllegalArgumentException("UserId está vazio!.");

        final VideoGrant videoGrant = new VideoGrant();

        videoGrant.setRoom(roomSid);

        AccessToken accessToken = new AccessToken
                .Builder(ACCOUNT_SID, KEY_SID, KEY_SECRET)
                .identity(userId)
                .grant(videoGrant)
                .build();

        log.info("Token criado com sucesso! " + accessToken.toJwt());
        return accessToken.toJwt();
    }
}
