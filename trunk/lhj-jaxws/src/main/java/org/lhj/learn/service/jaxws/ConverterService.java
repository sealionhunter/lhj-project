/*
 * All Rights Reserved. Copyright(C) LHJ 2009.
 */
package org.lhj.learn.service.jaxws;

import java.math.BigDecimal;

import javax.jws.WebService;

/**
 * Converter service
 * @author sealion
 *
 */
@WebService(endpointInterface = "org.lhj.learn.service.jaxws.Converter",
        targetNamespace = "http://services.lhj.org/Converter",
        serviceName = "ConverterService",
        portName = "ConverterPortType")
public class ConverterService implements Converter {
    private BigDecimal rate = new BigDecimal(0.8);
    private static final BigDecimal ZERO = new BigDecimal(0);

    /**
     * converte dollar to euro
     * @param dollars dollars
     * @return euro
     */
    @Override
    public BigDecimal dollarToEuro(BigDecimal dollars) {
        BigDecimal result = dollars == null ? ZERO : dollars.multiply(rate);
        return result;
    }

}
