package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForCommand extends Command {
  public ForDeclaration FD;
  public Expression E2;
  public Command C1;
  
  
  public ForCommand (ForDeclaration fdAST, Expression eAST, Command c1AST, SourcePosition thePosition) {
    super (thePosition);        
    E2 = eAST;
    C1 = c1AST;
    FD = fdAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitForCommand(this, o);
  }
}
