//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.07.17 at 01:03:24 AM CST 
//


package org.apache.geronimo.xml.ns.j2ee.connector_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for xatransactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="xatransactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transaction-caching" type="{http://geronimo.apache.org/xml/ns/j2ee/connector-1.1}emptyType" minOccurs="0"/>
 *         &lt;element name="thread-caching" type="{http://geronimo.apache.org/xml/ns/j2ee/connector-1.1}emptyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "xatransactionType", propOrder = {
    "transactionCaching",
    "threadCaching"
})
public class XatransactionType {

    @XmlElement(name = "transaction-caching")
    protected EmptyType transactionCaching;
    @XmlElement(name = "thread-caching")
    protected EmptyType threadCaching;

    /**
     * Gets the value of the transactionCaching property.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getTransactionCaching() {
        return transactionCaching;
    }

    /**
     * Sets the value of the transactionCaching property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setTransactionCaching(EmptyType value) {
        this.transactionCaching = value;
    }

    /**
     * Gets the value of the threadCaching property.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getThreadCaching() {
        return threadCaching;
    }

    /**
     * Sets the value of the threadCaching property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setThreadCaching(EmptyType value) {
        this.threadCaching = value;
    }

}
