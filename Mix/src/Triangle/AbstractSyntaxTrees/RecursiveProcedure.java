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
public class RecursiveProcedure extends ProcFunc {
    public Identifier I;
    public FormalParameterSequence FPS;
    public Command C;
        
    public RecursiveProcedure(Identifier iAST, FormalParameterSequence fpsAST, Command cAST, SourcePosition procFuncPos) {
        super(procFuncPos);
        I = iAST;
        FPS = fpsAST;
        C = cAST;       
    }
    public Object visit(Visitor v, Object o) {
        return v.visitRecursiveProcedure(this, o);
    }
    
}
