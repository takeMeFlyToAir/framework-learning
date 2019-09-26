package com.zzr.framework.abstractFactory;

/**
 * Created by zhaozhirong on 2019/6/10.
 */
public class HumanFactory extends AbstractHumanFactory {
    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        Human human = null;
        try {
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人种生成错误");
            e.printStackTrace();
        }
        return (T) human;
    }
}
