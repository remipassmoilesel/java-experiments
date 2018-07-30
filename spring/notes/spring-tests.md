# Test aves Spring

Pour créer un test case, ajouter ces annotations:

    @SpringBootTest                         // la classe de test doit se situer dans le même package pour détecter la configuration principale 
                                            // (pas forcement dans le même dossier)
    @RunWith(SpringRunner.class)            // lancer Spring aves le test: injection, ...
    @ActiveProfiles("PROFILE_NAME_VALUE")   // changer le profil courant
    public class SpringExamplesTest {
    
    }
    
    