//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.07.17 at 01:03:24 AM CST 
//


package org.openejb.xml.ns.openejb_jar_2;

import javax.xml.bind.annotation.XmlEnum;


/**
 * <p>Java class for transport-guaranteeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="transport-guaranteeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="INTEGRAL"/>
 *     &lt;enumeration value="CONFIDENTIAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum TransportGuaranteeType {

    NONE,
    INTEGRAL,
    CONFIDENTIAL;

    public String value() {
        return name();
    }

    public static TransportGuaranteeType fromValue(String v) {
        return valueOf(v);
    }

}
