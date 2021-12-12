/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Thread.AdicionarCarro;
import Thread.DesenhaMalha;
import Model.LeitorMalha;
import Model.Malha;
import View.Tela;
import java.io.IOException;

/**
 *
 * @author lucas
 */
public class Controller {

    private Tela view;
    private Malha malha;
    private AdicionarCarro addCarro;
    private DesenhaMalha desenhaMalha;

    public void setView(Tela view) {
        this.view = view;
    }

    public void CriaMalha(String caminho) {
        LeitorMalha leitor = new LeitorMalha(caminho);
        try {
            this.malha = leitor.lerArquivo();

        } catch (IOException ex) {
        }
        desenhaMalha = new DesenhaMalha(this.view.getTextArea(), this.malha);
        this.desenhaMalha = desenhaMalha;
        desenhaMalha.start();

    }

    public void iniciaSimulacao(int qtdTotalCarros, int tempoInsercao, String tipo, int velocidadeCarro) {

        AdicionarCarro addCarroLocal = new AdicionarCarro(this.malha, qtdTotalCarros, tempoInsercao, tipo, velocidadeCarro);
        this.addCarro = addCarroLocal;
        addCarroLocal.start();
    }

    public void pararSimulacao() {
        this.addCarro.paraExecucao();
    }

    public void pararSimulacaoAgora() {
        this.addCarro.paraExecucao();
        this.desenhaMalha.paraExecucao();
    }

}
