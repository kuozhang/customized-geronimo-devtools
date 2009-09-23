//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.07.17 at 01:03:24 AM CST 
//


package org.apache.geronimo.xml.ns.j2ee.web_1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.apache.geronimo.xml.ns.j2ee.web_1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WebApp_QNAME = new QName("http://geronimo.apache.org/xml/ns/j2ee/web-1.1", "web-app");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.apache.geronimo.xml.ns.j2ee.web_1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WebAppType }
     * 
     */
    public WebAppType createWebAppType() {
        return new WebAppType();
    }

    /**
     * Create an instance of {@link ContainerConfigType }
     * 
     */
    public ContainerConfigType createContainerConfigType() {
        return new ContainerConfigType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WebAppType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://geronimo.apache.org/xml/ns/j2ee/web-1.1", name = "web-app")
    public JAXBElement<WebAppType> createWebApp(WebAppType value) {
        return new JAXBElement<WebAppType>(_WebApp_QNAME, WebAppType.class, null, value);
    }

}
