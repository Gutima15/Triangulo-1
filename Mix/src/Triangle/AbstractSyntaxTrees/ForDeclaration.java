/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;
import Triangle.SyntacticAnalyzer.SourcePosition;
/**
 *
 * @author jorge
 */
public class ForDeclaration extends Declaration{
  public Expression E;
  public Identifier I;
  
  public ForDeclaration (Identifier iAST, Expression eAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    E = eAST;    
  }

  public Object visit(Visitor v, Object o) {
    return v.visitForDeclaration(this, o);
  }
}
