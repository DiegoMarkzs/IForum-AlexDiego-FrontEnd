package dac;

import model.Coordenador;
import service.CoordenadorService;

public class MainDeleteCoordenador {

    public static void main(String[] args) throws Exception{

        Coordenador coordenador = new Coordenador();
        coordenador.setEmail("coordenador@email.com");
        coordenador.setNome("Ana Julia");
        coordenador.setSenha("123");

        try {
            CoordenadorService coordenadorService =  new CoordenadorService();
            coordenadorService.delete(coordenador);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
