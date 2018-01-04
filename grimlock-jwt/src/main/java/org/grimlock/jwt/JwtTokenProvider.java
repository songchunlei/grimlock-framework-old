package org.grimlock.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.grimlock.jwt.config.JwtConfiguration;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

// jwt 工具类
// 这个博客中描述了jwt是什么东西
// http://www.jianshu.com/p/576dbf44b2ae
@Component
public class JwtTokenProvider {
	// 注入配置类
	private JwtConfiguration configuration;

	public JwtTokenProvider(JwtConfiguration configuration) {
		this.setConfiguration(configuration);
	}

	/**
	 * 生成token
	 * @return String
	 */
	public String createToken(Claims claims) {
		String compactJws = Jwts.builder().setPayload(JSONObject.toJSONString(claims))
				.compressWith(CompressionCodecs.DEFLATE)
				.signWith(SignatureAlgorithm.HS512, configuration.getSecretKeySpec()).compact();
		return compactJws;
	}

	/** token转换 */
	public Claims parseToken(String token) {
		try {
			return Jwts.parser().setSigningKey(configuration.getSecretKeySpec()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JwtConfiguration getConfiguration() {
		return configuration;
	}

	private void setConfiguration(JwtConfiguration configuration) {
		this.configuration = configuration;
	}
}
