package bank.ui;

import bank.entity.base.BaseEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

/**
 * Класс для взаимодействия с пользователем через консоль
 */
public class ConsoleMenu {
    /**
     * Получить один объект из списка от пользователя
     * @param title Заголовок вывода
     * @param objects Объекты, из которых будет выбирать пользователь
     * @return Выбранный объект из списка
     * @param <T> Тип объектов
     * @throws RuntimeException Ошибка ввода/вывода
     */
    public static <T extends BaseEntity> T getObjectFromUser(String title, Collection<T> objects) throws RuntimeException {
        if(objects.size() < 1) {
            throw new RuntimeException("Нет объектов для отображения");
        }
        System.out.println();
        showSeparator();
        System.out.println(title);
        showSeparator();
        for(BaseEntity o : objects) {
            System.out.println(o.toShortString());
        }
        showSeparator();
        System.out.print("Введите id нужной сущности...");

        Scanner sc = new Scanner(System.in);
        int id1 = sc.nextInt();
        Optional<T> obj = objects.stream().filter(o -> o.id == id1).findFirst();
        while(obj.isEmpty()) {
            System.out.print("Пожалуйста, введите id относящийся к предложенным сущностям...");
            int id2 = sc.nextInt();
            obj = objects.stream().filter(o -> o.id == id2).findFirst();
        }
        return obj.get();
    }

    /**
     * Вывод коллекции с заголовком
     * @param title Заголовок
     * @param objects Объекты
     * @param <T> Тип объектов
     */
    public static <T> void printCollection(String title, Collection<T> objects) {
        showTitle(title);
        for(T o : objects) System.out.println(o.toString());
        showSeparator();
    }

    public static <T extends BaseEntity> void printCollectionBeauty(String title, Collection<T> objects) {
        showTitle(title);
        for(T o : objects) System.out.println(o.toShortString());
        showSeparator();
    }

    /**
     * Вывести разделитель
     */
    public static void showSeparator() {
        System.out.println("----------------------");
    }

    /**
     * Вывести разделитель с заголовком
     * @param title Заголовок
     */
    public static void showTitle(String title) {
        System.out.println("----------" + title + "----------");
    }

    /**
     * Показывает варианты пользователю в виде "[индекс + 1] - [текст варианта]"
     * @param title Заголовок
     * @param variants Тексты вариантов
     * @return индекс варианта, который был выбран
     */
    public static int getVariant(String title, String[] variants) {
        System.out.println();
        showTitle(title);
        for(int i = 0; i < variants.length; i++) {
            System.out.println((i + 1) + " - " + variants[i]);
        }
        showSeparator();
        Scanner sc = new Scanner(System.in);
        int inputted = sc.nextInt();
        while(inputted < 1 || inputted > variants.length) {
            System.out.println("Пожалуйста, введите корректное значение");
            inputted = sc.nextInt();
        }
        return inputted - 1;
    }
}
