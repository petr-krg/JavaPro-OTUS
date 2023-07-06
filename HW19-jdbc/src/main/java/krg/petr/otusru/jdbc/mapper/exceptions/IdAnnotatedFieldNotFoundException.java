package krg.petr.otusru.jdbc.mapper.exceptions;

public class IdAnnotatedFieldNotFoundException extends RuntimeException {

    public IdAnnotatedFieldNotFoundException(Class<?> clazz) {
        super("Not found annotated @ID field in class " + clazz.getName());
    }
}
