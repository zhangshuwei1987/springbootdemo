package com.zhangsw.springbootdemo;


import com.springLearn.propertiesRelate.TestCustomMethodProperties;
import com.springLearn.propertiesRelate.TestCustomProperties;
import com.springLearn.propertiesRelate.TestMainProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {



    @Autowired
    TestMainProperties testMainProperties;

    @Autowired
    TestCustomProperties type1;

    @Autowired
    TestCustomMethodProperties.Type2 type2;

    @Autowired
    TestCustomMethodProperties.Type3 type3;

    @Test
    public void test(){

        System.out.println(type1);

        System.out.println(type2);

        System.out.println(type3);
    }

}
