import { useState } from 'react';
import styles from './RelatoAnalise.module.css';

function RelatoAnalise({ relato, onClose }) {
  const [statusAtual, setStatusAtual] = useState(relato.status || 'PENDENTE');

  const atualizarStatus = (novoStatus) => {
    const dadosParaEnvio = {
      assunto: relato.assunto || '',
      categoria: relato.categoria || 'INFRAESTRUTURA',
      tipoRelato: relato.tipoRelato === 'SUGESTAO' ? 'SUGESTAO' : 'DENUNCIA',
      anonimo: relato.anonimo || false,
      status: novoStatus,
    };

    fetch(`http://localhost:8080/relatos/${relato.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(dadosParaEnvio),
    })
      .then(async (res) => {
        if (!res.ok) {
          const errorBody = await res.json().catch(() => ({}));
          const errorMsg = errorBody.message || JSON.stringify(errorBody);
          throw new Error(`Erro ao atualizar status: ${errorMsg}`);
        }
        setStatusAtual(novoStatus);
        alert(`Relato ${novoStatus === 'ACEITO' ? 'aprovado' : 'rejeitado'} com sucesso!`);
        onClose();
      })
      .catch((err) => {
        console.error(err);
        alert(`Falha ao atualizar o status: ${err.message}`);
      });
  };

  return (
    <div className={styles.wrapper}>
      <span className={styles.iconClose} onClick={onClose}>&times;</span>

      <h2 className={styles.title}>Analisar Relato</h2>

      <div className={styles.formGroup}>
        <label>Assunto:</label>
        <input type="text" value={relato.assunto || ''} disabled />
      </div>

      <div className={styles.formGroup}>
        <label>Categoria:</label>
        <input type="text" value={relato.categoria || ''} disabled />
      </div>

      <div className={styles.formGroup}>
        <label>Data:</label>
        <input type="date" value={relato.data?.substring(0, 10) || ''} disabled />
      </div>

      <div className={styles.formGroup}>
        <label>Status:</label>
        <span className={styles.statusLabel}>{statusAtual}</span>
      </div>

      {/* O usuario so aparece no relato se o usuario não for anonimo*/}
      {!relato.anonimo && relato.usuario?.nome && (
        <div className={styles.formGroup}>
          <label>Usuário:</label>
          <input type="text" value={relato.usuario.nome} disabled />
        </div>
      )}

      {/* Se o usuario tiver enviado um arquivo, ele vai aparecer la em baixo como "Visualizar anexo" */}
      {relato.arquivoUrl && (
        <div className={styles.formGroup}>
          <label>Arquivo:</label>
          <a href={relato.arquivoUrl} target="_blank" rel="noopener noreferrer">
            Visualizar Anexo
          </a>
        </div>
      )}

      {(statusAtual === 'ACEITO' || statusAtual === 'RECUSADO') ? (
        <p className={styles.mensagemFinalizada}>Você já analisou esse relato!</p>
      ) : (
        <div className={styles.buttonGroup}>
          <button className={styles.botaoAprovar} onClick={() => atualizarStatus('ACEITO')}>
            Aprovar
          </button>
          <button className={styles.botaoRejeitar} onClick={() => atualizarStatus('RECUSADO')}>
            Rejeitar
          </button>
        </div>
      )}
    </div>
  );
}

export default RelatoAnalise;
