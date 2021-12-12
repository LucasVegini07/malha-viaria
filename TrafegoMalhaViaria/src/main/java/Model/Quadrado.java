/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Thread.Veiculo;

 // @author Afonso Uéslei e Lucas Vegini

public class Quadrado {

    private Veiculo carro;
    private Veiculo reservado;
    private Direcao direcao;
    private boolean saida;
    private int x;
    private int y;
    private Malha malha;

    public Quadrado(Direcao direcao, int x, int y) {
        this.malha = malha;
        this.direcao = direcao;
        this.x = x;
        this.y = y;
    }

    public boolean isSaida() {
        return saida;
    }

    public void setIsSaida(boolean isSaida) {
        this.saida = isSaida;
    }

    public Veiculo getReservado() {
        return reservado;
    }

    public void setReservado(Veiculo reservado) {
        this.reservado = reservado;
    }

    public Veiculo getCarro() {
        return carro;
    }

    public void setCarro(Veiculo carro) {
        this.carro = carro;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Malha getMalha() {
        return malha;
    }

    public void setMalha(Malha malha) {
        this.malha = malha;
    }

    public boolean isCruzamento() {

        return this.direcao == Direcao.CRUZAMENTO_CIMA
                || this.direcao == Direcao.CRUZAMENTO_BAIXO
                || this.direcao == Direcao.CRUZAMENTO_DIREITA
                || this.direcao == Direcao.CRUZAMENTO_ESQUERDA
                || this.direcao == Direcao.CRUZAMENTO_BAIXO_DIREITA
                || this.direcao == Direcao.CRUZAMENTO_BAIXO_ESQUERDA
                || this.direcao == Direcao.CRUZAMENTO_CIMA_DIRETIA
                || this.direcao == Direcao.CRUZAMENTO_CIMA_ESQUERDA;

    }

}
