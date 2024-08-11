package com.natu.remotedebugger.connection;

import jakarta.validation.constraints.NotNull;

public class SuccessResponse {
    public final boolean success;

    public SuccessResponse(boolean success) {
        this.success = success;
    }

    @NotNull
    public boolean isSuccess() {
        return success;
    }

}
