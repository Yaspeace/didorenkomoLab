package ui;

import bank.entity.base.BaseEntity;

import java.util.Collection;
import java.util.Scanner;

public class ConsoleMenu {
    public static <T extends BaseEntity> int getIdFromUser(String title, Collection<T> objects) {
        Separator();
        System.out.println(title);
        Separator();
        for(BaseEntity o : objects) {
            System.out.println(o.toShortString());
        }
        Separator();
        System.out.print("Введите id нужной сущности...");

        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private static void Separator() {
        System.out.println("----------------------");
    }
}
