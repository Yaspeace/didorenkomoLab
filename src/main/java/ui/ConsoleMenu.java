package ui;

import bank.entity.base.BaseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleMenu {
    public static <T extends BaseEntity> T getIdFromUser(String title, Collection<T> objects) throws Exception {
        if(objects.size() < 1) {
            throw new Exception("Нет объектов для отображения");
        }
        Separator();
        System.out.println(title);
        Separator();
        for(BaseEntity o : objects) {
            System.out.println(o.toShortString());
        }
        Separator();
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

    private static void Separator() {
        System.out.println("----------------------");
    }
}
