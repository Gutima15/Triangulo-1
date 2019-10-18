/*
 * @(#)LocalDeclaration.java                        2.1 2003/10/07
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

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LocalDeclaration extends Declaration {

  

    public LocalDeclaration(Declaration dAST, Declaration dAST2, Declaration dAST3, SourcePosition declarationPos) {
        super (declarationPos);
        D1 = dAST;
        D2 = dAST2;
        D3 = dAST3;
    }

  public Object visit(Visitor v, Object o) {
    return v.visitLocalDeclaration(this, o);
  }  
  public Declaration D1;
  public Declaration D2;
  public Declaration D3;
}
