package service;

import java.util.List;

import dao.DenunciaDAO;
import dao.PersistenciaDacException;
import model.Denuncia;

public class DenunciaService{
    private DenunciaDAO denunciaDao = new DenunciaDAO(); 

    //USUARIO NORMAL
    public void salvar(Denuncia denuncia) throws PersistenciaDacException{
        denuncia.setDeferido(false);
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
