# Sessions

Accéder à l'objet session:

    @RequestMapping(value = Mappings.DO_RESERVATION, method = RequestMethod.GET)
    public String doReservation(HttpServletRequest request){
    

Créer la session:

        request.getSession()
        
        
Stocker des valeurs:

        session.setAttribute( "chaine", exemple );
        String chaine = (String) session.getAttribute( "chaine" );