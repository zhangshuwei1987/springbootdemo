package com.springLearn.springAnoEnableRelate.Import;

import org.springframework.context.annotation.Bean;

public class ImportClassConfig {

    @Bean
    public Dog getDog(){
        return new Dog();
    }

    @Bean
    public Cat getCat(){
        return new Cat();
    }


}
