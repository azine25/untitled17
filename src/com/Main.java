package com;

import javax.xml.transform.SourceLocator;
import java.sql.SQLOutput;
import java.util.Random;

public class Main {
    public static int roundNumber = 0;
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 250, 300};
    public static int[] heroesDamage = {20, 15, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kineti", "Medical"};


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        System.out.println("_______________________");
        roundNumber++;
        System.out.println("\nROUND №" + roundNumber + "\n");
        changeDefence();
        medicToHeal();
        if (bossHealth > 0) {
            bossHits();
        }
        printStatistics();
        heroesHit();
    }

    public static void changeDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {


                    Random random = new Random();
                    int coeff = random.nextInt(10) + 2; //2,3,4,5,6,7,8,9,10,11
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void medicToHeal() {

        for (int i = 0; i < heroesDamage.length; i++) {
            Random random2 = new Random();
            int coeff2 = random2.nextInt(40) + 10;
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] + coeff2;
                System.out.println("Medic to heal: " + heroesAttackType[i]);
                System.out.println("Medical to heal: " + coeff2);
            }break;

        }

    }





            /*
            Добавить 4-го игрока Medic, у которого есть способность лечить после каждого
            раунда на N-ное количество единиц здоровья только одного из членов команды, имеющего здоровье менее 100 единиц.
            Мертвых героев медик оживлять не может, и лечит он до тех пор пока жив сам. Медик не участвует в бою,
            но получает урон от Босса
            ДЗ на сообразительность:
            Добавить n-го игрока, Golem, который имеет увеличенную жизнь но слабый удар.
            Может принимать на себя 1/5 часть урона исходящего от босса по другим игрокам.
            Добавить n-го игрока, Lucky, имеет шанс уклонения от ударов босса.
            Добавить n-го игрока, Berserk, блокирует часть удара босса по себе и прибавляет заблокированный
            урон к своему урону и возвращает его боссу
            Добавить n-го игрока, Thor, удар по боссу имеет шанс оглушить босса на 1 раунд,
            вследствие чего босс пропускает 1 раунд и не наносит урон героям.
             */
}




