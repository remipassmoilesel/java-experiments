%%
%class Exercice1
%standalone
parg=[(]
nombre=[0-9]+
op=[*/+-]
pard=[)]
%%
 {parg} {System.out.print(" parg ");}
 {nombre} {System.out.print(" nombre ");}
 {op} {System.out.print(" op ");}
 {pard} {System.out.print(" pard ");}
 