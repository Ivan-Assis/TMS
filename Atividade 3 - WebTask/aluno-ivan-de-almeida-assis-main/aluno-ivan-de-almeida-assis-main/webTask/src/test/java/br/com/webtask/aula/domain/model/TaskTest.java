package br.com.webtask.aula.domain.model;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    static Task t;

    @BeforeAll
    public static void initBefore() {
        System.out.println("####### Iniciando os testes");
        // planejamento
        t = new Task(0L, "teste 01",
                LocalDate.now().plusDays(1),
                null, null);
    }

    @AfterAll
    public static void finishAfter() {
        System.out.println("####### Finalizando os testes");
    }

    @BeforeEach
    public void init() {
        System.out.println("antes do teste");
    }

    @AfterEach
    public void finish() {
        System.out.println("depois do teste");
    }

    @Test
    @DisplayName("task não finalizada e com data planejada não vencida")
    public void taskComStatusNoPrazo() {
        System.out.println("teste 01");

        EStatus resultadoEsperado = EStatus.NOVO;

        // execução
        EStatus resultadoObtido = null;
        try {
            resultadoObtido = t.getStatus();
        } catch (Exception e) {
            fail("não deveria entrar no catch");
        }

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);

    }

    @Test
    @DisplayName("task não finalizada e com data planejada vencida")
    public void taskComStatusAtrasado() throws Exception {
        System.out.println("teste 02");
        // planejamento
        Task t = new Task(0L, "teste 01",
                LocalDate.now().minusDays(1),
                null, null);

        EStatus resultadoEsperado = EStatus.ATRASADO;

        // execução
        EStatus resultadoObtido = t.getStatus();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);

    }

    @Test
    @DisplayName("task finalizada e com data planejada vencida")
    public void taskFinalizadaComAtraso() throws Exception {
        System.out.println("teste 03");
        // planejamento
        Task t = new Task(0L, "teste 01",
                LocalDate.now().minusDays(1),
                LocalDate.now(), null);

        EStatus resultadoEsperado = EStatus.CONCLUIDO_ATRASADO;

        // execução
        EStatus resultadoObtido = t.getStatus();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);

    }

    @Test
    @DisplayName("task finalizada e com data planejada não vencida")
    public void taskFinalizadaNoPrazo() throws Exception {
        System.out.println("teste 04");
        // planejamento
        Task t = new Task(0L, "teste 01",
                LocalDate.now().plusDays(1),
                LocalDate.now(), null);

        EStatus resultadoEsperado = EStatus.CONCLUIDO_PRAZO;

        // execução
        EStatus resultadoObtido = t.getStatus();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);

    }

    @Test
    @DisplayName("task finalizada e com data planejada no mesmo dia")
    public void taskFinalizadaNoMesmoDiaPrazo() throws Exception {
        System.out.println("teste 05");
        // planejamento
        Task t = new Task(0L, "teste 01",
                LocalDate.now(),
                LocalDate.now(), null);

        EStatus resultadoEsperado = EStatus.CONCLUIDO_PRAZO;

        // execução
        EStatus resultadoObtido = t.getStatus();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);

    }

    @Test
    @DisplayName("task não finalizada e sem data planejada")
    public void taskNaoFinalizadaSemPlanejamento() {
        System.out.println("teste 06");
        // planejamento
        Task t = new Task(0L, "teste 01",
                null,
                null, null);

        String resultadoEsperado = "A data de planejamento não deve ser nula";

        // execução
        try {
            EStatus resultadoObtido = t.getStatus();
            fail("o método foi executado");
        } catch (Exception e) {
            Assertions.assertEquals(resultadoEsperado, e.getMessage());
            // Assertions.assertTrue(true);
        }

    }

    @Test
    @DisplayName("isFinish: tarefa finalizada")
    public void tarefaEstaFinalizada() {
        System.out.println("teste isFinish 01");
        // planejamento
        Task t = new Task(0L, "tarefa",
                LocalDate.now().plusDays(1),
                LocalDate.now(), null);
        boolean resultadoEsperado = true;

        // execução
        boolean resultadoObtido = t.isFinish();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isFinish: tarefa inacabada")
    public void tarefaNaoEstaFinalizada() {
        System.out.println("teste isFinish 02");
        // planejamento
        Task t = new Task(0L, "tarefa",
                LocalDate.now().plusDays(1),
                null, null);
        boolean resultadoEsperado = false;

        // execução
        boolean resultadoObtido = t.isFinish();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isLate: prazo vencido")
    public void tarefaAtrasada() {
        System.out.println("teste isLate 01");
        // planejamento
        Task t = new Task(0L, "tarefa",
                LocalDate.now().minusDays(1),
                null, null);
        boolean resultadoEsperado = true;

        // execução
        boolean resultadoObtido = t.isLate();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isLate: dentro do prazo")
    public void tarefaNaoAtrasada() {
        System.out.println("teste isLate 02");
        // planejamento
        Task t = new Task(0L, "tarefa",
                LocalDate.now().plusDays(1),
                null, null);
        boolean resultadoEsperado = false;

        // execução
        boolean resultadoObtido = t.isLate();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isLate: prazo é hoje")
    public void tarefaComPrazoHoje() {
        System.out.println("teste isLate 03");
        // planejamento
        Task t = new Task(0L, "tarefa",
                LocalDate.now(),
                null, null);
        boolean resultadoEsperado = false;

        // execução
        boolean resultadoObtido = t.isLate();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isDescriptionValid: descrição válida")
    public void descricaoValida() {
        System.out.println("teste isDescriptionValid 01");
        // planejamento
        Task t = new Task(0L, "tarefa",
                LocalDate.now(), null, null);
        boolean resultadoEsperado = true;

        // execução
        boolean resultadoObtido = t.isDescriptionValid();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isDescriptionValid: descrição com espaço")
    public void descricaoComEspaco() {
        System.out.println("teste isDescriptionValid 02");
        // planejamento
        Task t = new Task(0L, "minha tarefa",
                LocalDate.now(), null, null);
        boolean resultadoEsperado = false;

        // execução
        boolean resultadoObtido = t.isDescriptionValid();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isDescriptionValid: descrição com número")
    public void descricaoComNumero() {
        System.out.println("teste isDescriptionValid 03");
        // planejamento
        Task t = new Task(0L, "tarefa1",
                LocalDate.now(), null, null);
        boolean resultadoEsperado = false;

        // execução
        boolean resultadoObtido = t.isDescriptionValid();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    @DisplayName("isDescriptionValid: descrição com espaço e número")
    public void descricaoComEspacoENumero() {
        System.out.println("teste isDescriptionValid 04");
        // planejamento
        Task t = new Task(0L, "teste 01",
                LocalDate.now(), null, null);
        boolean resultadoEsperado = false;

        // execução
        boolean resultadoObtido = t.isDescriptionValid();

        // resultado
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

}