//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.10 at 04:26:17 �ߌ� JST 
//


package com.ustcsoft.generalsolution.dmat.webui.menu;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for menuType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="menuType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="folder"/>
 *     &lt;enumeration value="item"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "menuType")
@XmlEnum
public enum MenuType {

    @XmlEnumValue("folder")
    FOLDER("folder"),
    @XmlEnumValue("item")
    ITEM("item");
    private final String value;

    MenuType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MenuType fromValue(String v) {
        for (MenuType c: MenuType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
