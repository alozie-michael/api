package com.openbanking.api.ng.payload.pos;

import java.util.Date;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import com.openbanking.api.ng.definition.OperationStatus;
import com.openbanking.api.ng.payload.ErrorResponse;

import lombok.Data;

@Data
public class POS {
    private String terminalId = "1";
    private String merchantId = "1";
    private String merchantName = "Sodiq Fagbola";
    private String email = "sodiq@openbanking.com";
    private String phone = "09000000000";
    private Date dateDeployed;
    private CurrencyUnit currency = Monetary.getCurrency("NGN");
    private String terminalType;
    private String ptsa;
    private String ptsp;
    private String status;
    private double latitude = 100;
    private double longitude = 19;

}
