/*
 * @(#)Token.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.SyntacticAnalyzer;


final class Token extends Object {

  protected int kind;
  protected String spelling;
  protected SourcePosition position;

  public Token(int kind, String spelling, SourcePosition position) {

    if (kind == Token.IDENTIFIER) {
      int currentKind = firstReservedWord;
      boolean searching = true;

      while (searching) {
        int comparison = tokenTable[currentKind].compareTo(spelling);
        if (comparison == 0) {
          this.kind = currentKind;
          searching = false;
        } else if (comparison > 0 || currentKind == lastReservedWord) {
          this.kind = Token.IDENTIFIER;
          searching = false;
        } else {
          currentKind ++;
            //System.out.println(tokenTable[currentKind] + " spell: "+spelling);
        }
      }
    } else
      this.kind = kind;

    this.spelling = spelling;
    this.position = position;

  }

  public static String spell (int kind) {
    return tokenTable[kind];
  }

  public String toString() {
    return "Kind=" + kind + ", spelling=" + spelling +
      ", position=" + position;
  }

  // Token classes...

  public static final int

    // literals, identifiers, operators...
    INTLITERAL	= 0,
    CHARLITERAL	= 1,
    IDENTIFIER	= 2,
    OPERATOR	= 3,

    // reserved words - must be in alphabetical order...
    AND         = 4, //
   // BEGIN	= 5,
    ARRAY 	= 5,
    CONST 	= 6,
    DO 		= 7,
    ELSE 	= 8,
    END 	= 9,
    FOR 	= 10, //Se agrega el token FOR para realizar las modificaciones correspondientes en single-command
    FUNC 	= 11,
    IF 		= 12,
    IN 		= 13,
    INIT 	= 14, //
    LET 	= 15,
    LOCAL 	= 16, //
    LOOP 	= 17, //Se agrega el token LOOP para realizar las modificaciones correspondientes en single-command
    OF 		= 18,
    PROC 	= 19, 
    RECORD      = 20, 
    RECURSIVE   = 21, //
    REPEAT      = 22, //Se agrega el token REPEAT para realizar las modificaciones correspondientes en single-command
    SKIP        = 23, //Se agrega el token SKIP para reemplazar EOT en single-Command
    THEN        = 24, 
    TO          = 25, //Se agrega el token TO para realizar las modificaciones correspondientes en single-command
    TYPE        = 26,
    UNTIL       = 27, //Se agrega el token UNTIL para realizar las modificaciones correspondientes en single-command
    VAR         = 28,
    WHILE	= 29,

    // punctuation...
    DOT			= 30,
    COLON		= 31,
    SEMICOLON	        = 32,
    COMMA		= 33,
    BECOMES		= 34,
    IS			= 35,

    // brackets...
    LPAREN		= 36,
    RPAREN		= 37,
    LBRACKET	        = 38,
    RBRACKET	        = 39,
    LCURLY		= 40,
    RCURLY		= 41,

    // special tokens...
    EOT			= 42,
    ERROR		= 43;

  private static String[] tokenTable = new String[] {
    "<int>",
    "<char>",
    "<identifier>",
    "<operator>",

    "and", //
    "array",
    "const",
    "do",
    "else",
    "end",
    "for", // 
    "func",
    "if",
    "in",
    "init", //
    "let",
    "local", //
    "loop", //
    "of",
    "proc",
    "record",
    "recursive", //
    "repeat", //
    "skip", //
    "then",
    "to", //
    "type",
    "until", //
    "var",
    "while",
    ".",
    ":",
    ";",
    ",",
    ":=",
    "~",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "",
    "<error>"

  };

  private final static int	firstReservedWord = Token.AND,
  				lastReservedWord  = Token.WHILE;
  //Obtiene la última palabra reservada
  public static int getLastReservedWord(){
    return lastReservedWord;
  }

}
