/*******************************************************************************
 * Copyright (c) 2009-2013 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   * Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI
 *   * Tijs van der Storm - Tijs.van.der.Storm@cwi.nl
 *   * Paul Klint - Paul.Klint@cwi.nl - CWI
 *   * Mark Hills - Mark.Hills@cwi.nl (CWI)
 *   * Arnold Lankamp - Arnold.Lankamp@cwi.nl
 *   * Michael Steindorfer - Michael.Steindorfer@cwi.nl - CWI
 *******************************************************************************/
package org.rascalmpl.ast;


import org.eclipse.imp.pdb.facts.IConstructor;

public abstract class Assignment extends AbstractAST {
  public Assignment(IConstructor node) {
    super();
  }

  

  

  
  public boolean isAddition() {
    return false;
  }

  static public class Addition extends Assignment {
    // Production: sig("Addition",[])
  
    
  
    public Addition(IConstructor node ) {
      super(node);
      
    }
  
    @Override
    public boolean isAddition() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitAssignmentAddition(this);
    }
  
    	
  }
  public boolean isAppend() {
    return false;
  }

  static public class Append extends Assignment {
    // Production: sig("Append",[])
  
    
  
    public Append(IConstructor node ) {
      super(node);
      
    }
  
    @Override
    public boolean isAppend() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitAssignmentAppend(this);
    }
  
    	
  }
  public boolean isDefault() {
    return false;
  }

  static public class Default extends Assignment {
    // Production: sig("Default",[])
  
    
  
    public Default(IConstructor node ) {
      super(node);
      
    }
  
    @Override
    public boolean isDefault() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitAssignmentDefault(this);
    }
  
    	
  }
  public boolean isDivision() {
    return false;
  }

  static public class Division extends Assignment {
    // Production: sig("Division",[])
  
    
  
    public Division(IConstructor node ) {
      super(node);
      
    }
  
    @Override
    public boolean isDivision() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitAssignmentDivision(this);
    }
  
    	
  }
  public boolean isIfDefined() {
    return false;
  }

  static public class IfDefined extends Assignment {
    // Production: sig("IfDefined",[])
  
    
  
    public IfDefined(IConstructor node ) {
      super(node);
      
    }
  
    @Override
    public boolean isIfDefined() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitAssignmentIfDefined(this);
    }
  
    	
  }
  public boolean isIntersection() {
    return false;
  }

  static public class Intersection extends Assignment {
    // Production: sig("Intersection",[])
  
    
  
    public Intersection(IConstructor node ) {
      super(node);
      
    }
  
    @Override
    public boolean isIntersection() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitAssignmentIntersection(this);
    }
  
    	
  }
  public boolean isProduct() {
    return false;
  }

  static public class Product extends Assignment {
    // Production: sig("Product",[])
  
    
  
    public Product(IConstructor node ) {
      super(node);
      
    }
  
    @Override
    public boolean isProduct() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitAssignmentProduct(this);
    }
  
    	
  }
  public boolean isSubtraction() {
    return false;
  }

  static public class Subtraction extends Assignment {
    // Production: sig("Subtraction",[])
  
    
  
    public Subtraction(IConstructor node ) {
      super(node);
      
    }
  
    @Override
    public boolean isSubtraction() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitAssignmentSubtraction(this);
    }
  
    	
  }
}