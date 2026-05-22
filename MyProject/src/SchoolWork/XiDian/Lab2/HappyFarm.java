package SchoolWork.Xidian.Lab2;

import java.util.ArrayList;
import java.util.Scanner;

public class HappyFarm {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
       Farm happyfarm=new Farm();
        while (true) {
            System.out.println("\n===== 开心农场管理系统 =====");
            System.out.println("1. 种植农作物（小麦/玉米）");
            System.out.println("2. 养殖动物（鸡/牛 可命名）");
            System.out.println("3. 按名称查找");
            System.out.println("4.根据类型输出农场对象");
            System.out.println("5.输出指定编号之前的农场对象");
            System.out.println("6. 照料指定对象");
            System.out.println("7. 收获/移除农作物");
            System.out.println("8. 显示所有");
            System.out.println("9. 查看余额");
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
                        if(num==1){
                            happyfarm.addCrop(new Wheat());
                        }

                        else if(num==2){
                            happyfarm.addCrop(new Corn());
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
                        if(num==1){
                            happyfarm.addAnimal(new Chicken(name));
                        }

                        else if(num==2) {
                            happyfarm.addAnimal(new Cow(name));
                        }
                        else{
                            System.out.println("输入错误！已回到菜单");
                        }
                        break;
                    }

                    case 3:{
                        System.out.println("请输入要查找的名称：");
                        String targetName=sc.next();
                        FarmStuff stuff;
                        stuff=happyfarm.findbyName(targetName);
                        if(stuff!=null){
                            stuff.showInfo();
                        }
                        else{
                            System.out.println("未找到");
                        }
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
                        System.out.println("请输入要照料的对象编号");
                        int targetId=sc.nextInt();
                        happyfarm.careTargetId(targetId);
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
    private int count = 0;  //记录已经录入对象数
    private ArrayList<FarmStuff> farmstuffs = new ArrayList<>();

    public int showMoney() {
        return money;
    }

    public boolean addCrop(Wheat wheat) {
        if (count >= MAX_SIZE) {
            System.out.println("农场已满，购买失败");
            return false;
        }
        if (wheat.getBuyPrice() >= money) {
            System.out.println("余额不足，购买失败");
            System.out.println("当前余额：" + showMoney() + "  购买小麦所需余额：" + wheat.getBuyPrice());
            return false;
        }
        farmstuffs.add(wheat);
        money = money - wheat.getBuyPrice();
        System.out.println("购买成功");
        count++;
        return true;
    }

    public boolean addCrop(Corn corn) {
        if (count >= MAX_SIZE) {
            System.out.println("农场已满，购买失败");
            return false;
        }
        if (corn.getBuyPrice() >= money) {
            System.out.println("余额不足，购买失败");
            System.out.println("当前余额：" + showMoney() + "  购买玉米所需余额：" + corn.getBuyPrice());
            return false;
        }
        farmstuffs.add(corn);
        money = money - corn.getBuyPrice();
        System.out.println("购买成功");
        count++;
        return true;
    }

    public boolean addAnimal(Chicken chicken) {
        if (count >= MAX_SIZE) {
            System.out.println("农场已满，购买失败");
            return false;
        }
        if (chicken.getBuyPrice() >= money) {
            System.out.println("余额不足，购买失败");
            System.out.println("当前余额：" + showMoney() + "  购买小鸡所需余额：" + chicken.getBuyPrice());
            return false;
        }
        farmstuffs.add(chicken);
        money = money - chicken.getBuyPrice();
        System.out.println("购买成功");
        count++;
        return true;
    }

    public boolean addAnimal(Cow cow) {
        if (count >= MAX_SIZE) {
            System.out.println("农场已满，购买失败");
            return false;
        }

        if (cow.getBuyPrice() >= money) {
            System.out.println("余额不足，购买失败");
            System.out.println("当前余额：" + showMoney() + "  购买牛所需余额：" + cow.getBuyPrice());
            return false;
        }
        farmstuffs.add(cow);
        money = money - cow.getBuyPrice();
        System.out.println("购买成功");
        count++;
        return true;
    }

    public FarmStuff findbyName(String targetName) {
        for (FarmStuff stuff : farmstuffs) {
            if (stuff.getName().equals(targetName)) {
                return stuff;
            }
        }
        return null;
    }

    public FarmStuff findbyId(int targetId) {
        for (FarmStuff stuff : farmstuffs) {
            if (stuff.getId() == targetId) {
                return stuff;
            }
        }
        return null;
    }

    public FarmStuff findbyType(String targetType) {
        for (FarmStuff stuff : farmstuffs) {
            if (stuff.getType().equals(targetType)) {
                return stuff;
            }
        }
        return null;
    }

    public boolean showTargettype(String targetType) {
        boolean sign = false;
        for (FarmStuff stuff : farmstuffs) {
            if (stuff.getType().equals(targetType)) {
                stuff.showInfo();
                sign = true;
            }
        }
        return sign;
    }

    public boolean showBeforeTargetId(int targetId) {
        boolean sign = false;
        for (FarmStuff stuff : farmstuffs) {
            if (stuff.getId()< targetId) {
                stuff.showInfo();
                sign = true;
            }
        }
        return sign;
    }

    public void careTargetId(int targetId){
        for (FarmStuff stuff : farmstuffs) {
            if (stuff.getId() == targetId) {
                stuff.care();
                return;
            }
        }
    }

    public boolean sell(int targetId){
        for (int i = 0; i < farmstuffs.size(); i++) {
            FarmStuff stuff = farmstuffs.get(i);
            if (stuff.getId() == targetId) {

                if (stuff instanceof Wheat) {
                    money += ((Wheat) stuff).getSellPrice();
                }
                else if (stuff instanceof Corn) {
                    money += ((Corn) stuff).getSellPrice();
                }
                else if (stuff instanceof Cow) {
                    money += ((Cow) stuff).getSellPrice();
                }
                else if (stuff instanceof Chicken) {
                    money += ((Chicken) stuff).getSellPrice();
                }

                farmstuffs.remove(i);
                count--;
                return true;

            }
        }
        return false;
    }

    public boolean remove(int targetId) {
        for (int i = 0; i < farmstuffs.size(); i++) {
            if (farmstuffs.get(i).getId() == targetId) {
                farmstuffs.remove(i);
                count--;
                return true;
            }
        }
        return false;
    }

    public void showAll(){
        if (farmstuffs.isEmpty()) {
            System.out.println("农场为空");
            return;
        }
        System.out.println("\n=== 农场所有对象 ===");
        for (FarmStuff stuff : farmstuffs) {
            stuff.showInfo();
        }
    }

}
class FarmStuff{
    private static int count=1;
    private String name;
    private String type;
    private int id;

    public void care(){
    }

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

    public int getBuyPrice(){
        return buyPrice;
    }

    public int getSellPrice(){
        return sellPrice;
    }
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

    public int getBuyPrice(){
        return buyPrice;
    }

    public int getSellPrice(){
        return sellPrice;
    }
}

class Chicken extends FarmStuff{
    static final int buyPrice=10;
    static final int sellPrice=30;

    Chicken(String name){
        super(name,"鸡");
    }

    public void care(){
        System.out.println("抚摸了小鸡————咯咯咯~");
    }

    public int getBuyPrice(){
        return buyPrice;
    }

    public int getSellPrice(){
        return sellPrice;
    }
}

class Cow extends FarmStuff{
    static final int buyPrice=100;
    static final int sellPrice=500;

    Cow(String name){
        super(name,"牛");
    }

    public void care(){
        System.out.println("抚摸了牛———哞~");
    }

    public int getBuyPrice(){
        return buyPrice;
    }

    public int getSellPrice(){
        return sellPrice;
    }
}