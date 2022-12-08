package helpers;

public interface Logger {
    /**
     * Логировать текст
     * @param message Текст для логирования
     */
    void log(String message);

    /**
     * Логировать сообщение об ошибке
     * @param errorMessage Сообщение
     */
    void logError(String errorMessage);
}
