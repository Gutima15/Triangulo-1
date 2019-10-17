package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForCommand extends Command {
  public Expression E1,E2;
  public Command C1;
  public Identifier I1;
  
  public ForCommand (Identifier iAST, Expression eAST, Expression eAST2, Command c1AST, SourcePosition thePosition) {
    super (thePosition);
    I1 = iAST;
    E1 = eAST;
    E2 = eAST2;
    C1 = c1AST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitForCommand(this, o);
  }


}
