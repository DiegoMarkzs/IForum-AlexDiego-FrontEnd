import styles from './Container.module.css';

function Container({ onClose }) {
  const denuncias = [
    { id: 1, tipoDenuncia: 'Anonima', categoria: 'Corrupção', status: 'Aberta' },
    { id: 2, tipoDenuncia: 'Pública', categoria: 'Assédio', status: 'Fechada' },
  ];

  return (
    <div className={styles.wrapper}>
      <span className={styles.iconClose} onClick={onClose}>
        &times;
      </span>

      <h2 className={styles.title}>Enviados</h2>

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
            {denuncias.map((denuncia) => (
              <tr
                key={denuncia.id}
                onClick={() => (window.location.href = `/detalhe/${denuncia.id}`)}
              >
                <td>{denuncia.tipoDenuncia}</td>
                <td>{denuncia.categoria}</td>
                <td>{denuncia.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Container;
