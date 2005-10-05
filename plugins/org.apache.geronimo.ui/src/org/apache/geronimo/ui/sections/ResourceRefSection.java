/**
 * Copyright 2004, 2005 The Apache Software Foundation or its licensors, as applicable
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.geronimo.ui.sections;

import org.apache.geronimo.ui.internal.Messages;
import org.apache.geronimo.ui.wizards.ResourceRefWizard;
import org.apache.geronimo.xml.ns.naming.NamingFactory;
import org.apache.geronimo.xml.ns.naming.NamingPackage;
import org.apache.geronimo.xml.ns.web.WebAppType;
import org.apache.geronimo.xml.ns.web.WebFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ResourceRefSection extends DynamicTableSection {

    public ResourceRefSection(WebAppType plan, Composite parent,
            FormToolkit toolkit, int style) {
        super(plan, parent, toolkit, style);
        create();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.geronimo.ui.sections.DynamicTableSection#getTitle()
     */
    public String getTitle() {
        return Messages.editorResourceRefTitle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.geronimo.ui.sections.DynamicTableSection#getDescription()
     */
    public String getDescription() {
        return Messages.editorResourceRefDescription;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.geronimo.ui.sections.DynamicTableSection#getEFactory()
     */
    public EFactory getEFactory() {
        return NamingFactory.eINSTANCE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.geronimo.ui.sections.DynamicTableSection#getEReference()
     */
    public EReference getEReference() {
        return WebFactory.eINSTANCE.getWebPackage().getWebAppType_ResourceRef();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.geronimo.ui.sections.DynamicTableSection#getTableColumnNames()
     */
    public String[] getTableColumnNames() {
        return new String[] { Messages.editorResRefNameTitle,
                Messages.editorResRefLinkTitle,
                Messages.editorResRefTargetNameTitle };
    }

    public Class getWizardClass() {
        return ResourceRefWizard.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.geronimo.ui.sections.DynamicTableSection#getTableColumnEAttributes()
     */
    public EAttribute[] getTableColumnEAttributes() {
        return new EAttribute[] {
                NamingPackage.eINSTANCE.getResourceRefType_RefName(),
                NamingPackage.eINSTANCE.getResourceRefType_ResourceLink(),
                NamingPackage.eINSTANCE.getResourceRefType_TargetName() };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.geronimo.ui.sections.DynamicTableSection#getWizard()
     */
    public Wizard getWizard() {
        return new ResourceRefWizard(this);
    }

}
