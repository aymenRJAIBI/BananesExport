package com.exportBanane.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OrdreNoPermittedException extends RuntimeException {
    private final String errorMsg;

    private final String operationId;

    private final String source;


}
