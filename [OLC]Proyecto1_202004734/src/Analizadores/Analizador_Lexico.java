/* The following code was generated by JFlex 1.7.0 */

package Analizadores;
import java_cup.runtime.*;
import java.util.LinkedList;



/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>Lexico.jflex</tt>
 */
public class Analizador_Lexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0, 40,  4, 41, 40, 40,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    40,  8,  3, 26, 31, 25, 32,  5, 29, 30, 17, 18, 10, 15, 13,  6, 
     2,  2,  2,  2,  2,  2,  2,  2,  2,  2, 11, 14,  7, 28,  9, 16, 
    37,  1,  1, 20,  1,  1,  1,  1,  1,  1, 23,  1,  1,  1, 22, 21, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 35, 39, 36, 12, 38, 
    27,  1,  1, 20,  1,  1,  1,  1,  1,  1, 23,  1,  1,  1, 22, 21, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 33, 19, 34, 24,  0, 
     0,  0,  0,  0,  0, 41,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\1\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\2\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\1\2\1\0"+
    "\1\44\1\0\1\45\1\0\1\46\1\2\1\0\1\2"+
    "\1\47\1\50";

  private static int [] zzUnpackAction() {
    int [] result = new int[50];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\52\0\124\0\176\0\250\0\52\0\322\0\374"+
    "\0\u0126\0\52\0\52\0\52\0\52\0\52\0\52\0\52"+
    "\0\u0150\0\52\0\52\0\52\0\52\0\u017a\0\52\0\52"+
    "\0\52\0\52\0\52\0\52\0\52\0\52\0\52\0\52"+
    "\0\52\0\52\0\52\0\52\0\52\0\52\0\u01a4\0\250"+
    "\0\52\0\322\0\u01ce\0\u01f8\0\52\0\u0222\0\u024c\0\u0276"+
    "\0\52\0\124";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[50];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\3\3\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\1\45\1\46\1\6\54\0"+
    "\1\3\1\47\21\0\4\3\24\0\1\4\47\0\3\50"+
    "\1\51\1\0\45\50\4\52\1\0\1\51\44\52\6\0"+
    "\1\53\53\0\1\54\52\0\1\55\41\0\1\3\1\47"+
    "\21\0\1\3\1\56\2\3\24\0\1\47\47\0\4\53"+
    "\1\0\45\53\10\54\1\57\1\0\40\54\1\0\1\3"+
    "\1\47\21\0\2\3\1\60\1\3\33\0\1\61\41\0"+
    "\1\3\1\47\21\0\3\3\1\62\22\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[672];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\1\11\3\1\7\11\1\1\4\11"+
    "\1\1\20\11\1\1\1\0\1\11\1\0\1\1\1\0"+
    "\1\11\1\1\1\0\1\1\1\11\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[50];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    public static LinkedList<TError> errores = new LinkedList<TError>(); 


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Analizador_Lexico(java.io.Reader in) {
    this.zzReader = in;
  }



  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(Simbolos.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { {
            System.out.println("Error Lexico: "+ yytext()+" Linea: "+ yyline+" Columna: "+yycolumn);
            TError tmp= new TError("Lexico", yytext(),"NO PERTENECE AL LENGUAJE", yyline, yycolumn );
            errores.add(tmp); 
            
        }
            } 
            // fall through
          case 41: break;
          case 2: 
            { System.out.println("Reconocio token: <id> lexema: "+ yytext());
                    return new Symbol(Simbolos.id, yycolumn, yyline, yytext());
            } 
            // fall through
          case 42: break;
          case 3: 
            { System.out.println("Reconocio token: <numero> lexema: "+ yytext());
                    return new Symbol(Simbolos.numero, yycolumn, yyline, yytext());
            } 
            // fall through
          case 43: break;
          case 4: 
            { /* Espacios en blanco se ignoran */
            } 
            // fall through
          case 44: break;
          case 5: 
            { System.out.println("Reconocio token : <ComillaSimple> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 45: break;
          case 6: 
            { System.out.println("Reconocio token : <Barra> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 46: break;
          case 7: 
            { System.out.println("Reconocio token : <MenorQue> lexema: "+ yytext());
                    return new Symbol(Simbolos.MenorQue, yycolumn, yyline, yytext());
            } 
            // fall through
          case 47: break;
          case 8: 
            { System.out.println("Reconocio token : <AdmiracionA> lexema: "+ yytext());
                    return new Symbol(Simbolos.AdmiracionC, yycolumn, yyline, yytext());
            } 
            // fall through
          case 48: break;
          case 9: 
            { System.out.println("Reconocio token : <MayorQue> lexema: "+ yytext());
                    return new Symbol(Simbolos.MayorQue, yycolumn, yyline, yytext());
            } 
            // fall through
          case 49: break;
          case 10: 
            { System.out.println("Reconocio token: <coma> lexema: "+ yytext());
                    return new Symbol(Simbolos.coma, yycolumn, yyline, yytext());
            } 
            // fall through
          case 50: break;
          case 11: 
            { System.out.println("Reconocio token: <DosPuntos> lexema: "+ yytext());
                    return new Symbol(Simbolos.DosPuntos, yycolumn, yyline, yytext());
            } 
            // fall through
          case 51: break;
          case 12: 
            { System.out.println("Reconocio token: <Circunflejo> lexema: "+ yytext());
                   return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 52: break;
          case 13: 
            { System.out.println("Reconocio token: <Punto> lexema: "+ yytext());
                    return new Symbol(Simbolos.Punto, yycolumn, yyline, yytext());
            } 
            // fall through
          case 53: break;
          case 14: 
            { System.out.println("Reconocio token: <PuntoComa> lexema: "+ yytext());
                    return new Symbol(Simbolos.PuntoComa, yycolumn, yyline, yytext());
            } 
            // fall through
          case 54: break;
          case 15: 
            { System.out.println("Reconocio token : <guion> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 55: break;
          case 16: 
            { System.out.println("Reconocio token: <InterrogacionC> lexema: "+ yytext());
                    return new Symbol(Simbolos.InterrogacionC, yycolumn, yyline, yytext());
            } 
            // fall through
          case 56: break;
          case 17: 
            { System.out.println("Reconocio token: <Asterisco> lexema: "+ yytext());
                    return new Symbol(Simbolos.Asterisco, yycolumn, yyline, yytext());
            } 
            // fall through
          case 57: break;
          case 18: 
            { System.out.println("Reconocio token: <Mas> lexema: "+ yytext());
                    return new Symbol(Simbolos.Mas, yycolumn, yyline, yytext());
            } 
            // fall through
          case 58: break;
          case 19: 
            { System.out.println("Reconocio token : <Or> lexema: "+ yytext());
                    return new Symbol(Simbolos.Or, yycolumn, yyline, yytext());
            } 
            // fall through
          case 59: break;
          case 20: 
            { System.out.println("Reconocio token : <guion> lexema: "+ yytext());
                    return new Symbol(Simbolos.guion, yycolumn, yyline, yytext());
            } 
            // fall through
          case 60: break;
          case 21: 
            { System.out.println("Reconocio token : <PorCiento> lexema: "+ yytext());
                    return new Symbol(Simbolos.PorCiento, yycolumn, yyline, yytext());
            } 
            // fall through
          case 61: break;
          case 22: 
            { System.out.println("Reconocio token : <Numeral> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 62: break;
          case 23: 
            { System.out.println("Reconocio token : <AcentoGrave> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 63: break;
          case 24: 
            { System.out.println("Reconocio token : <igual> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 64: break;
          case 25: 
            { System.out.println("Reconocio token : <ParentesisA> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 65: break;
          case 26: 
            { System.out.println("Reconocio token : <ParentesisC> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 66: break;
          case 27: 
            { System.out.println("Reconocio token : <Dolar> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 67: break;
          case 28: 
            { System.out.println("Reconocio token : <Ampersand> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 68: break;
          case 29: 
            { System.out.println("Reconocio token: <LlaveA> lexema: "+ yytext());
                    return new Symbol(Simbolos.LlaveA, yycolumn, yyline, yytext());
            } 
            // fall through
          case 69: break;
          case 30: 
            { System.out.println("Reconocio token: <LlaveC> lexema: "+ yytext());
                    return new Symbol(Simbolos.LlaveC, yycolumn, yyline, yytext());
            } 
            // fall through
          case 70: break;
          case 31: 
            { System.out.println("Reconocio token: <CorcheteA> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 71: break;
          case 32: 
            { System.out.println("Reconocio token: <CorcheteC> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 72: break;
          case 33: 
            { System.out.println("Reconocio token: <Arroba> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 73: break;
          case 34: 
            { System.out.println("Reconocio token: <GuionBajo> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 74: break;
          case 35: 
            { System.out.println("Reconocio token: <BarraInvertida> lexema: "+ yytext());
                    return new Symbol(Simbolos.ASSCI, yycolumn, yyline, yytext());
            } 
            // fall through
          case 75: break;
          case 36: 
            { System.out.println("Reconocio token: <cadena> lexema: "+ yytext());
                    return new Symbol(Simbolos.Cadena, yycolumn, yyline, yytext());
            } 
            // fall through
          case 76: break;
          case 37: 
            { System.out.println("Reconocio token: <comentario> lexema: "+ yytext());
            } 
            // fall through
          case 77: break;
          case 38: 
            { System.out.println("Reconocio token: <flecha> lexema: "+ yytext());
                    return new Symbol(Simbolos.flecha, yycolumn, yyline, yytext());
            } 
            // fall through
          case 78: break;
          case 39: 
            { System.out.println("Reconocio token: <comentarioMulti> lexema: "+ yytext());
            } 
            // fall through
          case 79: break;
          case 40: 
            { System.out.println("Reconocio token : <CONJ> lexema: "+ yytext());
                    return new Symbol(Simbolos.CONJ, yycolumn, yyline, yytext());
            } 
            // fall through
          case 80: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}