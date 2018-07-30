# Spring security

Activer le débuggage:

   
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.debug(true);
        }
    
    }