package org.argouml.guillemets;

import java.awt.GridBagConstraints;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;
import org.argouml.kernel.ProjectSettings;
import org.argouml.notation.NotationSettings;
import org.argouml.notation.ui.SettingsTabNotation;
import org.argouml.util.Driver;

public privileged aspect ArgoUMLGuillemetsDynamic extends ArgoUMLGuillemetsAbstract {

    pointcut driver() : if(Driver.getDriver("argo.notation.guillemots").equals("true"));

    pointcut isChangePropertyGuillemetsHook(final PropertyChangeEvent pce) 
    : ArgoUMLGuillemetsAbstract.isChangePropertyGuillemetsHook(pce) && driver();

    pointcut isUseGuillemets(NotationSettings cthis) 
    : ArgoUMLGuillemetsAbstract.isUseGuillemets(cthis) && driver();

    pointcut getUseGuillemotsValue(ProjectSettings cthis) 
    : ArgoUMLGuillemetsAbstract.getUseGuillemotsValue(cthis) && driver();

    Object around() : adviceexecution() && within(org.argouml.guillemets.ArgoUMLGuillemetsAbstract) && !driver() {
        return null;
    }
}