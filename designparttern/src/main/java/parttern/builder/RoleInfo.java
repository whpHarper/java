package parttern.builder;

/**
 * @ClassName RoleInfo
 * @Description TODO
 * @Author whp
 * @Date 2022/10/26
 * @Version 1.0
 **/
public class RoleInfo {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    private String name;
    private Integer age;
    private Integer level;

    private RoleInfo(Builder builder){
        this.name=builder.name;
        this.age=builder.age;
        this.level=builder.level;
    }
    public static class Builder{
        private String name;
        private Integer age;
        private Integer level;

        public Builder addName(String name){
            this.name=name;
            return this;
        }
        public Builder addAge(Integer age){
            this.age=age;
            return this;
        }
        public Builder addLevel(Integer level){
            this.level=level;
            return this;
        }
        public RoleInfo build(){
            return new RoleInfo(this);
        }
    }
}
