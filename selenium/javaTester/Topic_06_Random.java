package javaTester;

import java.util.Random;

public class Topic_06_Random {
    //Java Buitin: thư viện cung ấp sẵn
    //Java libariries do 1 cá nhân or tổ chức tự viết
    //Java Faker: random name,email,phone,address có nghĩa

    public static void main(String[] args) {
        Random rand = new Random(); // Khởi tạo đối tượng Random
        System.out.println(rand.nextInt());
        System.out.println(rand.nextInt());

        System.out.println(rand.nextDouble());

        System.out.println(rand.nextFloat());

        System.out.println(rand.nextBoolean());

        System.out.println(rand.nextInt(99999));

        System.out.println("automation" + rand.nextInt(99999) + "@gmail.net");
        System.out.println("automation" + rand.nextInt(99999) + "@gmail.net");
        System.out.println("automation" + rand.nextInt(99999) + "@gmail.net");
        //System.out.println("automation" + rand.nextInt(99999) + "@gmail.net");

    }
}
