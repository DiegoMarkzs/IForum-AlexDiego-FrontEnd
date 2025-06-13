package dac;

import model.Coordenador;
import service.CoordenadorService;

public class MainCreateCoordenador {

    public static void main(String[] args) throws Exception{

        Coordenador coordenador = new Coordenador();
        coordenador.setEmail("coordenador@email.com");
        coordenador.setNome("Ana Julia");
        coordenador.setSenha("123");

        try {
            CoordenadorService coordenadorService =  new CoordenadorService();
            coordenadorService.createCoordenador(coordenador);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
