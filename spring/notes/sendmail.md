# Envoyer des mails avec SpringBoot et Postfix

Nécéssite un serveur postfix installé et configuré.

Ajouter au pom.xml:

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>

Puis dans un controlleur:

	@Autowired
	private JavaMailSender javaMailSender;

	...

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("from@mailcom");
        message.setTo("to@mail.com");
        message.setSubject("Subject");
        message.setText("Message body");
        javaMailSender.send(message);
