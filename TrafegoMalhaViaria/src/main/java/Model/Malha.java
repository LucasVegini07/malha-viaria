/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

// @author Afonso Uéslei e Lucas Vegini
public class Malha {

    private Quadrado[][] quadrados;

    public Malha(int x, int y) {
        this.quadrados = new Quadrado[x][y];
    }

    public Quadrado getMalha(int x, int y) {
        return this.quadrados[x][y];
    }

    public void setMalha(int x, int y, Quadrado quadrado) {
        this.quadrados[x][y] = quadrado;
    }

    public void setMalhas(Quadrado[][] malhas) {

        this.quadrados = malhas;

    }

    public Quadrado[][] getMalhas() {

        return this.quadrados;

    }

}
