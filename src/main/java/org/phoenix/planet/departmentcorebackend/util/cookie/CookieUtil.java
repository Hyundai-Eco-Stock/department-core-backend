package org.phoenix.planet.departmentcorebackend.util.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtil {

    private static final String REFRESH_TOKEN_COOKIE_NAME = "REFRESH_TOKEN";

    public static String resolveRefreshTokenFromCookie(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie c : cookies) {
            if (REFRESH_TOKEN_COOKIE_NAME.equals(c.getName())) {
                return c.getValue();
            }
        }
        return null;
    }
}
