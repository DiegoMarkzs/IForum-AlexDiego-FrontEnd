import { useEffect, useState } from 'react';
import styles from './TabelaListarRelatos.module.css';

function Container({ onClose, onRelatoClick }) {
  const [relatos, setRelatos] = useState([]);
  const [filtroStatus, setFiltroStatus] = useState('');
  const [filtroTipo, setFiltroTipo] = useState('');

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user || !user.id) {
      console.error('Usuário não está logado');
      return;
    }

    fetch(`http://localhost:8080/relatos/usuario/${user.id}`)
      .then(res => {
        if (!res.ok) throw new Error('Erro ao carregar relatos');
        return res.json();
      })
      .then(data => setRelatos(data))
      .catch(err => {
        console.error(err);
        setRelatos([]);
      });
  }, []);

  const relatosFiltrados = relatos.filter(relato => {
    const statusOk = !filtroStatus || relato.status === filtroStatus;
    const tipoOk = !filtroTipo || relato.tipo === filtroTipo;
    return statusOk && tipoOk;
  });

  return (
    <div className={styles.wrapper}>
      <span className={styles.iconClose} onClick={onClose}>
        &times;
      </span>

      <h2 className={styles.title}>Enviados</h2>

      {/* Os optionPane do filtro */}
      <div className={styles.filtros}>
        <select onChange={e => setFiltroStatus(e.target.value)} value={filtroStatus}>
          <option value="">Todos os status</option>
          <option value="PENDENTE">Pendente</option>
          <option value="ACEITO">Aceito</option>
          <option value="RECUSADO">Recusado</option>
        </select>

        <select onChange={e => setFiltroTipo(e.target.value)} value={filtroTipo}>
          <option value="">Todos os tipos</option>
          <option value="SUGESTAO">Sugestão</option>
          <option value="DENUNCIA">Denúncia</option>
        </select>
      </div>

      <div className={styles.tableContainer}>
        <table className={styles.tabela}>
          <thead>
            <tr>
              <th>Tipo</th>
              <th>Categoria</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {relatosFiltrados.length === 0 ? (
              <tr>
                <td colSpan="3">Nenhum relato encontrado</td>
              </tr>
            ) : (
              relatosFiltrados.map(relato => (
                <tr
                  key={relato.id}
                  style={{ cursor: 'pointer' }}
                  onClick={() => onRelatoClick(relato)}
                >
                  <td>{relato.tipo || ''}</td>
                  <td>{relato.categoria || ''}</td>
                  <td>{relato.status || ''}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Container;
