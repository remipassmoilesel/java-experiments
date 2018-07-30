# ORM Lite 
       
Utilitaires pour debuggage:
       
       // Afficher la requete SQL 
       System.out.println(builder.prepareStatementString());
       
       // clause (certainement) toujours vraie)
       where.isNotNull(Reservation.ID_FIELD_NAME),
       
       
Exemple de requête:
       
       
       QueryBuilder<Reservation, String> builder = dao.queryBuilder();
       
       Where<Reservation, String> where = builder.where();
       where.and(
               where.eq(Reservation.SHARED_RESOURCE_FIELD_NAME, resource.getId()),
               where.or(
                       // case 1: reservation begin or end in specified interval (SI)
                       where.or(
                               where.between(Reservation.DATEBEGIN_FIELD_NAME, begin, end),
                               where.between(Reservation.DATEEND_FIELD_NAME, begin, end)
                       ),

                       // case 2: reservation equal SI
                       // case 3: reservation contains SI
                       where.and(
                               where.le(Reservation.DATEBEGIN_FIELD_NAME, begin),
                               where.ge(Reservation.DATEEND_FIELD_NAME, end)
                       )
               )
       );

       List<Reservation> reservations = builder.query();
       
       
Pour les agrégations:
       
       // find out how many orders account-id #10 has
       GenericRawResults<String[]> rawResults =
         orderDao.queryRaw(
           "select count(*) from orders where account_id = 10");
           
       // there should be 1 result
       List<String[]> results = rawResults.getResults();
       
       // the results array should have 1 value
       String[] resultArray = results.get(0);
       
       // this should print the number of orders that have this account-id
       System.out.println("Account-id 10 has " + resultArray[0] + " orders");
       
       
Utilitaires:
       
        TableUtils.clearTable(connection, clazz);
        TableUtils.createTableIfNotExists(...
        ...
        