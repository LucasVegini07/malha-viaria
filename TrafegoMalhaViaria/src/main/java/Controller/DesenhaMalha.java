/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Malha;
import Model.Quadrado;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class DesenhaMalha extends Thread {

    private boolean emExecucao;
    private JTextArea textArea;
    private Malha malha;

    public DesenhaMalha(JTextArea textArea, Malha malha) {
        this.emExecucao = true;
        this.textArea = textArea;
        this.malha = malha;
    }

    public void paraExecucao() {

        this.emExecucao = false;

    }

    @Override
    public void run() {

        Quadrado[][] malhaAux = this.malha.getMalhas();

        String printLinha = "";
        while (this.emExecucao) {

            printLinha = "";

            for (int i = 0; i < malhaAux.length; i++) {
                for (int j = 0; j < malhaAux[i].length; j++) {

                    Quadrado quadrado = malhaAux[i][j];

                    if (quadrado.getDirecao() == null) {
                        printLinha += "  ";
                    } else {
                        printLinha += (quadrado.getCarro() != null ? quadrado.getCarro().getNome() : quadrado.getDirecao().getCaracterExibicao());
                        printLinha += " ";
                    }

                }

                printLinha += "\n";

            }
            this.textArea.setText(printLinha);

            try {
                this.sleep(50);
            } catch (InterruptedException ex) {
            }

        }

    }

}
