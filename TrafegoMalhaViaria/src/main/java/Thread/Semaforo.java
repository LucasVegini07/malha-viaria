/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread;

import Model.Quadrado;
import Thread.Veiculo;
import java.util.concurrent.Semaphore;

// @author Afonso Uéslei e Lucas Vegini

public class Semaforo extends Veiculo {

    private final Semaphore semaforo;

    public Semaforo(Semaphore semaforo, String nome, Quadrado quadradoAtual, int velocidadeCarro) {
        super(nome, quadradoAtual, velocidadeCarro);
        this.semaforo = semaforo;
    }

    public void andar(Quadrado destino) {
        if (destino.getCarro() != null) {
            return;
        }
        this.quadradoAtual.setCarro(null);
        this.quadradoAtual = destino;
        this.quadradoAtual.setCarro(this);
    }

    public void trataAndarReto(Quadrado destino) {
        if (destino.isCruzamento()) {
            this.defineCaminhoCruzamento(destino);
            this.setEmCruzamento(true);
            this.tentaReservarCruzamento();
            if (this.podeAndarCruzamento) {
                this.trataAndarCruzamento();
            }
            return;
        }
        this.andar(destino);
    }

    public void trataAndarCruzamento() {

        if (!caminhoCruzamento.isEmpty()) {
            Quadrado proxQuadrado = this.caminhoCruzamento.get(0);
            if (!verificaCaminhoCruzamentoContemMesmoQuadrado(proxQuadrado)) {
                proxQuadrado.setReservado(null);
            }
            this.andar(proxQuadrado);
            if (!proxQuadrado.isCruzamento()) {
                this.setEmCruzamento(false);
                this.setPodeAndarCruzamento(false);
                this.caminhoCruzamento.removeAll(caminhoCruzamento);
                return;
            }
            this.caminhoCruzamento.remove(0);
        }

    }

    public void tentaReservarCruzamento() {

        try {
            this.semaforo.acquire();
        } catch (InterruptedException ex) {
        }

        for (Quadrado quadrado : caminhoCruzamento) {
            if (quadrado.getCarro() != null) {
                this.semaforo.release();
                return;
            }
            if (quadrado.getReservado() != null) {
                this.semaforo.release();
                return;
            }
        }
        for (Quadrado quadrado : caminhoCruzamento) {
            quadrado.setReservado(this);
        }
        this.podeAndarCruzamento = true;
        this.semaforo.release();
    }

    public boolean verificaCaminhoCruzamentoContemMesmoQuadrado(Quadrado q) {
        for (int i = 1; i < this.caminhoCruzamento.size(); i++) {
            if (q.getX() == this.caminhoCruzamento.get(i).getX() && q.getY() == this.caminhoCruzamento.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        Quadrado proximoQuadrado = null;

        try {
            this.sleep(this.velocidadeCarro);
        } catch (InterruptedException ex) {
        }

        while (true) {
            if (quadradoAtual.isSaida()) {
                quadradoAtual.setCarro(null);
            } else if (this.emCruzamento) {
                if (this.podeAndarCruzamento) {
                    this.trataAndarCruzamento();
                } else {
                    this.tentaReservarCruzamento();
                    if (this.podeAndarCruzamento) {
                        this.trataAndarCruzamento();
                    } else if (!this.quadradoAtual.isCruzamento()) {
                        this.defineCaminhoCruzamento(this.getProxQuadrado());
                    }
                }

            } else {
                proximoQuadrado = this.getProxQuadrado();
                this.trataAndarReto(proximoQuadrado);
            }
            try {
                this.sleep(this.velocidadeCarro);
            } catch (InterruptedException ex) {
            }
        }
    }

}
