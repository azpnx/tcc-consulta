package com.tcc.tccconsultas.service;

import com.tcc.tccconsultas.controller.response.SalaResponse;
import com.tcc.tccconsultas.controller.response.TokenResponse;
import com.twilio.Twilio;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.ChatGrant;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.twilio.rest.chat.v2.service.Channel;
import com.twilio.rest.chat.v2.service.channel.Member;
import com.twilio.rest.video.v1.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Value("${service_id}")
    private String SERVICE_ID;

    public SalaResponse criaSala(String nomeSala){

        if (String.valueOf(nomeSala).isEmpty()) throw new IllegalArgumentException("Nome da sala está vazia!.");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Room room = Room.creator()
                .setRecordParticipantsOnConnect(true)
                .setMaxParticipants(2)
                .setUniqueName(nomeSala).create();

        log.info("Sala criada com sucesso! RoomSid: " + room.getSid());

        return new SalaResponse(room.getSid());
    }

    public TokenResponse geraToken(String userId, String roomSid) {

        if (String.valueOf(userId).isEmpty()) throw new IllegalArgumentException("UserId está vazio!.");

        final VideoGrant videoGrant = new VideoGrant();

        videoGrant.setRoom(roomSid);

        AccessToken accessToken = new AccessToken
                .Builder(ACCOUNT_SID, KEY_SID, KEY_SECRET)
                .identity(userId)
                .grant(videoGrant)
                .build();

        log.info("Token criado com sucesso! " + accessToken.toJwt());
        String chatToken = geraTokenTextChat(userId, roomSid);
        return new TokenResponse(accessToken.toJwt(), chatToken);
    }

    public String geraTokenTextChat(String userId, String roomSid) {

        if (String.valueOf(userId).isEmpty()) throw new IllegalArgumentException("UserId está vazio!.");
        Room room = Room.fetcher(roomSid).fetch();

        com.twilio.rest.chat.v2.Service service = com.twilio.rest.chat.v2.Service.fetcher(SERVICE_ID).fetch();
        Channel channel = Channel.creator(service.getSid())
                .setFriendlyName(room.getSid())
                .setUniqueName(room.getUniqueName())
                .create();

        Member member = Member.creator(SERVICE_ID,channel.getSid(), userId).create();
        log.info("Sala de texto criada com sucesso: chatSid: " + channel.getSid());

        final ChatGrant chatGrant = new ChatGrant();
        chatGrant.setServiceSid(SERVICE_ID);
        chatGrant.setEndpointId(userId + "_" + channel.getSid());

        AccessToken accessToken = new AccessToken
                .Builder(ACCOUNT_SID, KEY_SID, KEY_SECRET)
                .identity(userId)
                .grant(chatGrant)
                .build();

        log.info("Token do chat de texto criado com sucesso! " + accessToken.toJwt());
        return accessToken.toJwt();
    }
}