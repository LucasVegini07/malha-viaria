/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread;

import Model.Direcao;
import Model.Malha;
import Thread.Monitor;
import Model.Quadrado;
import Thread.Semaforo;
import Thread.Veiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

 // @author Afonso Uéslei e Lucas Vegini

public final class AdicionarCarro extends Thread {

    private boolean emExecucao;
    private final Malha malha;
    private final List<Quadrado> listQuadradosEntrada;
    private final int qtdTotalCarros;
    private final Semaphore semaforoPrincipal;
    private final int tempoInsercao;
    private final Random random;
    private final String tipo;
    private final int velocidadeCarro;

    public AdicionarCarro(Malha malha, int qtdTotalCarros, int tempoInsercao, String tipo, int velocidadeCarro) {
        this.emExecucao = true;
        this.malha = malha;
        this.semaforoPrincipal = new Semaphore(1);
        this.listQuadradosEntrada = new ArrayList<Quadrado>();
        this.qtdTotalCarros = qtdTotalCarros;
        this.tempoInsercao = tempoInsercao;
        this.random = new Random();
        this.tipo = tipo;
        this.velocidadeCarro = velocidadeCarro;
        this.setQuadradosEntradaSaida();
    }

    public void paraExecucao() {

        this.emExecucao = false;

    }

    private String getIdentificadorCarro() {

        char c = (char) (random.nextInt(26) + 'a');

        return c + "";
    }

    public void setQuadradosEntradaSaida() {

        Quadrado[][] matrizQuadrados = this.malha.getMalhas();

        for (int x = 0; x < matrizQuadrados[0].length; x++) {

            Quadrado qCima = matrizQuadrados[0][x];
            Quadrado qBaixo = matrizQuadrados[matrizQuadrados.length - 1][x];

            if (qCima != null && qCima.getDirecao() == Direcao.BAIXO) {
                this.listQuadradosEntrada.add(qCima);
            }

            if (qCima != null && qCima.getDirecao() == Direcao.CIMA) {
                qCima.setIsSaida(true);
            }

            if (qBaixo != null && qBaixo.getDirecao() == Direcao.CIMA) {
                this.listQuadradosEntrada.add(qBaixo);
            }
            if (qBaixo != null && qBaixo.getDirecao() == Direcao.BAIXO) {
                qBaixo.setIsSaida(true);
            }

        }

        for (int x = 0; x < matrizQuadrados.length; x++) {

            Quadrado qDireita = matrizQuadrados[x][matrizQuadrados[0].length - 1];
            Quadrado qEsquerda = matrizQuadrados[x][0];

            if (qDireita != null && qDireita.getDirecao() == Direcao.ESQUERDA) {

                this.listQuadradosEntrada.add(qDireita);
            }

            if (qDireita != null && qDireita.getDirecao() == Direcao.DIREITA) {
                qDireita.setIsSaida(true);
            }

            if (qEsquerda != null && qEsquerda.getDirecao() == Direcao.DIREITA) {
                this.listQuadradosEntrada.add(qEsquerda);
            }
            if (qEsquerda != null && qEsquerda.getDirecao() == Direcao.ESQUERDA) {
                qEsquerda.setIsSaida(true);
            }
        }

    }

    @Override
    public void run() {
        int veiculos = 0;
        Veiculo veiculo;
        Quadrado quadradoEntrada;
        while (this.emExecucao) {

            quadradoEntrada = this.listQuadradosEntrada.get(random.nextInt(this.listQuadradosEntrada.size()));

            if (tipo.equals("Semáforo")) {
                veiculo = new Semaforo(this.semaforoPrincipal, this.getIdentificadorCarro(), quadradoEntrada, velocidadeCarro);
            } else {
                veiculo = new Monitor(this.getIdentificadorCarro(), quadradoEntrada, velocidadeCarro);
            }
            if (quadradoEntrada.getCarro() == null) {
                quadradoEntrada.setCarro(veiculo);
                veiculo.start();
                try {
                    this.sleep(tempoInsercao);
                } catch (InterruptedException ex) {
                }
                veiculos++;
            }

            if (veiculos == qtdTotalCarros) {
                break;

            }

        }

    }

}
