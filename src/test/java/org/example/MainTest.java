package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    @Test
    @DisplayName("1 + 1 == 2")
    void t1(){
        assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }
    @Test
    @DisplayName("1 + 2 == 3")
    void t2(){
        assertThat(Calc.run("1 + 2")).isEqualTo(3);
    }
    @Test
    @DisplayName("2 + 2 == 4")
    void t3(){
        assertThat(Calc.run("2 + 2")).isEqualTo(4);
    }
    @Test
    @DisplayName("1000 + 280 == 1280")
    void t4(){
        assertThat(Calc.run("1000 + 280")).isEqualTo(1280);
    }


}