/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.SyntacticAnalyzer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
        
public class WriterHTML {
    private String fileName;
	private Queue<String> dataQueue;
	private String lastTag;
	private String CSS_CODE = "<style>" +
					"div.code{ font-family : \"Courier New\", \"Lucida Console\"; font-size : 1em;}\n" +
					"resword{font-weight: bold; }\n" +
					"literal{color: #11A8FF;}\n" +
					"comment{color: #00A310;}" +
					"</style>";

	public WriterHTML(String fileName) {
		this.fileName = fileName;
		dataQueue = new LinkedList<>();
		lastTag = "";
		writeHeader();
	}
        
        private void writeHeader(){
		this.dataQueue.add("<!DOCTYPE html> <html>");
		this.dataQueue.add("<head>\n");
		this.dataQueue.add(CSS_CODE);
		this.dataQueue.add("</head>");
		this.dataQueue.add("<body>");
		this.dataQueue.add("<div class= \"code\">");
	}

	public void closeTag(String pTag){
		this.dataQueue.add( String.format("</%s>", pTag) );
	}

	public void writeSeparator(char pSeparator) {
		String rawText;
		switch (pSeparator){
			case '\n':
				rawText = "<br>";
				break;
			case '\t':
				rawText = "<span class=\"mtk1\">&nbsp;&nbsp;</span>";
				break;
			case '!':
				rawText = "<comment>!";
				break;
			case ' ':
				rawText = "&nbsp;";
				break;
			default:
				rawText = Character.toString(pSeparator);
		}
		this.dataQueue.add(rawText);
	}

	public void write(String pText, int pKind) {
		String tag;

		if (!lastTag.equals(""))
			closeTag(lastTag);

		switch(pKind){
			case Token.IDENTIFIER:
			{
				tag = "identifier";
			}
			break;

			case Token.CHARLITERAL:
			case Token.INTLITERAL:
			{
				tag = "literal";
			}
			break;

			default:
				if (pKind < Token.getLastReservedWord())
					tag = "resword";//String.format("<common> %s </common>", pText);
				else
					tag = "identifier";
		}

		this.dataQueue.add(String.format("<%s>%s</%s>", tag, pText, tag));
		lastTag = tag;
	}

	public boolean save(){
		// Prepare the file to write

		this.dataQueue.add("</div>");
		this.dataQueue.add("</body>");
		this.dataQueue.add("</<html>");

		try {
			FileWriter fileWriter = new FileWriter(fileName);

			while (!this.dataQueue.isEmpty())
			//HTML header
				fileWriter.write(this.dataQueue.remove());

			fileWriter.close();

		} catch (IOException e) {
			System.err.println("Error while creating file for print the AST");
			e.printStackTrace();
		}
		return true;
	}
}
