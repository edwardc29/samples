package com.carrion.edward.domain.exception;

public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
