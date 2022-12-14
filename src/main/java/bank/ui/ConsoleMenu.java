package bank.ui;

import bank.entity.base.BaseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleMenu {
    public static <T extends BaseEntity> T getIdFromUser(String title, Collection<T> objects) throws RuntimeException {
        if(objects.size() < 1) {
            throw new RuntimeException("Нет объектов для отображения");
        }
        separator();
        System.out.println(title);
        separator();
        for(BaseEntity o : objects) {
            System.out.println(o.toShortString());
        }
        separator();
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

    public static void separator() {
        System.out.println("----------------------");
    }

    public static void title(String title) {
        System.out.println("----------" + title + "----------");
    }
}
