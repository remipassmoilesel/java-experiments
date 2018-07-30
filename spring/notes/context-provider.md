# Accéder au contexte d'application Spring

Faire hériter une classe de ApplicationContextAware:

    @Component
    public class ApplicationContextProvider implements ApplicationContextAware {
    
        private static ApplicationContext context;
    
        public ApplicationContext getApplicationContext() {
            return context;
        }
    
        @Override
        public void setApplicationContext(ApplicationContext ctx) {
            context = ctx;
        }
    
    }
    
Usage: 

    ApplicationContext appCtx = new ApplicationContextProvider().getApplicationContext();
    
La documentation recommande de ne pas utiliser @Autowired pour accéder au contexte,
pour éviter les NullPointerException.

Source: https://spring.io/understanding/application-context