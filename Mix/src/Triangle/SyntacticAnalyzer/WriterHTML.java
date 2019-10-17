package Triangle.SyntacticAnalyzer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
        
public class WriterHTML {
    private String fileName;//Nombre archivo
    private Queue<String> queue;//Cola de tipo string, se ingresa el html
    private String endTag;//Cierre de etiqueta
    private String estilo = 
        "<style>" +
	"div.code{ font-family : \"Courier New\", \"Lucida Console\"; font-size : 1em;}\n" +
        "reservedword{font-weight: bold; }\n" +
	"literal{color: #013ba5;}\n" +
        "comment{color: #53a501;}" +
	"</style>";//Se presenta el estilo que contiene el html, así los colores de las asignaciones solicitadas
        
        //Inicialización del html con el nombre del archivo
	public WriterHTML(String fileName) {
            this.fileName = fileName;
            queue = new LinkedList<>();
            endTag = "";
            writeHeader();
	}
        
        //Header del html, cada parte de la estructura se agrega a la cola
        private void writeHeader(){
            this.queue.add("<!DOCTYPE html> <html>");
            this.queue.add("<head>\n");
            this.queue.add("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
            this.queue.add(estilo);
            this.queue.add("</head>");
            this.queue.add("<body>");
            this.queue.add("<div class= \"code\">");
	}
        //Genera el cierre de las etiquetas
	public void EndTag(String tag){
		this.queue.add( String.format("</%s>", tag) );
	}
        
        //Asigna su correspondiente en html según como termina la palabra
	public void writeSeparator(char caracter) {
		String separator;
		switch (caracter){
                    case '!':
			separator = "<comment>!";
			break;
                    case '\n':
			separator = "<br>";
			break;
                    case ' ':
			separator = "&nbsp;";
			break;
                    case '\t':
			separator = "<span class=\"mtk1\">&nbsp;&nbsp;</span>";
			break;
                    default:
			separator = Character.toString(caracter);
		}
		this.queue.add(separator);
	}
        
        //Escribe el html 
	public void write(String entry, int type) {
		String tag;//Etiqueta
		if (!endTag.equals(""))//Si no hay más palabras, termina el html
			EndTag(endTag);
                //Asigna si es un identificador, literal,comentario o otra palabra según su formato
		switch(type){
                    case Token.CHARLITERAL:
                    case Token.IDENTIFIER:{
			tag = "identifier";
                    }
                    break;	
                    case Token.INTLITERAL:{
			tag = "literal";
                    }
                    break;
                    default:
			if (type < Token.getLastReservedWord())//Verificar si es una palabra reservada
                            tag = "reservedword";
			else
                            tag = "identifier";
		}

		this.queue.add(String.format("<%s>%s</%s>", tag, entry, tag));
		endTag = tag;
	}
        // Prepara el archivo para escribir y guardar
	public boolean save(){
                //Etiquetas de cierre
		this.queue.add("</div>");
		this.queue.add("</body>");
		this.queue.add("</<html>");
		try {
                    FileWriter fileWriter = new FileWriter(fileName);
                    while (!this.queue.isEmpty())//Lo hace si la cola no esté vacía
			fileWriter.write(this.queue.remove());
			fileWriter.close();
		} catch (IOException e) {
                    System.err.println("Error while creating file for print the AST");
                    e.printStackTrace();
		}
		return true;
	}
}
