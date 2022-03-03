package Analizadores;
import java_cup.runtime.*;
import java.util.LinkedList;


%%

%{
    public static LinkedList<TError> errores = new LinkedList<TError>(); 
%}

%public
%class Analizador_Lexico
%cupsym Simbolos
%cup
%char
%column
%full
%ignorecase
%line
%unicode

letra=[a-zA-Z]
numero=[0-9]+
id={letra}({letra}|"_"|{numero})*
cadena = ([\"][^\n\"]* [\"]) | ([\'][^\n\']* [\'])
comentario ="//"[^\n]*
comentarioMulti = "<!"[^"!>"]* "!>"

%%





<YYINITIAL>","  {
                   System.out.println("Reconocio token: <coma> lexema: "+ yytext());
                   return new Symbol(Simbolos.coma, yycolumn, yyline, yytext());
                }

<YYINITIAL>":"  {
                   System.out.println("Reconocio token: <DosPuntos> lexema: "+ yytext());
                    return new Symbol(Simbolos.DosPuntos, yycolumn, yyline, yytext());
                }

<YYINITIAL>"^"  {
                   System.out.println("Reconocio token: <Circunflejo> lexema: "+ yytext());
                   return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }


<YYINITIAL>"."  {
                   System.out.println("Reconocio token: <Punto> lexema: "+ yytext());
                    return new Symbol(Simbolos.Punto, yycolumn, yyline, yytext());
                }


<YYINITIAL>";"  {
                   System.out.println("Reconocio token: <PuntoComa> lexema: "+ yytext());
                    return new Symbol(Simbolos.PuntoComa, yycolumn, yyline, yytext());
                }


<YYINITIAL>"->"  {
                   System.out.println("Reconocio token: <flecha> lexema: "+ yytext());
                    return new Symbol(Simbolos.flecha, yycolumn, yyline, yytext());
                }

<YYINITIAL>"?"  {
                   System.out.println("Reconocio token: <InterrogacionC> lexema: "+ yytext());
                    return new Symbol(Simbolos.InterrogacionC, yycolumn, yyline, yytext());
                }

<YYINITIAL>"*"  {
                   System.out.println("Reconocio token: <Asterisco> lexema: "+ yytext());
                    return new Symbol(Simbolos.Asterisco, yycolumn, yyline, yytext());
                }

<YYINITIAL>"+"  {
                    System.out.println("Reconocio token: <Mas> lexema: "+ yytext());
                    return new Symbol(Simbolos.Mas, yycolumn, yyline, yytext());
                }

<YYINITIAL>"|"  {
                    System.out.println("Reconocio token : <Or> lexema: "+ yytext());
                    return new Symbol(Simbolos.Or, yycolumn, yyline, yytext());
                }

<YYINITIAL>"CONJ"  {
                    System.out.println("Reconocio token : <CONJ> lexema: "+ yytext());
                    return new Symbol(Simbolos.CONJ, yycolumn, yyline, yytext());
                }

<YYINITIAL>"-"  {
                    System.out.println("Reconocio token : <guion> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"~"  {
                    System.out.println("Reconocio token : <guion> lexema: "+ yytext());
                    return new Symbol(Simbolos.guion, yycolumn, yyline, yytext());
                }

<YYINITIAL>"!"  {
                    System.out.println("Reconocio token : <AdmiracionA> lexema: "+ yytext());
                    return new Symbol(Simbolos.AdmiracionC, yycolumn, yyline, yytext());
                }


<YYINITIAL>"%"  {
                    System.out.println("Reconocio token : <PorCiento> lexema: "+ yytext());
                    return new Symbol(Simbolos.PorCiento, yycolumn, yyline, yytext());
                }

<YYINITIAL>"#"  {
                    System.out.println("Reconocio token : <Numeral> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"`"  {
                    System.out.println("Reconocio token : <AcentoGrave> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>">"  {
                    System.out.println("Reconocio token : <MayorQue> lexema: "+ yytext());
                    return new Symbol(Simbolos.MayorQue, yycolumn, yyline, yytext());
                }

<YYINITIAL>"<"  {
                    System.out.println("Reconocio token : <MenorQue> lexema: "+ yytext());
                    return new Symbol(Simbolos.MenorQue, yycolumn, yyline, yytext());
                }

<YYINITIAL>"="  {
                    System.out.println("Reconocio token : <igual> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"/"  {
                    System.out.println("Reconocio token : <Barra> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }


<YYINITIAL>"("  {
                    System.out.println("Reconocio token : <ParentesisA> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>")"  {
                    System.out.println("Reconocio token : <ParentesisC> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"'"  {
                    System.out.println("Reconocio token : <ComillaSimple> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }


<YYINITIAL>"$"  {
                    System.out.println("Reconocio token : <Dolar> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"&"  {
                    System.out.println("Reconocio token : <Ampersand> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"{"  {
                   System.out.println("Reconocio token: <LlaveA> lexema: "+ yytext());
                    return new Symbol(Simbolos.LlaveA, yycolumn, yyline, yytext());
                }

<YYINITIAL>"}"  {
                   System.out.println("Reconocio token: <LlaveC> lexema: "+ yytext());
                    return new Symbol(Simbolos.LlaveC, yycolumn, yyline, yytext());
                }

<YYINITIAL>"["  {
                   System.out.println("Reconocio token: <CorcheteA> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"]"  {
                   System.out.println("Reconocio token: <CorcheteC> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"@"  {
                   System.out.println("Reconocio token: <Arroba> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"_"  {
                   System.out.println("Reconocio token: <GuionBajo> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }

<YYINITIAL>"\\"  {
                   System.out.println("Reconocio token: <BarraInvertida> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
                }


<YYINITIAL>{id} {
                    System.out.println("Reconocio token: <id> lexema: "+ yytext());
                    return new Symbol(Simbolos.id, yycolumn, yyline, yytext());
                }

<YYINITIAL>{cadena} {
                    System.out.println("Reconocio token: <cadena> lexema: "+ yytext());
                    return new Symbol(Simbolos.Cadena, yycolumn, yyline, yytext());
                }

<YYINITIAL>{comentario} {
                    System.out.println("Reconocio token: <comentario> lexema: "+ yytext());
                    
                }

<YYINITIAL>{comentarioMulti} {
                    System.out.println("Reconocio token: <comentarioMulti> lexema: "+ yytext());
                    
                }

<YYINITIAL>{numero} {
                    System.out.println("Reconocio token: <numero> lexema: "+ yytext());
                    return new Symbol(Simbolos.numero, yycolumn, yyline, yytext());
                }


[\t \n \f \r ] { /* Espacios en blanco se ignoran */}


.   {
        {
            System.out.println("Error Lexico: "+ yytext()+" Linea: "+ yyline+" Columna: "+yycolumn);
            TError tmp= new TError("Lexico", yytext(),"NO PERTENECE AL LENGUAJE", yyline, yycolumn );
            errores.add(tmp); 
            
        }
    }
