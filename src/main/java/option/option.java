package option;

import java.util.Optional;

public class option {
    
    /**
     * 从 Java 8 引入的一个很有趣的特性是 Optional  类。Optional 类主要解决的问题是臭名昭著的空指针异常（NullPointerException） —— 每个 Java 程序员都非常了解的异常。
     * 本质上，这是一个包含有可选值的包装类，这意味着 Optional 类既可以含有对象也可以为空。
     * Optional 是 Java 实现函数式编程的强劲一步，并且帮助在范式中实现。但是 Optional 的意义显然不止于此。
     * @param args
     */
    public static void main(String[] args) {
        // 1.创建 Optional  实例
        // 1.1 empty创建 Optional  实例
        // 重申一下，这个类型的对象可能包含值，也可能为空。你可以使用同名方法创建一个空的 Optional。
        Optional<User> emptyOpt = Optional.empty();
        // 尝试访问 emptyOpt 变量的值会导致 NoSuchElementException
        // emptyOpt.get();

        // 1.2 可以使用  of() 和 ofNullable() 方法创建包含值的 Optional。两个方法的不同之处在于如果你把 null 值作为参数传递进去，of() 方法会抛出 NullPointerException：
        User user = null;
        // Optional<User> opt = Optional.of(user);
        // 我们并没有完全摆脱 NullPointerException。因此，你应该明确对象不为 null  的时候使用 of()。
        // 如果对象即可能是 null 也可能是非 null，你就应该使用 ofNullable() 方法：
        Optional<User> opt2 = Optional.ofNullable(user);
        
        // 2.访问 Optional 对象的值
        // 2.1 get() 访问 Optional 对象的值
        // 从 Optional 实例中取回实际值对象的方法之一是使用 get() 方法：
        String name = "John";
        Optional<String> opt3 = Optional.ofNullable(name);
        System.out.println(opt3.get());
        // 这个方法会在值为 null 的时候抛出异常。要避免异常，你可以选择首先验证是否有值：
        if(opt3.isPresent()){
            System.out.println(opt3.get());
        }
        // 检查是否有值的另一个选择是 ifPresent() 方法。该方法除了执行检查，还接受一个Consumer(消费者) 参数，如果对象不是空的，就对执行传入的 Lambda 表达式：
        // if(opt3.ifPresent()){
            // System.out.println(opt3.get());
        // }

        // 3.返回默认值
        // Optional 类提供了 API 用以返回对象值，或者在对象为空的时候返回默认值。
        // 3.1 这里你可以使用的第一个方法是 orElse()，它的工作方式非常直接，如果有值则返回该值，否则返回传递给它的参数值：
        user = null;
        User user2 = new option().new User();
        user2.setName("name2");
        user2.setEmail("email2@qq.com");
        User result = Optional.ofNullable(user).orElse(user2);
        System.out.println("result:"+result+",result.getEmail():"+result.getEmail());
        // 这里 user 对象是空的，所以返回了作为默认值的 user2。
        // 如果对象的初始值不是 null，那么默认值会被忽略：
        user = new option().new User();
        user.setEmail("email");
        user.setName("name");
        result = Optional.ofNullable(user).orElse(user2);
        System.out.println("result:"+result+",result.getEmail():"+result.getEmail());
        
        // 3.2 第二个同类型的 API 是 orElseGet() —— 其行为略有不同。
        // 这个方法会在有值的时候返回值，如果没有值，它会执行作为参数传入的 Supplier(供应者) 函数式接口，并将返回其执行结果：
        user = null;
        result = Optional.ofNullable(user).orElseGet(() -> {
            User user3 = new option().new User();
            user3.setName("name3");
            user3.setEmail("email3@qq.com");
            return user3;
        });
        System.out.println("result:"+result+",result.getEmail():"+result.getEmail());

        // 3.3 orElse() 和 orElseGet() 的不同之处
        // 乍一看，这两种方法似乎起着同样的作用。然而事实并非如此。我们创建一些示例来突出二者行为上的异同。
        // 我们先来看看对象为空时他们的行为：
        user = null;
        System.out.println("Using orElse");
        result = Optional.ofNullable(user).orElse(createNewUser());
        System.out.println("Using orElseGet");
        result = Optional.ofNullable(user).orElseGet(() -> createNewUser());
        //由此可见，当对象为空而返回默认对象时，行为并无差异。
        //看一个类似的示例，但这里 Optional  不为空：
        user =  new option().new User();
        user.setEmail("email");
        user.setName("name");
        System.out.println("Using orElse");
        result = Optional.ofNullable(user).orElse(createNewUser());
        System.out.println("Using orElseGet");
        result = Optional.ofNullable(user).orElseGet(() -> createNewUser());
        // 这个示例中，两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。不过，orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
        // 在执行较密集的调用时，比如调用 Web 服务或数据查询，这个差异会对性能产生重大影响。
    }   
    static User createNewUser(){
        System.out.println("Creating New User");
        User user = new option().new User();
        user.setName("new name");
        user.setEmail("new email@qq.com");
        return user;
    }

    class User{
        public String name;
        public String email;

        public User(){
            System.out.println("create new User");
        }

        public String getEmail() {
            return email;
        }
        public String getName() {
            return name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
