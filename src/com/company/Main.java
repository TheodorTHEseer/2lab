package com.company;

import Events.Research;
import cretures.pac.Enemy;
import cretures.pac.Hero;
import items.pac.Weaponry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    //Метод мейн
    public static void main(String[] args) throws IOException, InterruptedException {
        //Для наименований
        String [] []Names = {{"Мол", "Гоур","Фре","Них","Мир","Сон","Вор","Моор","Стэв","Мне"},
                {"ов", "гар","'Даал","кац","'Та","лав","'Муур","ки","сан","эст"},
                {" Беспощадный", " Кровавый", " Безумный", " Еретик", " Палач"," Сверепый"," Смертный",
                        " Последний палач Абаддона", " Проклятый", " Проводник"}};
        String [] []Tags = {{"Стальной","Железный","Медный","Бронзовый"},
                {"одноручный меч","топор","кинжал","полуторный меч"}};
        //Для основного кода
        //Список врагов
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        Random random = new Random();
        for (int count=0; count<5;count++) {
            //Для имён
            int firstPt = random.nextInt(10);
            int secondPt = random.nextInt(10);
            int nicknameId = random.nextInt(10);
            //Создание имени
            String name = Names[0][firstPt] + Names[1][secondPt] + Names[2][nicknameId];
            //Добавление нового в список
            enemies.add(new Enemy(name, 100, (float) 1, 1,  random.nextFloat(),  (float) 0.2,   (float) random.nextInt(100)));
        }
        //Список оружия для подбора/покупки
        ArrayList<Weaponry> weapons = new ArrayList<Weaponry>();
        for (int count = 0;count <5; count++){
            //Для урона
            int firstPt = random.nextInt(4);
            int secondPt = random.nextInt(4);
            String tag = Tags[0][firstPt]+" "+Tags[1][secondPt];
            //Добавление нового в список
            weapons.add(new Weaponry(tag, random.nextInt(5+1)+count, random.nextFloat()));
        }
        //Список оружия для врага
        ArrayList<Weaponry> enemyWeapons = new ArrayList<Weaponry>();
        for (int count = 1;count <6; count++) {
            //Для урона
            int firstPt = random.nextInt(4);
            int secondPt = random.nextInt(4);
            String tag = Tags[0][firstPt] + " " + Tags[1][secondPt];
            //Добавление нового в список
            enemyWeapons.add(new Weaponry(tag, count, random.nextInt(5 - 1) + 1));
        }
        //Главный герой
        Hero player = new Hero("Игрок", 100, 1000, (float) 1, 1000, (float) 7);
        Weaponry myWeapon = new Weaponry("Моё оружие", 1,1);
        player.setDamage(GameLogic.calcDPS(myWeapon.getWeaponDmg(),myWeapon.getWeaponEffectBoost(),player.getDamage()));
        //Показатель прохождения последнего врага
        //Показатель готовности человека выйти из тренировочной арены
        boolean exitStatus = false;
        //Начало главного цикла
        int stepid = 0;
        int key;
        while (exitStatus == false||player.getHp()>0) {
            //Начало каждого боя
            //Выбор шага
            player.setLvl(GameLogic.getLvl(player.getExp()));
            Scanner in = new Scanner(System.in);
            System.out.println("Введите то, что хотите сделать\n" +
                    "---------------------");
            System.out.println("[1 - войти на тренировочное поле]\n" +
                    "[2 - выйти из игры]\n" +
                    "[3 - войти в магазин]\n" +
                    "[4 - войти в режим экспедиции]");
            key = in.nextInt();
            //Следующий этап
            if (key == 1) {
                boolean prize = false;
                stepid++;
                int waitManager = 0;
                while (prize != true && waitManager != 1) {
                    if (waitManager == 0) {
                        waitManager = 1;
                        Enemy localEnemy = enemies.get(stepid);
                        Weaponry localWeapon = enemyWeapons.get(stepid);
                        localEnemy.setDamage(GameLogic.calcDPS(localWeapon.getWeaponDmg(), localWeapon.getWeaponEffectBoost(), localEnemy.getRageEffect(), localEnemy.getRaceEffect(), localEnemy.getDamage()));
                        System.out.println("Ваш враг - ужасное существо, производящие ужас: " + localEnemy.getName() +
                                " В руках у него: " + localWeapon.getName() + " он может ударить вас так сильно, что нанесёт "
                                + localEnemy.getDamage()
                                + " урона.");

                        if (localEnemy.getDexteritySkill() > player.getDexteritySkill()) {
                            System.out.println("Он сразу бросается на Вас.");
                            player.setHp(player.getHp() - localEnemy.getDamage());
                            if (player.getHp() > 0) {
                                System.out.println("Но вы выживаете!");
                                prize = false;
                            } else
                                prize = true;
                        }

                        if (localEnemy.getDexteritySkill() <= player.getDexteritySkill()) {
                            System.out.printf("Атаковать? \n" +
                                    "[Вы получите 1/10 урона врага]\n" +
                                    "[Атака - 1]\n" +
                                    "[Защита - 2]");
                            int chose;
                            chose = in.nextInt();
                            if (chose == 1) {
                                localEnemy.setHp(localEnemy.getHp() - player.getDamage());
                                player.setHp(player.getHp()-localEnemy.getDamage() / 10);
                                if (player.getHp() <= 0)
                                    System.out.println("Вы погибли.");
                                prize = true;
                                waitManager = 1;
                                if (player.getHp() > 0 && localEnemy.getHp() > 0)
                                    prize = false;
                            }
                            if (chose == 2) {
                                System.out.println("Вы заняли оборонительную позицию. \n" +
                                        "Принесёт ли это Вам победу?");
                                player.setHp(player.getHp() - localEnemy.getDamage() / 5);
                                localEnemy.setHp(localEnemy.getHp() - player.getDamage() / 10);
                                if (player.getHp() <= 0) {
                                    prize = true;
                                    waitManager = 1;
                                    System.out.println("Вы погибли. Вам не хватило " + player.getHp()*(-1) + "Hp");
                                }
                                if (player.getHp() > 0 && localEnemy.getHp() > 0){
                                    System.out.printf("Да. ");
                                    prize = false;}

                            }
                        }
                        if (player.getHp()>0)
                        System.out.println("У вас осталось " + player.getHp() + " Hp");
                        if (localEnemy.getHp() <= 0 && player.getHp()>0) {
                            prize = true;
                            waitManager = 1;
                            System.out.println("Вы победили");
                            player.setExp(player.getExp() + GameLogic.getExp(localEnemy.getLvl()));
                            player.setMoney(player.getMoney() + GameLogic.getMoney(localEnemy.getLvl()));
                        }
                    }
                }
            }
            if (player.getHp()>=0)
                exitStatus = true;
            //Выход
            if (key == 2){
                exitStatus = true;
                player.setHp(0);
            }
            //Магазин
            if (key == 3){
                for (Weaponry Weaponry:weapons
                ) {
                    System.out.println(" Оружие: " + Weaponry.getName()+", dmg:"+
                                Weaponry.getWeaponDmg()+", необходимый уровень: "+
                                Weaponry.getRequiredLvl()+", цена:" + Weaponry.getCost());

                }
                System.out.println("Вы имеет: "+player.getMoney() +" золотых"+ " и " + player.getLvl()+" уровень.");
                System.out.println("Что хотите приобрести?\n" +
                        "[Первый инедкс = 0]");
                int marketId = in.nextInt();
                //Условие продажи
                    if (marketId < 5 && weapons.get(marketId).getRequiredLvl() <= player.getLvl() && weapons.get(marketId).getCost() <= player.getMoney()) {
                        //Если подошло
                        System.out.println("Вы выбрали: " + weapons.get(marketId).getName());
                        myWeapon.setName(myWeapon.getName() + ": " + weapons.get(marketId).getName());
                        myWeapon.setWeaponDmg(weapons.get(marketId).getWeaponDmg());
                        myWeapon.setAttackTime(weapons.get(marketId).getAttackTime());
                        player.setMoney(player.getMoney() - weapons.get(marketId).getCost());
                    }
                    //Если неправильный id
                    if (marketId >= 5)
                        System.out.println("Такого оружия не существует");
                    //Если оружие не подходит
                    if (marketId < 5 && weapons.get(marketId).getRequiredLvl() >= player.getLvl() || weapons.get(marketId).getCost() >= player.getMoney())
                        System.out.println("Оружие Вам не подходит");

            }
            if (key == 4){
                int moneySpends = 20;
                System.out.printf("Введите сумму, которую хотите потратить на исследование");
                moneySpends = in.nextInt();
                ExecutorService service = Executors.newCachedThreadPool();
                service.submit(new Research());
                player.setMoney(Research.getBenefits(moneySpends));
            }
        }
    }
    //Логика игры
    public static class GameLogic {
        public static float mdps;
        public static float dps;
        public static  float specialEffect;
        public static int exp;
        public static int money;
        //Методы
        //Метод вычисления дпс
        public static float calcDPS(float weaponDmg, float weaponEffectBoost, float damage) {
            dps = damage * weaponDmg * weaponEffectBoost;
            return dps;
        }
        //Перегрузка для Enemy
        public static float calcDPS(float weaponDmg, float weaponEffectBoost,
                                    float rageEffect,float raceEffect, float damage) {
            specialEffect = raceEffect+rageEffect;
            dps = damage + weaponDmg * weaponEffectBoost + specialEffect;
            return dps;
        }
        public float calcMDPS(int magicDamage, float damage){
            return mdps = (float) magicDamage+damage;
        }
        public static void displayGetLoot(String name){
            System.out.println("Вы получли: " + name);
        }
        //Получение экспы
        public static int getExp(int enemyLvl){
            if (enemyLvl <2)
            return exp = enemyLvl*2000;
            else
                return exp = enemyLvl*250;
        }
        public static int getLvl(int exp){
            return exp/1000;
        }
        //Получение денег
        public static int getMoney(int enemyLvl){
            return money = enemyLvl*500;
        }
        //Вычисление магического дпс
        public static float calcMdps(int magicDamage, int lvl){
            if (lvl <10)
                mdps = (float) magicDamage;
            if (lvl >10 && lvl <20)
                mdps = (float) magicDamage * (float) lvl / (float) 3;
            if (lvl >20)
                mdps = (float) magicDamage * (float) lvl;
            return mdps;
        }
    }
}