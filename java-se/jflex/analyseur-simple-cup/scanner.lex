import java_cup.runtime.SymbolFactory;
%%
%cup
%class Scanner
%{
  	public Scanner(java.io.InputStream r, SymbolFactory sf){
		this(new java.io.InputStreamReader(r));
		this.sf=sf;
	}
	private SymbolFactory sf;
%}
%eofval{
    return sf.newSymbol("EOF",sym.EOF);
%eofval}

%%
"SET" { return sf.newSymbol("Declaration SET",sym.SET); }
[a-zA-Z]+ { return sf.newSymbol("Déclaration de variable", sym.VAR_NAME, yytext()); }
"=" { return sf.newSymbol("Affectation",sym.AFFECT); }
";" { return sf.newSymbol("Semicolon",sym.SEMI); }
"+" { return sf.newSymbol("Plus",sym.PLUS); }
"*" { return sf.newSymbol("Times",sym.TIMES); }
"(" { return sf.newSymbol("Left Bracket",sym.LPAREN); }
")" { return sf.newSymbol("Right Bracket",sym.RPAREN); }
[0-9]+ { return sf.newSymbol("Integral Number",sym.NUMBER, new Integer(yytext())); }
[ \t\r\n\f] { /* ignore white space. */ }
. { System.err.println("Illegal character: "+yytext()); }
