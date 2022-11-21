package com.journey.web.config.oauth;

import com.journey.web.domain.member.Member;
import com.journey.web.dto.member.MemberJoinDto;
import com.journey.web.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.journey.web.exception.ErrorCode.*;

@RequiredArgsConstructor
@Component
public class ClientGoogle {

    public Member getMember(String idToken) {

        BufferedReader in  = null;
        InputStream is = null;
        InputStreamReader isr = null;
        JSONParser jsonParser = new JSONParser();

        try {

            String url = "https://oauth2.googleapis.com/tokeninfo";
            url += "?id_token="+idToken;

            URL gUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) gUrl.openConnection();

            is = conn.getInputStream();
            isr = new InputStreamReader(is, "UTF-8");
            in = new BufferedReader(isr);


            JSONObject jsonObj = (JSONObject)jsonParser.parse(in);

            String firstname = jsonObj.get("firstname").toString();
            String lastname = jsonObj.get("lastname").toString();
            String nickName =  firstname + lastname + "#" + jsonObj.get("sub").toString();
            String email = jsonObj.get("email").toString();
            String password = "sool1234";

            MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                    .memberEmail(email)
                    .nickName(nickName)
                    .firstname(firstname)
                    .lastname(lastname)
                    .pwd(password)
                    .build();
            return memberJoinDto.toGoogleMember();


        }catch(Exception e) {
            throw new CustomException(INVALID_GOOGLE_ID_TOKEN);
        }
    }
}

