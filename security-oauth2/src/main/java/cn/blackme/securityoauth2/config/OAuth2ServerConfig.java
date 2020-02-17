package cn.blackme.securityoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
public class OAuth2ServerConfig {

    /**
     * 资源服务器
     */
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.requestMatchers()
                    .antMatchers("/api/v1/user/register", "/api/v1/index", "/api/v1/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/v1/user/register", "/api/v1/index").permitAll()
                    .antMatchers( "/api/v1/**").access("hasRole('ROLE_ADMIN')")
                    .and()
                    .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        }
    }

    /**
     * 授权服务器
     */
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        @Qualifier("customUserDetailsService")
        private UserDetailsService userDetailsService;

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("permitAll()")
                    .allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("admin")// client_id
                    .secret(passwordEncoder.encode("admin"))// client_secret
                    .accessTokenValiditySeconds(3600)// token的有效期
                    .refreshTokenValiditySeconds(7200)// 刷新token的有效期
                    .redirectUris("https://www.baidu.com")// 授权成功后跳转uri
                    .scopes("all")// 权限范围
                    .authorizedGrantTypes("authorization_code","password");// 授权类型
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .pathMapping("/oauth/token", "/api/v1/oauth/token")
                    .pathMapping("/oauth/authorize", "/api/v1/oauth/authorize")
                    .pathMapping("/oauth/confirm_access", "/api/v1/oauth/confirm_access")
                    .pathMapping("/oauth/error", "/api/v1/oauth/error")
                    .pathMapping("/oauth/check_token", "/api/v1/oauth/check_token")
                    .pathMapping("/oauth/token_key", "/api/v1/oauth/token_key")
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        }
    }

}
