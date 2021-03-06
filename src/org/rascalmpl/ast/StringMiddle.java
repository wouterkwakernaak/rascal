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

public abstract class StringMiddle extends AbstractAST {
  public StringMiddle(IConstructor node) {
    super();
  }

  
  public boolean hasExpression() {
    return false;
  }

  public org.rascalmpl.ast.Expression getExpression() {
    throw new UnsupportedOperationException();
  }
  public boolean hasMid() {
    return false;
  }

  public org.rascalmpl.ast.MidStringChars getMid() {
    throw new UnsupportedOperationException();
  }
  public boolean hasTail() {
    return false;
  }

  public org.rascalmpl.ast.StringMiddle getTail() {
    throw new UnsupportedOperationException();
  }
  public boolean hasTemplate() {
    return false;
  }

  public org.rascalmpl.ast.StringTemplate getTemplate() {
    throw new UnsupportedOperationException();
  }

  

  
  public boolean isInterpolated() {
    return false;
  }

  static public class Interpolated extends StringMiddle {
    // Production: sig("Interpolated",[arg("org.rascalmpl.ast.MidStringChars","mid"),arg("org.rascalmpl.ast.Expression","expression"),arg("org.rascalmpl.ast.StringMiddle","tail")])
  
    
    private final org.rascalmpl.ast.MidStringChars mid;
    private final org.rascalmpl.ast.Expression expression;
    private final org.rascalmpl.ast.StringMiddle tail;
  
    public Interpolated(IConstructor node , org.rascalmpl.ast.MidStringChars mid,  org.rascalmpl.ast.Expression expression,  org.rascalmpl.ast.StringMiddle tail) {
      super(node);
      
      this.mid = mid;
      this.expression = expression;
      this.tail = tail;
    }
  
    @Override
    public boolean isInterpolated() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitStringMiddleInterpolated(this);
    }
  
    
    @Override
    public org.rascalmpl.ast.MidStringChars getMid() {
      return this.mid;
    }
  
    @Override
    public boolean hasMid() {
      return true;
    }
    @Override
    public org.rascalmpl.ast.Expression getExpression() {
      return this.expression;
    }
  
    @Override
    public boolean hasExpression() {
      return true;
    }
    @Override
    public org.rascalmpl.ast.StringMiddle getTail() {
      return this.tail;
    }
  
    @Override
    public boolean hasTail() {
      return true;
    }	
  }
  public boolean isMid() {
    return false;
  }

  static public class Mid extends StringMiddle {
    // Production: sig("Mid",[arg("org.rascalmpl.ast.MidStringChars","mid")])
  
    
    private final org.rascalmpl.ast.MidStringChars mid;
  
    public Mid(IConstructor node , org.rascalmpl.ast.MidStringChars mid) {
      super(node);
      
      this.mid = mid;
    }
  
    @Override
    public boolean isMid() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitStringMiddleMid(this);
    }
  
    
    @Override
    public org.rascalmpl.ast.MidStringChars getMid() {
      return this.mid;
    }
  
    @Override
    public boolean hasMid() {
      return true;
    }	
  }
  public boolean isTemplate() {
    return false;
  }

  static public class Template extends StringMiddle {
    // Production: sig("Template",[arg("org.rascalmpl.ast.MidStringChars","mid"),arg("org.rascalmpl.ast.StringTemplate","template"),arg("org.rascalmpl.ast.StringMiddle","tail")])
  
    
    private final org.rascalmpl.ast.MidStringChars mid;
    private final org.rascalmpl.ast.StringTemplate template;
    private final org.rascalmpl.ast.StringMiddle tail;
  
    public Template(IConstructor node , org.rascalmpl.ast.MidStringChars mid,  org.rascalmpl.ast.StringTemplate template,  org.rascalmpl.ast.StringMiddle tail) {
      super(node);
      
      this.mid = mid;
      this.template = template;
      this.tail = tail;
    }
  
    @Override
    public boolean isTemplate() { 
      return true; 
    }
  
    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
      return visitor.visitStringMiddleTemplate(this);
    }
  
    
    @Override
    public org.rascalmpl.ast.MidStringChars getMid() {
      return this.mid;
    }
  
    @Override
    public boolean hasMid() {
      return true;
    }
    @Override
    public org.rascalmpl.ast.StringTemplate getTemplate() {
      return this.template;
    }
  
    @Override
    public boolean hasTemplate() {
      return true;
    }
    @Override
    public org.rascalmpl.ast.StringMiddle getTail() {
      return this.tail;
    }
  
    @Override
    public boolean hasTail() {
      return true;
    }	
  }
}