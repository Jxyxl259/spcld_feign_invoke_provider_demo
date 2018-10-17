package com.spcld.demo.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ProviderApplicationTests {

    @Test
    public void contextLoads() {


                System.out.println("hello world!");
                byte bTemp=111;
                System.out.println(bTemp);
// float fTemp=3.3f;
                int iTemp1=1,iTemp2=1;
                boolean bTemp1=(++iTemp1>0)||true&&(++iTemp2>0);//这个应该是 1 2才对吧 我这输出21
        System.out.println(bTemp1);
                System.out.println(iTemp1+""+iTemp2);
                System.out.println(false && true || true);//true 因为 &&比||优先
                System.out.println(true || true && false);//true 因为 &&比||优先
         }

}
