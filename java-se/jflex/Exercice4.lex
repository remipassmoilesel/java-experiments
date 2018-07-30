%%
%class Exercice4
%standalone
%{
	private static double sum = 0;
	private static double nbr = 0;
	{
		System.out.println("Addition des occurences de nombres et moyenne.");
	}
%}
%{eof
		System.out.println("Somme: " + sum);
		System.out.println("Moyenne: " + sum / nbr);
		System.out.println("Fin");
%eof}
	nombre=[0-9]+\.?[0-9]*
	other=.
%%
 {nombre} {
	
		double n = 0;
		try{
			n = Double.parseDouble(yytext());
			
			System.out.println("Nombre trouvé: " + n); 
	
	 		sum += Double.parseDouble(yytext());
			nbr ++;
			
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Nombre refusé: " + yytext()); 
		}
	
 	}
 	
 {other} {}
