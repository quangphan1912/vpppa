package com.gcs.vppa.core.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/14/2019.
 */
public class ProcessResult implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private Integer numberOfSuccess;

    private Integer numberOfFailure;

    private List<String> failures = new ArrayList<>();

    private String status;

    private String errorMessage;

    public ProcessResult() {
        this.numberOfSuccess = 0;
        this.numberOfFailure = 0;
    }

    public Integer getNumberOfSuccess() {
        return numberOfSuccess;
    }

    public void setNumberOfSuccess(Integer numberOfSuccess) {
        this.numberOfSuccess = numberOfSuccess;
    }

    public Integer getNumberOfFailure() {
        return numberOfFailure;
    }

    public void setNumberOfFailure(Integer numberOfFailure) {
        this.numberOfFailure = numberOfFailure;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public List<String> Failures() {
        return failures;
    }

    public void setFailures(List<String> failures) {
        this.failures = failures;
    }
}
