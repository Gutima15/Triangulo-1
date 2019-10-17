/*
 * @(#)UntilCommand.java                        2.1 2003/10/07
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class UntilCommand extends Command {
  public Expression E;
  public Command C;
  
  public UntilCommand (Expression eAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    E = eAST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitUntilCommand(this, o);
  }
}