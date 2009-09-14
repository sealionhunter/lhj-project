/*
 * All Rights Reserved. Copyright(C) LHJ 2009.
 */
package org.lhj.learn.service.jaxws;

import java.math.BigDecimal;

import javax.jws.WebService;

/**
 * Converter service interface
 * @author sealion
 *
 */
@WebService(name="ConverterPortType",
        targetNamespace="http://services.lhj.org/ConverterPortType")
public interface Converter {
    /**
     * converte dollar to euro
     * @param dollars dollars
     * @return euro
     */
    BigDecimal dollarToEuro(BigDecimal dollars);

}
