package Play;

import java.util.ArrayList;
import java.util.Scanner;

// 父类：农场对象
class FarmObject {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String type;

    public FarmObject(String name, String type) {
        this.id = idCounter++;
        this.name = name;
        this.type = type;
    }

    public void care() {
        System.out.print(name + " 被照料：");
    }

    public void showInfo() {
        System.out.println("编号：" + id + "，名称：" + name + "，类型：" + type);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public static void checkType(FarmObject obj) {
        if (obj == null) {
            System.out.println("对象不存在");
            return;
        }
        System.out.print("类型：");
        if (obj instanceof Wheat) System.out.println("小麦");
        else if (obj instanceof Corn) System.out.println("玉米");
        else if (obj instanceof Chicken) System.out.println("鸡");
        else if (obj instanceof Cow) System.out.println("牛");
        else System.out.println("未知");
    }
}

// 小麦子类
class Wheat extends FarmObject {
    private static int count = 0;
    private int buyPrice;
    private int sellPrice;

    public Wheat() {
        super("小麦第" + (++count) + "份", "农作物");
        this.buyPrice = 5;
        this.sellPrice = 12;
    }

    @Override
    public void care() {
        super.care();
        System.out.println("浇水、施肥");
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}

// 玉米子类
class Corn extends FarmObject {
    private static int count = 0;
    private int buyPrice;
    private int sellPrice;

    public Corn() {
        super("玉米第" + (++count) + "份", "农作物");
        this.buyPrice = 6;
        this.sellPrice = 14;
    }

    @Override
    public void care() {
        super.care();
        System.out.println("除草、松土");
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}

// 鸡子类
class Chicken extends FarmObject {
    public Chicken(String name) {
        super(name, "动物");
    }

    @Override
    public void care() {
        super.care();
        System.out.println("喂食、清洁");
    }
}

// 牛子类
class Cow extends FarmObject {
    public Cow(String name) {
        super(name, "动物");
    }

    @Override
    public void care() {
        super.care();
        System.out.println("放牧、喂饲料");
    }
}

// 农场类
class Farm {
    private static final int MAX_CAPACITY = 10;
    private ArrayList<FarmObject> farmObjects;
    private int money;

    public Farm() {
        farmObjects = new ArrayList<>();
        money = 100;
    }

    public boolean addCrop(Wheat wheat) {
        if (farmObjects.size() >= MAX_CAPACITY) {
            System.out.println("农场已满！");
            return false;
        }
        if (money < wheat.getBuyPrice()) {
            System.out.println("余额不足！");
            return false;
        }
        money -= wheat.getBuyPrice();
        farmObjects.add(wheat);
        return true;
    }

    public boolean addCrop(Corn corn) {
        if (farmObjects.size() >= MAX_CAPACITY) {
            System.out.println("农场已满！");
            return false;
        }
        if (money < corn.getBuyPrice()) {
            System.out.println("余额不足！");
            return false;
        }
        money -= corn.getBuyPrice();
        farmObjects.add(corn);
        return true;
    }

    public boolean addAnimal(FarmObject obj) {
        if (farmObjects.size() >= MAX_CAPACITY) {
            System.out.println("农场已满！");
            return false;
        }
        farmObjects.add(obj);
        return true;
    }

    public boolean sell(int id) {
        for (int i = 0; i < farmObjects.size(); i++) {
            FarmObject obj = farmObjects.get(i);
            if (obj.getId() == id) {
                if (obj instanceof Wheat) {
                    money += ((Wheat) obj).getSellPrice();
                } else if (obj instanceof Corn) {
                    money += ((Corn) obj).getSellPrice();
                }
                farmObjects.remove(i);
                return true;
            }
        }
        return false;
    }

    public FarmObject findByName(String name) {
        for (FarmObject obj : farmObjects) {
            if (obj.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }

    public FarmObject findById(int id) {
        for (FarmObject obj : farmObjects) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }

    public boolean removeById(int id) {
        for (int i = 0; i < farmObjects.size(); i++) {
            if (farmObjects.get(i).getId() == id) {
                farmObjects.remove(i);
                return true;
            }
        }
        return false;
    }

    public void careById(int id) {
        FarmObject obj = findById(id);
        if (obj != null) {
            obj.care();
        } else {
            System.out.println("对象不存在");
        }
    }

    public void showAll() {
        if (farmObjects.isEmpty()) {
            System.out.println("农场为空");
            return;
        }
        System.out.println("\n=== 农场所有对象 ===");
        for (FarmObject obj : farmObjects) {
            obj.showInfo();
        }
    }

    public void showMoney() {
        System.out.println("当前余额：" + money + " 元");
    }

    // 以下是【新增功能方法】，原有代码完全没动
    // 3.根据类型输出农场对象
    public void showByType(String type) {
        boolean has = false;
        for (FarmObject o : farmObjects) {
            if (o.getType().equals(type)) {
                o.showInfo();
                has = true;
            }
        }
        if (!has) System.out.println("无该类型农场对象");
    }

    // 4.输出指定编号之前的农场对象
    public void showBeforeId(int targetId) {
        System.out.println("--- 编号" + targetId + "之前的对象 ---");
        for (FarmObject o : farmObjects) {
            if (o.getId() < targetId) {
                o.showInfo();
            }
        }
    }

    // 6.判断指定农场对象的类型
    public void checkObjTypeById(int id) {
        FarmObject o = findById(id);
        FarmObject.checkType(o);
    }

    // 7.收获或移除农场对象
    public void harvestOrRemove(int id) {
        boolean res = sell(id);
        if (res) {
            System.out.println("收获/移除成功");
        } else {
            if (removeById(id)) {
                System.out.println("移除成功");
            } else {
                System.out.println("对象不存在");
            }
        }
    }
}

// 主菜单
public class FarmTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Farm farm = new Farm();

        while (true) {
            System.out.println("\n===== 开心农场系统 =====");
            System.out.println("1.创建农场对象");
            System.out.println("2.根据名称查找农场对象");
            System.out.println("3.根据类型输出农场对象");
            System.out.println("4.输出指定编号之前的农场对象");
            System.out.println("5.照料指定农场对象");
            System.out.println("6.判断指定农场对象的类型");
            System.out.println("7.收获或移除农场对象");
            System.out.println("8.输出所有农场对象");
            System.out.println("0.退出系统");
            System.out.print("请选择：");
            int choose = sc.nextInt();

            if (choose == 0) {
                System.out.println("退出成功");
                break;
            }

            switch (choose) {
                case 1:
                    System.out.println("1.小麦  2.玉米  3.鸡  4.牛");
                    int t1 = sc.nextInt();
                    if (t1 == 1) {
                        farm.addCrop(new Wheat());
                    } else if (t1 == 2) {
                        farm.addCrop(new Corn());
                    } else if (t1 == 3) {
                        System.out.print("给鸡命名：");
                        farm.addAnimal(new Chicken(sc.next()));
                    } else if (t1 == 4) {
                        System.out.print("给牛命名：");
                        farm.addAnimal(new Cow(sc.next()));
                    } else {
                        System.out.println("输入错误");
                    }
                    break;

                case 2:
                    System.out.print("输入要查找的名称：");
                    FarmObject o = farm.findByName(sc.next());
                    if (o != null) o.showInfo();
                    else System.out.println("未找到");
                    break;

                case 3:
                    System.out.print("输入类型(农作物/动物)：");
                    farm.showByType(sc.next());
                    break;

                case 4:
                    System.out.print("输入指定编号：");
                    farm.showBeforeId(sc.nextInt());
                    break;

                case 5:
                    System.out.print("输入要照料的编号：");
                    farm.careById(sc.nextInt());
                    break;

                case 6:
                    System.out.print("输入要判断类型的编号：");
                    farm.checkObjTypeById(sc.nextInt());
                    break;

                case 7:
                    System.out.print("输入要收获/移除的编号：");
                    farm.harvestOrRemove(sc.nextInt());
                    break;

                case 8:
                    farm.showAll();
                    break;

                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
        sc.close();
    }
}