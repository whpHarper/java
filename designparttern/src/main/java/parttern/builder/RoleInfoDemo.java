package parttern.builder;

/**
 * @ClassName RoleInfoDemo
 * @Description TODO
 * @Author whp
 * @Date 2022/10/26
 * @Version 1.0
 **/
public class RoleInfoDemo {
    public static void main(String[] args) {
        final RoleInfo whp = new RoleInfo.Builder()
                .addAge(10)
                .addLevel(10)
                .addName("whp")
                .build();
        System.out.println("info"+whp.getName());
    }
}
