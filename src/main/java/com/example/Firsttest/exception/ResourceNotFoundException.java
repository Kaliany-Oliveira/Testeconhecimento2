package com.example.Firsttest.exception;

public class ResourceNotFoundException extends RuntimeException{

        public ResourceNotFoundException(String message) {
            super(message);  // Chama o construtor da classe pai (RuntimeException)
        }
}

