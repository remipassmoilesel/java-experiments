%%
%class Exercice3
%standalone
email=[0-9a-zA-Z\.-]+[@][0-9a-zA-Z\.-]+
other=.
%%
 {email} {System.out.println("Email: " + yytext());}
 {other} {}
