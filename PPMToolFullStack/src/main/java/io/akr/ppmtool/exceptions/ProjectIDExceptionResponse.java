package io.akr.ppmtool.exceptions;

public class ProjectIDExceptionResponse {

    private String projectidentifier;

    public ProjectIDExceptionResponse(String projectidentifier) {
        this.projectidentifier = projectidentifier;
    }

    public String getProjectidentifier() {
        return projectidentifier;
    }

    public void setProjectidentifier(String projectidentifier) {
        this.projectidentifier = projectidentifier;
    }
}
