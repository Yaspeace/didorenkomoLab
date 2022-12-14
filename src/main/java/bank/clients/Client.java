package bank.clients;

/**
 * Клиент взаимодействия с внешними источниками данных
 */
public interface Client {
    /**
     * Сохранить/отправить данные в строковом представдении
     * @param destination Путь отправки/сохранения
     * @param toSend Данные для сохранения
     * @throws Exception Ошибка сохранения/отправки
     */
    void send(String destination, String toSend) throws Exception;

    /**
     * Получение данных в строковом представлении из внешнего источника
     * @param destination Путь к данным
     * @return Полученные данные
     * @throws Exception Ошибка получения данных
     */
    String get(String destination) throws Exception;
}
