/*
 * IDE-Triangle v1.0
 * Compiler.java 
 */

package Triangle;

import Triangle.CodeGenerator.Frame;
import java.awt.event.ActionListener;
import Triangle.SyntacticAnalyzer.SourceFile;
import Triangle.SyntacticAnalyzer.Scanner;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.SyntacticAnalyzer.Parser;
import Triangle.ContextualAnalyzer.Checker;
import Triangle.CodeGenerator.Encoder;
import Triangle.TreeWriterHTML.Writer;



/** 
 * This is merely a reimplementation of the Triangle.Compiler class. We need
 * to get to the ASTs in order to draw them in the IDE without modifying the
 * original Triangle code.
 *
 * @author Luis Leopoldo P�rez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class IDECompiler {

    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    /**
     * Creates a new instance of IDECompiler.
     *
     */
    public IDECompiler() {
    }
    
    /**
     * Particularly the same compileProgram method from the Triangle.Compiler
     * class.
     * @param sourceName Path to the source file.
     * @return True if compilation was succesful.
     */
    public boolean compileProgram(String sourceName) {
        System.out.println("********** " +
                           "Triangle Compiler (IDE-Triangle 1.0)" +
                           " **********");
        
        System.out.println("Syntactic Analysis ...");
        SourceFile source = new SourceFile(sourceName);
        Scanner scanner = new Scanner(source);
        scanner.enableWriting(sourceName.replace(".tri", ".html")); //--
        report = new IDEReporter();
        Parser parser = new Parser(scanner, report);
        boolean success = false;
        
        rootAST = parser.parseProgram();
        if (report.numErrors == 0) {
            System.out.println("Contextual Analysis ...");//Comentar para que funcione el sint�ctico solo
            Checker checker = new Checker(report);        //Comentar para que funcione el sint�ctico solo
            checker.check(rootAST);                       //Comentar para que funcione el sint�ctico solo
            if (report.numErrors == 0) {
              //  System.out.println("Code Generation ...");//Comentar para que funcione el sint�ctico solo
//                Encoder encoder = new Encoder(report);    //Comentar para que funcione el sint�ctico solo
  //              encoder.encodeRun(rootAST, false);        //Comentar para que funcione el sint�ctico solo
                
                if (report.numErrors == 0) {
    //                encoder.saveObjectProgram(sourceName.replace(".tri", ".tam"));//Comentar para que funcione el sint�ctico solo
                    success = true;
                }
            }
        }

        if (success){
            System.out.println("Compilation was successful.");
            System.out.println("Generating html file...");
            scanner.finishWriting();
        }else{
            System.out.println("Compilation was unsuccessful.");
        }
        
        Writer w = new Writer("astTree.xml");
        w.write(getAST());
        return(success);
    }
      
    /**
     * Returns the line number where the first error is.
     * @return Line number.
     */
    public int getErrorPosition() {
        return(report.getFirstErrorPosition());
    }
        
    /**
     * Returns the root Abstract Syntax Tree.
     * @return Program AST (root).
     */
    public Program getAST() {
        return(rootAST);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    private Program rootAST;        // The Root Abstract Syntax Tree.    
    private IDEReporter report;     // Our ErrorReporter class.
    // </editor-fold>
}
