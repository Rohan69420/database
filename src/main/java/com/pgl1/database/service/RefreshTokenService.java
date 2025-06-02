package com.pgl1.database.service;

import com.pgl1.database.model.entity.RefreshToken;
import com.pgl1.database.model.entity.User;
import com.pgl1.database.repository.RefreshTokenRepository;
import com.pgl1.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Long refreshTokenValidity = 5*60*60*1000L;

    public RefreshToken createRefreshToken(String userName){
        User user = userRepository.findByUsername(userName);
        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null) {
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(refreshTokenValidity))
                    .user(userRepository.findByUsername(userName))
                    .build();
        }

       return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) throws Exception{
        RefreshToken refreshTokenOb = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new RuntimeException("Token does not exist."));
        if (refreshTokenOb.getExpiry().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshTokenOb);
            throw new Exception("Refresh token expired.");
        }

        return refreshTokenOb;
    }
}
