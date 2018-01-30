package org.grimlock.uua.server.web;

import io.jsonwebtoken.Claims;
import org.grimlock.jwt.JWTToken;
import org.grimlock.jwt.JwtTokenProvider;
import org.grimlock.jwt.UAAClaims;
import org.grimlock.jwt.config.JwtConfiguration;
import org.grimlock.uua.server.db.User;
import org.grimlock.uua.server.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @Author:chunlei.song@live.com
 * @Description:获取token
 * @Date Create in 15:03 2018-1-22
 * @Modified By:
 */
@RestController
@RequestMapping("/")
public class TokenController {
    @Autowired
    JwtConfiguration jwtConfiguration;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    // 获取一个根据手机号和密码获取token
    @PostMapping("/token/byPhone")
    public ResponseEntity<?> getTokenByPhone(@RequestBody User user) {
        User domain = userRepository.findByPhoneAndPassword(user.getPhone(), user.getPassword());
        if (domain == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("验证不通过");
        }
        return ResponseEntity.ok(new JWTToken(jwtTokenProvider.createToken(parseClaims(domain))));
    }

    // 将token反解出来，看看里面的内容；
    // 仅用于开发场景
    @RequestMapping("/token/parse")
    public Claims parseToken(String token) {
        return jwtTokenProvider.parseToken(token);
    }

    // UAAClaims这个对象就是token中的内容
    private UAAClaims parseClaims(User user) {
        UAAClaims uaaClaims = new UAAClaims();
        uaaClaims.setIssuer(jwtConfiguration.getIss());
        uaaClaims.setIssuedAt(new Date());
        uaaClaims.setAudience(String.valueOf(user.getId()));
        uaaClaims.setId(UUID.randomUUID().toString());
        uaaClaims.setUserName(user.getUserName());
        uaaClaims.setExpiration(new Date(System.currentTimeMillis() + jwtConfiguration.getExpm() * 1000 * 60));
        uaaClaims.setEmail(user.getEmail());
        uaaClaims.setPhone(user.getPhone());
        uaaClaims.setSubject(String.valueOf(user.getId()));
        uaaClaims.setNotBefore(new Date());
        return uaaClaims;
    }
}
