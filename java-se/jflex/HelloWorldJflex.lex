%%
%class HelloWorldJflex
%standalone
%{
	private static int sum = 0;
	{
		System.out.println("Addition des occurences de nombres.");
	}
%}
%{eof
		System.out.println("Somme totale : " + sum);
		System.out.println("Fin");
%eof}
	entier=[0-9]+
	other=.
%%
 {entier} {
 	int n = Integer.valueOf(yytext());
 	System.out.println("Nombre trouvé : " + n); 
 	sum += Integer.valueOf(yytext());
 	}
 {other} {}
 