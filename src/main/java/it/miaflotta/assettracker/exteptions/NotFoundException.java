package it.miaflotta.assettracker.exteptions;

public class NotFoundException extends Exception {
    private String message;
    private int code;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
        this.code = -1;
    }

    public NotFoundException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
