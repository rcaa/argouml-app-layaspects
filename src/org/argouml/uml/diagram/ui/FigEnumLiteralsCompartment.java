// $Id: FigEnumLiteralsCompartment.java 17045 2009-04-05 16:52:52Z mvw $
// Copyright (c) 1996-2009 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.uml.diagram.ui;

import java.awt.Rectangle;
import java.util.Collection;

import org.argouml.model.Model;
import org.argouml.notation.NotationProviderFactory2;
import org.argouml.ui.targetmanager.TargetManager;
import org.argouml.uml.diagram.DiagramSettings;
import org.argouml.uml.diagram.static_structure.ui.FigEnumerationLiteral;

/**
 * The Fig for the compartment of an Enumeration 
 * that shows a list of enumeration literals.
 * 
 * @author Tom Morris
 */
public class FigEnumLiteralsCompartment extends FigEditableCompartment {
    /**
     * Serial version for initial implementation.
     */
    private static final long serialVersionUID = 829674049363538379L;

    /**
     * Constructor.
     * 
     * @param owner owning UML element
     * @param bounds bounding rectangle
     * @param settings render settings
     */
    public FigEnumLiteralsCompartment(Object owner, Rectangle bounds, 
            DiagramSettings settings) {
        super(owner, bounds, settings);
        super.populate();
        
        // TODO: We don't really want this to be filled, but if it's not then
        // the user can't double click in the compartment to add a new literal
        // Apparently GEF thinks unfilled figs are only selectable by border
//        setFilled(false);
    }
    
    /*
     * @see org.argouml.uml.diagram.ui.FigEditableCompartment#getUmlCollection()
     */
    protected Collection getUmlCollection() {
        return Model.getFacade().getEnumerationLiterals(getOwner());
    }

    /*
     * @see org.argouml.uml.diagram.ui.FigEditableCompartment#getNotationType()
     */
    protected int getNotationType() {
        /* The EnumerationLiteral uses a dedicated notation that supports 
         * parsing "name1;name2;name3" and stereotypes. 
         * Also supports deleting a literal by erasing text. */
        return NotationProviderFactory2.TYPE_ENUMERATION_LITERAL;
    }

    /**
     * @see org.argouml.uml.diagram.ui.FigEditableCompartment#createModelElement()
     */
    protected void createModelElement() {
        Object enumeration = getGroup().getOwner();
        Object literal = Model.getCoreFactory().buildEnumerationLiteral(
                "literal",  enumeration);
        TargetManager.getInstance().setTarget(literal);
    }

    @Override
    protected FigSingleLineTextWithNotation createFigText(Object owner,
            Rectangle bounds, DiagramSettings settings) {
        return new FigEnumerationLiteral(owner, bounds, settings);
    }  
}
