# Planifier des tâches

pom.xml:

    <dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-web</artifactId>
    </dependency>

Créer un classe avec une méthode planifiée:

    @Component
    public class ScheduledTasks {
    
        @Scheduled(fixedRate = 10000)
        public void performTask() {
    
        }
    
        @Scheduled(initialDelay = 1000, fixedRate = 10000)
        public void performDelayedTask() {
    
        }
    
        @Scheduled(cron = "*/5 * * * * *")
        public void performTaskUsingCron() {
    
        }
    }


Activer les tâches planifiées avec l'annotation @EnableScheduling:

    @SpringBootApplication
    @EnableScheduling
    public class SpringBootHelloWorldApplication {
    
        ... 
