package rfi.monpaniervert.exceptions;


import org.springframework.core.NestedRuntimeException;

/**
 * Exception thrown when an error occurred when interacting with a S3 bucket.
 */
public class S3ClientException extends NestedRuntimeException {
    private static final long serialVersionUID = -5462707347089495419L;

    /**
     * Constructor of {@link S3ClientException}.
     *
     * @param message the exception message.
     * @param cause the exception cause.
     */
    public S3ClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
