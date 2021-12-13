/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

 // @author Afonso Uéslei e Lucas Vegini

public class LeitorMalha {

    private       int    quadrado[][];
    private final String diretorio;
    private       Malha  malha;

    public LeitorMalha(String diretorio) {
        this.diretorio = diretorio;
    }

    public Malha lerArquivo() throws IOException {

        BufferedReader buffRead = null;

        try {
            buffRead = new BufferedReader(new FileReader(this.diretorio));
        } catch (FileNotFoundException ex) {}

        if (buffRead == null) {
            return null;
        }

        this.setMalhaTamanho(buffRead);
        this.setMalhaDirecao(buffRead);

        buffRead.close();

        return this.converteIntParaQuadrado();

    }

    public Malha converteIntParaQuadrado() throws IOException {

        for (int x = 0; x < quadrado.length; x++) {
            for (int y = 0; y < quadrado[0].length; y++) {

                if (quadrado[x][y] == 0) {
                    malha.setMalha(x, y, null);
                }

                Direcao  direcao  = getDirecao(quadrado[x][y]);
                Quadrado quadrado = new Quadrado(direcao, x, y);
                
                malha.setMalha(x, y, quadrado);
            }

        }
        for (int x = 0; x < quadrado.length; x++) {
            for (int y = 0; y < quadrado[0].length; y++) {
                malha.getMalha(x, y).setMalha(malha);
            }
        }

        return malha;

    }

    public void setMalhaTamanho(BufferedReader buffRead) throws IOException {

        String strQtdLinhas  = buffRead.readLine().trim();
        String strQtdColunas = buffRead.readLine().trim();

        int qtdLinhas  = Integer.parseInt(strQtdLinhas);
        int qtdColunas = Integer.parseInt(strQtdColunas);

        this.quadrado = new int[qtdLinhas][qtdColunas];
        this.malha    = new Malha(qtdLinhas, qtdColunas);

    }

    public void setMalhaDirecao(BufferedReader buffRead) throws IOException {

        String srtLinha = buffRead.readLine();
        int    numLinha = 0;

        while (srtLinha != null) {
            String[] numerosLinha = srtLinha.split("\t");

            this.setDirecaoLinha(numLinha, this.linhaStringToInt(numerosLinha));
 
            numLinha++;

            srtLinha = buffRead.readLine();
        }

    }

    public void setDirecaoLinha(int x, int[] linha) {

        for (int y = 0; y < this.quadrado[x].length; y++) {
            this.quadrado[x][y] = linha[y];
        }
        
    }

    public int[] linhaStringToInt(String[] linha) {

        int[] linhaInt = new int[linha.length];

        for (int x = 0; x < linha.length; x++) {
            linhaInt[x] = Integer.parseInt(linha[x]);
        }

        return linhaInt;
    }

    public Direcao getDirecao(int intDir) {
        Direcao retorno = null;

        switch (intDir) {
            case 1:
                retorno = Direcao.CIMA;
                break;
            case 2:
                retorno = Direcao.DIREITA;
                break;
            case 3:
                retorno = Direcao.BAIXO;
                break;
            case 4:
                retorno = Direcao.ESQUERDA;
                break;
            case 5:
                retorno = Direcao.CRUZAMENTO_CIMA;
                break;
            case 6:
                retorno = Direcao.CRUZAMENTO_DIREITA;
                break;
            case 7:
                retorno = Direcao.CRUZAMENTO_BAIXO;
                break;
            case 8:
                retorno = Direcao.CRUZAMENTO_ESQUERDA;
                break;
            case 9:
                retorno = Direcao.CRUZAMENTO_CIMA_DIRETIA;
                break;
            case 10:
                retorno = Direcao.CRUZAMENTO_CIMA_ESQUERDA;
                break;
            case 11:
                retorno = Direcao.CRUZAMENTO_BAIXO_DIREITA;
                break;
            case 12:
                retorno = Direcao.CRUZAMENTO_BAIXO_ESQUERDA;
                break;
        }

        return retorno;

    }

}
