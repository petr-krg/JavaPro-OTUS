package krg.petr.otusru.jdbc.mapper.exceptions;

public class DataTemplateException extends RuntimeException{
    public DataTemplateException(Exception exception) {
        super(exception);
    }
}
