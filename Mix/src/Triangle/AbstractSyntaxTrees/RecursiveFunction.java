/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author keyro
 */
public class RecursiveFunction extends ProcFunc{
    public Identifier I;
    public FormalParameterSequence FPS;
    public TypeDenoter T;
    public Expression E;
            
    public RecursiveFunction(Identifier iAST, FormalParameterSequence fpsAST, TypeDenoter tAST, Expression eAST, SourcePosition procFuncPos) {
        super(procFuncPos);
        I= iAST;
        FPS = fpsAST;
        T = tAST;
        E = eAST;
    }
    public Object visit(Visitor v, Object o) {
    return v.visitRecursiveFunction(this, o);
  }
    
}

