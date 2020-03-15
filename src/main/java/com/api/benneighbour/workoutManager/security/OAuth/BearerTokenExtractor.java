package com.api.benneighbour.workoutManager.security.OAuth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class BearerTokenExtractor implements TokenExtractor {

    private static final String access_token_key = "access_token";
    private static final String refresh_token_key = "refresh_token";

    @Override
    public Authentication extract(HttpServletRequest request) {
        return new PreAuthenticatedAuthenticationToken(getTokenFromRequest(request), "");
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(access_token_key))
                .filter(cookie -> cookie.getName().equals(refresh_token_key))
                .findFirst()
                .map(Cookie::getValue).orElse(null);
    }

}
