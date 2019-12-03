/*
 * @(#)IdentificationTable.java                2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.ContextualAnalyzer;

import Triangle.AbstractSyntaxTrees.Declaration;
import Triangle.AbstractSyntaxTrees.ForCommand;

public final class IdentificationTable {

  private int level;
  private IdEntry latest;
  private IdentificationTable localEntries;
  private IdEntry LocalEntry; //Guarda la declaración D1 del local declacation
  private IdEntry LocalDone; // Guarda la declaración D2 del local declacation
  private boolean isLocalEntry;
  private boolean isLocalDone; 
  private boolean recursiveFlag;
  public IdentificationTable () {
    level = 0;
    latest = null;
  }

  public IdentificationTable (IdentificationTable main) {
    localEntries = main.localEntries;
  }

  // Opens a new level in the identification table, 1 higher than the
  // current topmost level.

  public void openScope () {

    level ++;
  }

  // Closes the topmost level in the identification table, discarding
  // all entries belonging to that level.

  public void closeScope () {

    IdEntry entry, local;

    // Presumably, idTable.level > 0.
    entry = this.latest;
    while (entry.level == this.level) {
      local = entry;
      entry = local.previous;
    }
    this.level--;
    this.latest = entry;
  }

// Modificaciones hechas por @StephanieDelgago.
  public void setLocalEntry(boolean localE) {
        this.isLocalEntry = localE;
    }

    public void setLocalDone(boolean localD ) {
        this.isLocalDone = localD;
    }  
   
    public void changingPointersLocalDeclaration(){
        IdEntry latestBeforeLocal = LocalEntry.previous;
        LocalDone.previous = latestBeforeLocal;
    }
    
  // Makes a new entry in the identification table for the given identifier
  // and attribute. The new entry belongs to the current level.
  // duplicated is set to to true iff there is already an entry for the
  // same identifier at the current level.

  public void enter (String id, Declaration attr) {

    IdEntry entry = this.latest;
    boolean present = false, searching = true;

    // Check for duplicate entry ...
    while (searching) {
      if (entry == null || entry.level < this.level){
        searching = false;
      }
      else if (entry.id.equals(id)) {
        present = true;
        searching = false;
       } else{
       entry = entry.previous;
      }
    }
    attr.duplicated = present;
    // Local Declarations
    //Added by @Jorge G
    if(isLocalEntry){        
        entry= new IdEntry(id, attr, this.level, this.latest);//Note que, cuando recibimos el inicio de una declaración local
        // tenemos que guardar la condición de la tabla justo antes de leer la declación.
        this.LocalEntry= entry; //instanciamos localEntry con los valores iniciales pre declaración local
        this.latest = entry; 
        this.isLocalEntry = false; //para indicar que terminamos
        
    }else if(isLocalDone){ //Lo mismo de arriba pero para registrar el fin de la declaración.
        entry= new IdEntry(id, attr, this.level, this.latest);
        this.LocalDone=entry;
        this.latest = entry;
        this.isLocalDone = false;
        
    }//Comportamiento en casos típicos del lenguaje.
    entry = new IdEntry(id, attr, this.level, this.latest);
    this.latest = entry;
  }

  // Finds an entry for the given identifier in the identification table,
  // if any. If there are several entries for that identifier, finds the
  // entry at the highest level, in accordance with the scope rules.
  // Returns null iff no entry is found.
  // otherwise returns the attribute field of the entry found.

  public Declaration retrieve (String id) {

    IdEntry entry;
    Declaration attr = null;
    boolean present = false, searching = true;

    entry = this.latest;
    while (searching) {
      if (entry == null)
        searching = false;
      else if (entry.id.equals(id)) {
        present = true;
        searching = false;
        attr = entry.attr;
      } else
        entry = entry.previous;
    }

    return attr;
  }
  public boolean isRecursiveFlag() {
    return recursiveFlag;
  }
 
  public void setRecursiveFlag(boolean recursiveFlag) {
    this.recursiveFlag = recursiveFlag;    
  }

    void enter(String spelling, ForCommand ast) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
