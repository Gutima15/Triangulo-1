/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequencialProcFunc extends ProcFunc {
    public ProcFunc P1, P2;
    
    public SequencialProcFunc (ProcFunc p1AST, ProcFunc p2AST, SourcePosition thePosition) {
    super (thePosition);
    P1 = p1AST;
    P2 = p2AST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitSequentialProcFunc(this, o);
  }

  
}
