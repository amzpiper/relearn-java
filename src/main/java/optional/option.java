package optional;

import java.util.ArrayList;
import java.util.List;
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

        // 4.返回异常
        //除了 orElse() 和 orElseGet() 方法，Optional 还定义了 orElseThrow() API —— 它会在对象为空的时候抛出异常，而不是返回备选的值：
        // user = null;
        result = Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException());
        //这里，如果 user 值为 null，会抛出 IllegalArgumentException。
        // 这个方法让我们有更丰富的语义，可以决定抛出什么样的异常，而不总是抛出 NullPointerException。
        // 现在我们已经很好地理解了如何使用 Optional，我们来看看其它可以对 Optional 值进行转换和过滤的方法。

        // 5.转换值和过滤的方法
        // 5.1 转换值
        // 有很多种方法可以转换 Optional  的值。我们从 map() 和 flatMap() 方法开始。
        // 先来看一个使用 map() API 的例子：
        user = new option().new User();
        user.setEmail("email@qq.com");
        user.setName("name");
        String email = Optional.ofNullable(user).map( u -> u.getEmail()).orElse("other@qq.com");
        System.out.println(email);
        // map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中。这就使对返回值进行链试调用的操作成为可能 —— 这里的下一环就是 orElse()。
        // 相比这下，flatMap() 也需要函数作为参数，并对值调用这个函数，然后直接返回结果。
        // 下面的操作中，我们给 User 类添加了一个方法，用来返回 Optional：
        // 既然 getter 方法返回 String 值的 Optional，你可以在对 User 的 Optional 对象调用 flatMap() 时，用它作为参数。其返回的值是解除包装的 String 值：
        user.setPosition("Developer");
        String position = Optional.ofNullable(user).flatMap( u -> u.getPosition()).orElse("default");
        System.out.println(position);

        // 5.2 过滤值
        // Optional  类也提供了按条件“过滤”值的方法。
        // filter() 接受一个 Predicate 参数，返回测试结果为 true 的值。如果测试结果为 false，会返回一个空的 Optional。
        // 来看一个根据基本的电子邮箱验证来决定接受或拒绝 User(用户) 的示例：
        Optional<User> result2 = Optional.ofNullable(user).filter(u -> u.getEmail()!=null && u.getEmail().contains("@"));
        System.out.println(result2.isPresent());
        System.out.println(result2.get().getEmail());
        // 如果通过过滤器测试，result 对象会包含非空值。

        // 6.Optional 类的链式方法
        Country country = new option().new Country();
        country.setIsocode("abc");

        Address address = new option().new Address();
        address.setCountry(country);

        user = new option().new User();
        user.setEmail("email@qq.com");
        user.setName("name");
        user.setPosition("Developer");
        user.setAddress(address);
        // 现在可以删除 null 检查，替换为 Optional 的方法：
        // 结果现在的代码看起来比之前采用条件分支的冗长代码简洁多了。
        String msg = Optional.ofNullable(user).flatMap(User::getAddress).flatMap(Address::getCountry).map(Country::getIsocode).orElse("defalut").toUpperCase();
        System.out.println("msg:"+msg);

        // 7.总结：
        // Optional 主要用作返回类型。在获取到这个类型的实例后，如果它有值，你可以取得这个值，否则可以进行一些替代行为。
        // Optional 类有一个非常有用的用例，就是将其与流或其它返回 Optional 的方法结合，以构建流畅的API。
        // 我们来看一个示例，使用 Stream 返回 Optional 对象的 findFirst() 方法：
        List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user);
        Optional<User> findFirst = users.stream().findFirst();
        findFirst.filter(u -> u.getEmail()!=null && u.getEmail().contains("$")).orElse(user2);
        System.out.println(user.getEmail());
        // Optional 是 Java 语言的有益补充 —— 它旨在减少代码中的 NullPointerExceptions，虽然还不能完全消除这些异常。
        // 它也是精心设计，自然融入 Java 8 函数式支持的功能。
        // 总的来说，这个简单而强大的类有助于创建简单、可读性更强、比对应程序错误更少的程序。
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
        private String position;
        private Address address;

        public User(){
            System.out.println("create new User");
        }

        public void setPosition(String string) {
            this.position = string;
        }

        public Optional<String> getPosition(){
            return Optional.ofNullable(position);
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
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
    class Address{
        private Country country;

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }

        public void setCountry(Country country) {
            this.country = country;
        }
    }

    class Country{
        public String isocode;

        public String getIsocode() {
            return isocode;
        }

        public void setIsocode(String isocode) {
            this.isocode = isocode;
        }
    }
}
