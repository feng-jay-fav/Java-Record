package SchoolWork.Xidian.Lab3;

import java.util.ArrayList;
import java.util.Scanner;

public class HappyFarm {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Farm happyfarm=new Farm();

        happyfarm.initFarm(5);

        while (true) {
            System.out.println("\n===== 开心农场管理系统 =====");
            System.out.println("1. 种植农作物（小麦/玉米）");
            System.out.println("2. 养殖动物（鸡/牛 可命名）");
            System.out.println("3. 按名称查找");
            System.out.println("4.根据类型输出农场对象");
            System.out.println("5.输出指定编号之前的农场对象");
            System.out.println("6. 照料指定对象（按行+位置）");
            System.out.println("7. 收获/移除农作物");
            System.out.println("8. 显示所有");
            System.out.println("9. 查看余额");
            System.out.println("10. 根据位置删除对象");
            System.out.println("0. 退出");
            System.out.print("请选择：");
            int choice;

            while (!sc.hasNextInt()) {
                System.out.println("输入错误！请输入正确的数字！");
                sc.next();
            }
            choice = sc.nextInt();

            if(choice==0){
                break;
            }

            else{
                switch(choice){

                    case 1: {
                        System.out.println("请输入：1、买小麦 2、买玉米");
                        int num;
                        while (!sc.hasNextInt()) {
                            System.out.println("输入错误！请输入正确的数字！");
                            sc.next();
                        }
                        num = sc.nextInt();

                        System.out.print("请输入要添加到第几行：");
                        int row = sc.nextInt();

                        if(num==1){
                            happyfarm.addCrop(new Wheat(), row);
                        }
                        else if(num==2){
                            happyfarm.addCrop(new Corn(), row);
                        }
                        else{
                            System.out.println("输入错误！已回到菜单");
                        }
                        break;
                    }

                    case 2:{
                        System.out.println("请输入：1、买鸡 2、买牛");
                        int num;
                        while (!sc.hasNextInt()) {
                            System.out.println("输入错误！请输入正确的数字！");
                            sc.next();
                        }
                        num = sc.nextInt();
                        System.out.println("请给动物命名");
                        String name=sc.next();

                        System.out.print("请输入要添加到第几行：");
                        int row = sc.nextInt();

                        if(num==1){
                            happyfarm.addAnimal(new Chicken(name), row);
                        }
                        else if(num==2) {
                            happyfarm.addAnimal(new Cow(name), row);
                        }
                        else{
                            System.out.println("输入错误！已回到菜单");
                        }
                        break;
                    }

                    case 3:{
                        System.out.println("请输入要查找的名称：");
                        String targetName=sc.next();
                        happyfarm.findByName(targetName);
                        break;
                    }

                    case 4:{
                        System.out.println("请输入要查找的类型：");
                        String targetType=sc.next();
                        if(!happyfarm.showTargettype(targetType)){
                            System.out.println("没有当前类型的对象");
                        }
                        break;
                    }

                    case 5:{
                        System.out.println("请输入要显示编号多少之前的对象（整数）：");
                        int targetId =sc.nextInt();
                        if(!happyfarm.showBeforeTargetId(targetId)){
                            System.out.println("没有当前编号的对象");
                        }
                        break;
                    }

                    case 6:{
                        System.out.print("请输入行号：");
                        int row = sc.nextInt();
                        System.out.print("请输入位置编号：");
                        int col = sc.nextInt();
                        happyfarm.careByPosition(row, col);
                        break;
                    }

                    case 7:{
                        System.out.println("请选择：1.收获售卖  2.删除");
                        int choice1;
                        while (!sc.hasNextInt()) {
                            System.out.println("输入错误！请输入正确的数字！");
                            sc.next();
                        }
                        choice1 = sc.nextInt();
                        if(choice1==1){
                            System.out.println("请输入要售卖的对象的编号：");
                            int targetId=sc.nextInt();
                            if(happyfarm.sell(targetId)){
                                System.out.println("售卖成功！"+"当前余额"+happyfarm.showMoney());
                            }
                            else{
                                System.out.println("售卖失败！");
                            }
                        }
                        else if(choice1==2){
                            System.out.println("请输入要删除的对象的编号：");
                            int targetId=sc.nextInt();
                            if(happyfarm.remove(targetId)){
                                System.out.println("删除成功！");
                            }
                            else{
                                System.out.println("删除失败！");
                            }
                        }
                        else{
                            System.out.println("输入错误！已回到菜单");
                        }
                        break;
                    }

                    case 8: {
                        happyfarm.showAll();
                        break;
                    }

                    case 9: {
                        System.out.println("余额剩余"+happyfarm.showMoney()+"元");
                        break;
                    }

                    case 10:{
                        System.out.print("请输入要删除的行号：");
                        int row = sc.nextInt();
                        System.out.print("请输入位置编号：");
                        int col = sc.nextInt();
                        if(happyfarm.removeByPosition(row, col)){
                            System.out.println("删除成功！");
                        }else{
                            System.out.println("删除失败！");
                        }
                        break;
                    }

                    default: {
                        System.out.println("输入选项有误，请重新选择！");
                        break;
                    }
                }
            }
        }
        sc.close();
    }
}

class Farm {
    public static final int MAX_SIZE = 100;
    private int money = 1000;

    private ArrayList<ArrayList<FarmStuff>> farmstuffs = new ArrayList<>();

    public void initFarm(int row) {
        for (int i = 0; i < row; i++) {
            farmstuffs.add(new ArrayList<>());
        }
        System.out.println("农场共 " + row + " 行");
    }

    public int showMoney() {
        return money;
    }

    public boolean addCrop(Wheat wheat, int row) {
        if (row < 0 || row >= farmstuffs.size()) {
            System.out.println("行号非法！");
            return false;
        }
        if (farmstuffs.get(row).size() >= MAX_SIZE) {
            System.out.println("该行已满，购买失败");
            return false;
        }
        if (wheat.getBuyPrice() > money) {
            System.out.println("余额不足");
            return false;
        }
        farmstuffs.get(row).add(wheat);
        money -= wheat.getBuyPrice();
        System.out.println("购买成功，已添加到第 " + row + " 行");
        return true;
    }

    public boolean addCrop(Corn corn, int row) {
        if (row < 0 || row >= farmstuffs.size()) {
            System.out.println("行号非法！");
            return false;
        }
        if (farmstuffs.get(row).size() >= MAX_SIZE) {
            System.out.println("该行已满，购买失败");
            return false;
        }
        if (corn.getBuyPrice() > money) {
            System.out.println("余额不足");
            return false;
        }
        farmstuffs.get(row).add(corn);
        money -= corn.getBuyPrice();
        System.out.println("购买成功，已添加到第 " + row + " 行");
        return true;
    }

    public boolean addAnimal(Chicken chicken, int row) {
        if (row < 0 || row >= farmstuffs.size()) {
            System.out.println("行号非法！");
            return false;
        }
        if (farmstuffs.get(row).size() >= MAX_SIZE) {
            System.out.println("该行已满，购买失败");
            return false;
        }
        if (chicken.getBuyPrice() > money) {
            System.out.println("余额不足");
            return false;
        }
        farmstuffs.get(row).add(chicken);
        money -= chicken.getBuyPrice();
        System.out.println("购买成功，已添加到第 " + row + " 行");
        return true;
    }

    public boolean addAnimal(Cow cow, int row) {
        if (row < 0 || row >= farmstuffs.size()) {
            System.out.println("行号非法！");
            return false;
        }
        if (farmstuffs.get(row).size() >= MAX_SIZE) {
            System.out.println("该行已满，购买失败");
            return false;
        }
        if (cow.getBuyPrice() > money) {
            System.out.println("余额不足");
            return false;
        }
        farmstuffs.get(row).add(cow);
        money -= cow.getBuyPrice();
        System.out.println("购买成功，已添加到第 " + row + " 行");
        return true;
    }

    public void findByName(String name) {
        boolean found = false;
        for (int i = 0; i < farmstuffs.size(); i++) {
            for (int j = 0; j < farmstuffs.get(i).size(); j++) {
                FarmStuff s = farmstuffs.get(i).get(j);
                if (s.getName().equals(name)) {
                    System.out.println("找到对象：");
                    s.showInfo();
                    System.out.println("位置：第 " + i + " 行，第 " + j + " 个");
                    found = true;
                }
            }
        }
        if (!found) System.out.println("未找到");
    }

    public boolean showTargettype(String targetType) {
        boolean sign = false;
        for (ArrayList<FarmStuff> row : farmstuffs) {
            for (FarmStuff stuff : row) {
                if (stuff.getType().equals(targetType)) {
                    stuff.showInfo();
                    sign = true;
                }
            }
        }
        return sign;
    }

    public boolean showBeforeTargetId(int targetId) {
        boolean sign = false;
        for (ArrayList<FarmStuff> row : farmstuffs) {
            for (FarmStuff stuff : row) {
                if (stuff.getId() < targetId) {
                    stuff.showInfo();
                    sign = true;
                }
            }
        }
        return sign;
    }

    public void careByPosition(int row, int col) {
        if (row < 0 || row >= farmstuffs.size()) {
            System.out.println("行号错误");
            return;
        }
        if (col < 0 || col >= farmstuffs.get(row).size()) {
            System.out.println("位置错误");
            return;
        }
        farmstuffs.get(row).get(col).care();
    }

    public boolean removeByPosition(int row, int col) {
        if (row < 0 || row >= farmstuffs.size()) return false;
        if (col < 0 || col >= farmstuffs.get(row).size()) return false;
        farmstuffs.get(row).remove(col);
        return true;
    }

    public boolean sell(int targetId) {
        for (ArrayList<FarmStuff> farmstuff : farmstuffs) {
            for (int j = 0; j < farmstuff.size(); j++) {
                FarmStuff stuff = farmstuff.get(j);
                if (stuff.getId() == targetId) {
                    if (stuff instanceof Wheat) money += ((Wheat) stuff).getSellPrice();
                    else if (stuff instanceof Corn) money += ((Corn) stuff).getSellPrice();
                    else if (stuff instanceof Cow) money += ((Cow) stuff).getSellPrice();
                    else if (stuff instanceof Chicken) money += ((Chicken) stuff).getSellPrice();

                    farmstuff.remove(j);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(int targetId) {
        for (ArrayList<FarmStuff> farmstuff : farmstuffs) {
            for (int j = 0; j < farmstuff.size(); j++) {
                if (farmstuff.get(j).getId() == targetId) {
                    farmstuff.remove(j);
                    return true;
                }
            }
        }
        return false;
    }

    public void showAll() {
        int total = 0;
        System.out.println("\n===== 农场所有对象 =====");
        for (int i = 0; i < farmstuffs.size(); i++) {
            System.out.println("\n第 " + i + " 行：");
            for (FarmStuff stuff : farmstuffs.get(i)) {
                stuff.showInfo();
                total++;
            }
        }
        System.out.println("\n对象总数：" + total);
    }

    public void careTargetId(int targetId) {
        for (ArrayList<FarmStuff> row : farmstuffs) {
            for (FarmStuff stuff : row) {
                if (stuff.getId() == targetId) {
                    stuff.care();
                    return;
                }
            }
        }
    }
}

class FarmStuff{
    private static int count=1;
    private String name;
    private String type;
    private int id;

    public void care(){}

    public FarmStuff(String name,String type){
        this.id=count++;
        this.name=name;
        this.type=type;
    }

    public void showInfo(){
        System.out.println("编号:"+id+" "+"类型："+type+" "+name);
    }

    public String getName(){return name;}
    public int getId(){return id;}
    public String getType(){return type;}
}

class Wheat extends FarmStuff{
    private static int wheatcount=1;
    static final int buyPrice=1;
    static final int sellPrice=5;

    Wheat(){
        super("第"+wheatcount+"份小麦","农作物");
        wheatcount++;
    }

    public void care(){
        System.out.println("经过浇水施肥，小麦开始抽穗了");
    }

    public int getBuyPrice(){ return buyPrice; }
    public int getSellPrice(){ return sellPrice; }
}

class Corn extends FarmStuff{
    private static int corncount=1;
    static final int buyPrice=2;
    static final int sellPrice=10;

    Corn(){
        super("第"+corncount+"份玉米","农作物");
        corncount++;
    }

    public void care(){
        System.out.println("经过浇水施肥，玉米开始结棒了");
    }

    public int getBuyPrice(){ return buyPrice; }
    public int getSellPrice(){ return sellPrice; }
}

class Chicken extends FarmStuff{
    static final int buyPrice=10;
    static final int sellPrice=30;

    Chicken(String name){ super(name,"鸡"); }

    public void care(){
        System.out.println("抚摸了小鸡————咯咯咯~");
    }

    public int getBuyPrice(){ return buyPrice; }
    public int getSellPrice(){ return sellPrice; }
}

class Cow extends FarmStuff{
    static final int buyPrice=100;
    static final int sellPrice=500;

    Cow(String name){ super(name,"牛"); }

    public void care(){
        System.out.println("抚摸了牛———哞~");
    }

    public int getBuyPrice(){ return buyPrice; }
    public int getSellPrice(){ return sellPrice; }
}