package service;

import java.util.List;

import dao.DenunciaDAO;
import dao.PersistenciaDacException;
import model.Denuncia;
import model.DenunciaAnonima;
import model.DenunciaPublica;
import model.User;

public class DenunciaService{
    private DenunciaDAO denunciaDao = new DenunciaDAO(); 

    //USUARIO NORMAL
    public void fazerDenunciaAnonima(DenunciaAnonima denuncia) throws PersistenciaDacException{
        denunciaDao.save(denuncia);
    }

    public void fazerDenunciaPublica(DenunciaPublica denuncia, User usuario) throws PersistenciaDacException{
        denuncia.setUsuario(usuario);
        denunciaDao.save(denuncia);
    }


    

    //COORDENADOR
	public List<Denuncia> listarTodas() throws PersistenciaDacException{
        return denunciaDao.getAll();
    }

    public void categorizarDenuncia(boolean deferimento, Denuncia denuncia) throws PersistenciaDacException{
        denuncia.setDeferido(deferimento);
        denunciaDao.update(denuncia);

    }


}
