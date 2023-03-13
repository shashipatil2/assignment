package com.assignment.expensify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@SuppressWarnings("deprecation")
@Configuration 
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "expensify";
	   private String clientSecret = "expensify-secret-key";
	   
	   private String privateKey = "-----BEGIN PRIVATE KEY-----\r\n" +
			   "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDOqZuLA3VenCtY\r\n" +
			   "VmS2cS1vzBul7NxNPTdK6Cw=\r\n" +
			   "-----END PRIVATE KEY-----";
	   
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" +
			   "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzqmbiwN1XpwrWMv5CnOf\r\n" +
			   "/QIDAQAB\r\n" +
			   "-----END PUBLIC KEY-----";
	   
	   
	   private AuthenticationManager authenticationManager;
	    

	   @Autowired
	   @Qualifier("authenticationManagerBean")
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	 

	@Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(new BCryptPasswordEncoder().encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}