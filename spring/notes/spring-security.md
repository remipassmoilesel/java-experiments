# Spring security

## Mode debug

   
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.debug(true);
        }
    
    }

## Afficher tous les filtres

    package com.org.configuration;
    
    import org.springframework.core.annotation.Order;
    import org.springframework.security.config.annotation.web.builders.WebSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    
    @Order(0)
    @EnableWebSecurity
    public class DebugConf extends WebSecurityConfigurerAdapter {
    
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.debug(true);
        }
    
    }

Exemple de sortie console:

    Security filter chain: [
      WebAsyncManagerIntegrationFilter
      SecurityContextPersistenceFilter
      HeaderWriterFilter
      LogoutFilter
      OAuth2AuthenticationProcessingFilter
      RequestCacheAwareFilter
      SecurityContextHolderAwareRequestFilter
      AnonymousAuthenticationFilter
      SessionManagementFilter
      ExceptionTranslationFilter
      FilterSecurityInterceptor
    ]

    