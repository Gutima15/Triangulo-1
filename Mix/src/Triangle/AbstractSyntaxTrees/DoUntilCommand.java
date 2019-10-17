package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class DoUntilCommand extends Command {
  public Expression E;
  public Command C;
  
  public DoUntilCommand (Expression eAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    E = eAST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitDoUntilCommand(this, o);
  }
}
