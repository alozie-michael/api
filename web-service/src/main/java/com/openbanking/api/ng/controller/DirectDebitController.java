package com.openbanking.api.ng.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openbanking.api.ng.bank.exception.BankResourceNotFoundException;
import com.openbanking.api.ng.bank.exception.ServiceOperationNotSupported;
import com.openbanking.api.ng.bank.service.BankAccountService;
import com.openbanking.api.ng.definition.OperationStatus;
import com.openbanking.api.ng.payload.GenericServiceResponse;
import com.openbanking.api.ng.payload.GenericServiceResponseBuilder;
import com.openbanking.api.ng.payload.customer.PocessingOperationResponse;
import com.openbanking.api.ng.payload.directdebit.DirectDebit;
import com.openbanking.api.ng.payload.directdebit.DirectDebitCancelRequest;
import com.openbanking.api.ng.payload.directdebit.DirectDebitSetup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/dd")
@Api(value = "Direct Debit", description = "Direct Debit related operations", consumes = "application/json", tags = {"direct_debit"})

public class DirectDebitController extends BaseApiController{
	
	@Autowired
	private BankAccountService bankAccountService;
	
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<GenericServiceResponse> setupDirectDebit(@RequestBody DirectDebitSetup directDebitSetup) throws BankResourceNotFoundException, ServiceOperationNotSupported {
    	PocessingOperationResponse pResponse=bankAccountService.setupDirectDebit(directDebitSetup);
        return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponse()
                .withData(Collections.singletonList(pResponse))
                .withStatus(OperationStatus.SUCCESSFUL)
                .withMessage(OperationStatus.SUCCESSFUL.name())
                .build());
    }

    @RequestMapping(value = "/accountNumber/{accountNumber}/referenceNumber/{referenceNumber}", method = RequestMethod.GET)
    public ResponseEntity<GenericServiceResponse> getDirectDebit(@PathVariable @ApiParam(value = "Reference Number") String referenceNumber,
                                                                 @PathVariable @ApiParam(value = "Account Number") String accountNumber) throws BankResourceNotFoundException, ServiceOperationNotSupported {
    	DirectDebit pResponse=bankAccountService.getDirectDebit(accountNumber,referenceNumber);
        return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponse()
                .withData(new DirectDebit())
                .withStatus(OperationStatus.SUCCESSFUL)
                .withMessage(OperationStatus.SUCCESSFUL.name())
                .build());
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public ResponseEntity<GenericServiceResponse> cancelDirectDebit(@RequestBody DirectDebitCancelRequest directDebitCancelRequest) throws BankResourceNotFoundException, ServiceOperationNotSupported {
    	PocessingOperationResponse pResponse=bankAccountService.cancelDirectDebit(directDebitCancelRequest);
        return ResponseEntity.ok(GenericServiceResponseBuilder.aGenericServiceResponse()
                .withData(pResponse)
        		.withStatus(OperationStatus.SUCCESSFUL)
                .withMessage("Direct Debit cancelled successfully")
                .build());
    }

}