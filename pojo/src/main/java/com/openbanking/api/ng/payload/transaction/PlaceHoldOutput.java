package com.openbanking.api.ng.payload.transaction;

import lombok.Data;

@Data
public class PlaceHoldOutput {
    private String holdReferenceId = "1";
    private String responseCode = "00";
    private String responseDescription = "Successful";

}
