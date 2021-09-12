package com.amine.ciklumchallenge

import com.amine.ciklumchallenge.controller.GameController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes=com.amine.ciklumchallenge.GameApplication.class)
class GameApplicationSpec extends spock.lang.Specification {

    @Autowired (required = false)
    private  GameController gameController

    def "When context is loaded then all expected beans are created"() {
        expect: "the gameController is created"
        gameController
    }
}