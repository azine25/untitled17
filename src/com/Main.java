package com;

import javax.xml.transform.SourceLocator;
import java.sql.SQLOutput;
import java.util.Random;

public class Main {
    public static int roundNumber = 0;
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 250, 300, 500, 240, 260, 300};
    public static int[] heroesDamage = {20, 15, 10, 0, 4, 10, 15, 25};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medical", "Golem", "lucky", "Berserk", "Thor"};
    public static int golemHit = bossDamage / 5;
    public static int goler = bossDamage - golemHit;


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
        if (bossHealth > 0 && thorHits()) {
            bossHits();
            changeDefence();
            heroesHit();
            System.out.println("   ");
            medicToHeal();
            System.out.println("   ");
            golemHits();
            System.out.println("   ");
        }
        luckyHits();
        System.out.println("   ");
        berserkHits();

        System.out.println("   ");
        printStatistics();
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
                    heroesHealth[i] = heroesHealth[i] - goler;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {

                    Random random = new Random();
                    int coeff = random.nextInt(10) + 5; //2,3,4,5,6,7,8,9,10,11
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] <= 0) {
                        bossHealth = 0;
                    } else {
                     bossHealth= bossHealth-heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void medicToHeal() {
        for (int i = 0; i < heroesDamage.length; i++) {
            Random random2 = new Random();
            int coeff2 = random2.nextInt(30) + 10;

            if (heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                heroesHealth[i] = heroesHealth[i] + coeff2;
                System.out.println("Medic to heal: " + heroesAttackType[i]);
                System.out.println("Healing: " + coeff2 + "HP");
            }
            break;
        }
    }

    public static void golemHits() {
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[4] > 0) {
                heroesHealth[4] = heroesHealth[4] - golemHit;
            } else if (heroesHealth[4] <= 0) {
                heroesHealth[4] = 0;
            }

        }
        System.out.println("Golem took the demege: " + golemHit);
    }

    public static void luckyHits() {
        Random random = new Random();
        boolean lucky = random.nextBoolean();
        if (!lucky) {
            heroesHealth[5] = heroesHealth[5] + goler;
            System.out.println("Lucky dodged a blow");
        }

    }

    public static void berserkHits() {
        for (int i = 0; i < 1; i++) {
            Random random = new Random();
            int blockHits = random.nextInt(30) + 10;
            int tookDemage = bossDamage - blockHits;
            int hitsOn = blockHits + heroesDamage[6];


            if (heroesHealth[6] > 0) {
                heroesHealth[6] = heroesHealth[6] + tookDemage;
                bossHealth = bossHealth - hitsOn;
                heroesDamage[6] = heroesDamage[6] + blockHits;


            }
            System.out.println("Berserk blocks the hit on:" + blockHits + "\nHits on: " + hitsOn);
        }
    }

    public static boolean thorHits() {
        Random random = new Random();
        boolean thor = random.nextBoolean();
        for (int j = 0; j < 1; j++) {
               if (thor) {
                   bossDamage = 0;
                   System.out.println("Boss hits");
                   break;
               } else if (!thor) {
                   bossDamage = goler;
                   System.out.println("Thor stunned the boss");
               }
        }
        return thor;
    }

}
