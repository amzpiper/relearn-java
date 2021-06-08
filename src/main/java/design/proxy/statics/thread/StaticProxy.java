package design.proxy.statics.thread;

public class StaticProxy {
    public static void main(String[] args) {

        new ComPany(new I()).marry();
    }
}

interface Marry{
    void marry();
}

class I implements Marry {
    @Override
    public void marry() {
        System.out.println("i was married");
    }
}

class ComPany implements Marry{
    private Marry object;
    
    @Override
    public void marry() {
        PlayGar();
        this.object.marry();
        Cook();
    }

    ComPany(Marry marry){
        this.object = marry;    
    }

    public void PlayGar(){
        System.out.println("租车");
    }

    public void Cook(){
        System.out.println("组织宴席");
    }
}
