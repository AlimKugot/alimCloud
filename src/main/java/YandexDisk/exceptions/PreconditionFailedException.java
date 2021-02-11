package YandexDisk.exceptions;

public class PreconditionFailedException extends WebdavException {
    public PreconditionFailedException(String message) {
        super(message);
    }
}
