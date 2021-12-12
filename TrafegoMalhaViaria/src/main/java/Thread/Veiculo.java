/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread;

import Model.Malha;
import Model.Malha;
import Model.Quadrado;
import Model.Quadrado;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

 // @author Afonso Uéslei e Lucas Vegini

public abstract class Veiculo extends Thread {

    public String nome;
    public Quadrado quadradoAtual;
    public boolean emCruzamento;
    public boolean podeAndarCruzamento;
    public List<Quadrado> caminhoCruzamento;
    public final int velocidadeCarro;

    public Random random;

    public Veiculo(String nome, Quadrado quadradoAtual, int velocidadeCarro) {
        this.nome = nome;
        this.quadradoAtual = quadradoAtual;
        this.emCruzamento = false;
        this.caminhoCruzamento = new ArrayList<Quadrado>();
        this.random = new Random();
        this.velocidadeCarro = velocidadeCarro;
    }

    public String getNome() {
        return nome;
    }

    public boolean getEmCruzamento() {
        return this.emCruzamento;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Quadrado getQuadradoAtual() {
        return quadradoAtual;
    }

    public void setQuadradoAtual(Quadrado quadradoAtual) {
        this.quadradoAtual = quadradoAtual;
    }

    public boolean isEmCruzamento() {
        return emCruzamento;
    }

    public void setEmCruzamento(boolean emCruzamento) {
        this.emCruzamento = emCruzamento;
    }

    public boolean isPodeAndarCruzamento() {
        return podeAndarCruzamento;
    }

    public void setPodeAndarCruzamento(boolean podeAndarCruzamento) {
        this.podeAndarCruzamento = podeAndarCruzamento;
    }

    public Quadrado getQuadradoCima() {
        Malha malha = this.quadradoAtual.getMalha();
        Quadrado destino = malha.getMalha(this.quadradoAtual.getX() - 1, this.quadradoAtual.getY());
        return destino;
    }

    public Quadrado getQuadradoEsquerda() {
        Malha malha = this.quadradoAtual.getMalha();
        Quadrado destino = malha.getMalha(this.quadradoAtual.getX(), this.quadradoAtual.getY() - 1);
        return destino;
    }

    public Quadrado getQuadradoAbaixo() {
        Malha malha = this.quadradoAtual.getMalha();
        Quadrado destino = malha.getMalha(this.quadradoAtual.getX() + 1, this.quadradoAtual.getY());
        return destino;
    }

    public Quadrado getQuadradoDireita() {
        Malha malha = this.quadradoAtual.getMalha();
        Quadrado destino = malha.getMalha(this.quadradoAtual.getX(), this.quadradoAtual.getY() + 1);
        return destino;
    }

    public Quadrado getQuadradoCimaRef(Quadrado q) {
        Malha malha = q.getMalha();
        Quadrado destino = malha.getMalha(q.getX() - 1, q.getY());
        return destino;
    }

    public Quadrado getQuadradoEsquerdaRef(Quadrado q) {
        Malha malha = q.getMalha();
        Quadrado destino = malha.getMalha(q.getX(), q.getY() - 1);
        return destino;
    }

    public Quadrado getQuadradoAbaixoRef(Quadrado q) {
        Malha malha = q.getMalha();
        Quadrado destino = malha.getMalha(q.getX() + 1, q.getY());
        return destino;
    }

    public Quadrado getQuadradoDireitaRef(Quadrado q) {
        Malha malha = q.getMalha();
        Quadrado destino = malha.getMalha(q.getX(), q.getY() + 1);
        return destino;
    }

    public Quadrado getProxQuadrado() {

        Quadrado proximoQuadrado = null;

        switch (this.quadradoAtual.getDirecao()) {
            case CIMA:
                proximoQuadrado = this.getQuadradoCima();
                break;
            case DIREITA:
                proximoQuadrado = this.getQuadradoDireita();
                break;
            case BAIXO:
                proximoQuadrado = this.getQuadradoAbaixo();
                break;
            case ESQUERDA:
                proximoQuadrado = this.getQuadradoEsquerda();
                break;
            case CRUZAMENTO_CIMA:
                proximoQuadrado = this.getQuadradoCima();
                break;
            case CRUZAMENTO_DIREITA:
                proximoQuadrado = this.getQuadradoDireita();
                break;
            case CRUZAMENTO_BAIXO:
                proximoQuadrado = this.getQuadradoAbaixo();
                break;
            case CRUZAMENTO_ESQUERDA:
                proximoQuadrado = this.getQuadradoEsquerda();
                break;
        }
        return proximoQuadrado;
    }

    public void sairQuadrado() {
        this.quadradoAtual.setCarro(null);

    }

    public Quadrado defineProxQuadradoCruzamento(Quadrado q1, Quadrado q2) {

        if (caminhoCruzamento.contains(q1)) {
            return q2;
        }
        if (caminhoCruzamento.contains(q2)) {
            return q1;
        }

        if (random.nextInt(2) == 0) {
            return q1;
        } else {
            return q2;
        }
    }

    public void defineCaminhoCruzamento(Quadrado quadradoIniCruzamento) {
        caminhoCruzamento.removeAll(caminhoCruzamento);
        caminhoCruzamento.add(quadradoIniCruzamento);
        Quadrado proxQuadrado = quadradoIniCruzamento;
        Quadrado q1 = null;
        Quadrado q2 = null;
        while (true) {
            switch (proxQuadrado.getDirecao()) {
                case CRUZAMENTO_CIMA:
                    proxQuadrado = this.getQuadradoCimaRef(proxQuadrado);
                    break;
                case CRUZAMENTO_BAIXO:
                    proxQuadrado = this.getQuadradoAbaixoRef(proxQuadrado);
                    break;
                case CRUZAMENTO_ESQUERDA:
                    proxQuadrado = this.getQuadradoEsquerdaRef(proxQuadrado);
                    break;
                case CRUZAMENTO_DIREITA:
                    proxQuadrado = this.getQuadradoDireitaRef(proxQuadrado);
                    break;
                case CRUZAMENTO_BAIXO_DIREITA:
                    q1 = this.getQuadradoAbaixoRef(proxQuadrado);
                    q2 = this.getQuadradoDireitaRef(proxQuadrado);
                    proxQuadrado = defineProxQuadradoCruzamento(q1, q2);
                    break;
                case CRUZAMENTO_BAIXO_ESQUERDA:
                    q1 = this.getQuadradoAbaixoRef(proxQuadrado);
                    q2 = this.getQuadradoEsquerdaRef(proxQuadrado);
                    proxQuadrado = defineProxQuadradoCruzamento(q1, q2);
                    break;
                case CRUZAMENTO_CIMA_DIRETIA:
                    q1 = this.getQuadradoCimaRef(proxQuadrado);
                    q2 = this.getQuadradoDireitaRef(proxQuadrado);
                    proxQuadrado = defineProxQuadradoCruzamento(q1, q2);
                    break;
                case CRUZAMENTO_CIMA_ESQUERDA:
                    q1 = this.getQuadradoCimaRef(proxQuadrado);
                    q2 = this.getQuadradoEsquerdaRef(proxQuadrado);
                    proxQuadrado = defineProxQuadradoCruzamento(q1, q2);
                    break;
            }
            caminhoCruzamento.add(proxQuadrado);
            if (!proxQuadrado.isCruzamento()) {
                break;
            }
        }
    }

}
