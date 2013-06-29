package org.argouml.guillemets;

import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import org.argouml.kernel.ProjectSettings;
import org.argouml.notation.NotationSettings;
import org.argouml.notation.ui.SettingsTabNotation;
import org.argouml.util.Driver;

public privileged aspect ArgoUMLGuillemetsDynamic extends ArgoUMLGuillemetsAbstract {

    pointcut driver() : if(Driver.getDriver("argo.notation.guillemots").equals("true"));

    pointcut createCheckBoxGuillemotsHook(JPanel settings,
            GridBagConstraints constraints, SettingsTabNotation cthis) 
        : ArgoUMLGuillemetsAbstract.createCheckBoxGuillemotsHook(settings, constraints, cthis) && driver();

    pointcut isUseGuillemets(NotationSettings cthis) 
    : ArgoUMLGuillemetsAbstract.isUseGuillemets(cthis) && driver();

    pointcut getUseGuillemotsValue(ProjectSettings cthis) 
    : ArgoUMLGuillemetsAbstract.getUseGuillemotsValue(cthis) && driver();

    Object around() : adviceexecution() && within(org.argouml.guillemets.ArgoUMLGuillemetsAbstract) && !driver() {
        return null;
    }
}