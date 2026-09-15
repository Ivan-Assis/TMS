package br.com.webtask.aula.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserClientTest {

    @Test
    @DisplayName("isEmailValid: email válido")
    public void emailValido() {
        System.out.println("teste isEmailValid 01");
        // planejamento
        UserClient u = UserClient.builder()
                .id(1L)
                .name("Ivan")
                .cpf("12345678901")
                .email("ivan@gmail.com")
                .senha("123")
                .ativo(true)
                .build();
        boolean resultadoEsperado = true;

        // execução
        boolean resultadoObtido = u.isEmailValid();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isEmailValid: email começando com @")
    public void emailInvalidoComecaComArroba() {
        System.out.println("teste isEmailValid 02");
        // planejamento
        UserClient u = UserClient.builder()
                .id(1L)
                .name("Ivan")
                .cpf("12345678901")
                .email("@gmail.com")
                .senha("123")
                .ativo(true)
                .build();
        boolean resultadoEsperado = false;

        // execução
        boolean resultadoObtido = u.isEmailValid();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isEmailValid: email sem ponto")
    public void emailInvalidoSemPonto() {
        System.out.println("teste isEmailValid 03");
        // planejamento
        UserClient u = UserClient.builder()
                .id(1L)
                .name("Ivan")
                .cpf("12345678901")
                .email("ivan@gmail")
                .senha("123")
                .ativo(true)
                .build();
        boolean resultadoEsperado = false;

        // execução
        boolean resultadoObtido = u.isEmailValid();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isEmailValid: email com ponto no nome")
    public void emailInvalidoComPontoNoNome() {
        System.out.println("teste isEmailValid 04");
        // planejamento
        UserClient u = UserClient.builder()
                .id(1L)
                .name("Ivan")
                .cpf("12345678901")
                .email("ivan.almeida@gmail.com")
                .senha("123")
                .ativo(true)
                .build();
        boolean resultadoEsperado = false;

        // execução
        boolean resultadoObtido = u.isEmailValid();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }
}