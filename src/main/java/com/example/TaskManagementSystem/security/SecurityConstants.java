package com.example.TaskManagementSystem.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 70000;
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

}
