import { useState, useEffect } from 'react';
import styles from './TabelaFazerRelato.module.css';

function TabelaFazerRelato({ onClose }) {
  const [tipo, setTipo] = useState('DENUNCIA');
  const [categoria, setCategoria] = useState('INFRAESTRUTURA');
  const [descricao, setDescricao] = useState('');
  const [anonimo, setAnonimo] = useState(true);
  const [user, setUser] = useState(null);
  const [arquivo, setArquivo] = useState(null);

  useEffect(() => {
    try {
      const userStr = localStorage.getItem('user');
      if (userStr) {
        const userObj = JSON.parse(userStr);
        if (userObj) {
          setUser(userObj);
        }
      }
    } catch (err) {
      console.error('Erro ao carregar o usuário', err);
    }
  }, []);

  const handleArquivoChange = (e) => {
    setArquivo(e.target.files[0]);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('tipoRelato', tipo);
    formData.append('categoria', categoria);
    formData.append('assunto', descricao);
    formData.append('anonimo', anonimo);
    formData.append('status', 'PENDENTE');
    formData.append('data', new Date().toISOString().slice(0, 10));
    if (!anonimo && user && user.id) {
      formData.append('userId', user.id);
    }
    if (arquivo) {
      formData.append('arquivo', arquivo);
    }

    fetch('http://localhost:8080/relatos/save', {
      method: 'POST',
      body: formData,
    })
      .then(res => {
        if (!res.ok) throw new Error('Erro ao enviar relato');

        alert('Relato enviado com sucesso!');

        if(anonimo){alert("Denuncias anonimas não aparecem na sua lista de denuncias")}
        onClose();
      })
      .catch(err => {
        console.error('Erro ao enviar relato:', err);
        alert('Falha ao enviar o relato.');
      });
  };

  return (
    <div className={styles.wrapper}>
      <span className={styles.iconClose} onClick={onClose}>&times;</span>
      <h2 className={styles.title}>Fazer Relato</h2>
      <form onSubmit={handleSubmit} className={styles.form} encType="multipart/form-data">
        <textarea
          className={styles.textarea}
          placeholder="Descreva o relato..."
          value={descricao}
          onChange={(e) => setDescricao(e.target.value)}
          required
        />

        <div className={styles.selectGroup}>
          <label>Tipo:</label>
          <select value={tipo} onChange={(e) => setTipo(e.target.value)}>
            <option value="DENUNCIA">Denuncia</option>
            <option value="SUGESTAO">Sugestão</option>
          </select>

          <label>Categoria:</label>
          <select value={categoria} onChange={(e) => setCategoria(e.target.value)}>
            <option value="INFRAESTRUTURA">Infraestrutura</option>
            <option value="ENSINO">Ensino</option>
            <option value="SEGURANCA">Segurança</option>
          </select>
        </div>

        <div className={styles.checkboxGroup}>
          <input
            type="checkbox"
            checked={anonimo}
            onChange={(e) => setAnonimo(e.target.checked)}
            id="anonimo"
          />
          <label htmlFor="anonimo">Enviar anonimamente</label>
        </div>

       
        <div className={styles.fileGroup} style={{ margin: '10px 0' }}>
          <label htmlFor="arquivo">Anexar arquivo:</label>
          <input
            type="file"
            id="arquivo"
            onChange={handleArquivoChange}
            accept="image/*,application/pdf" 
          />
        </div>

        <button type="submit" className={styles.botaoEnviar}>Enviar</button>
      </form>
    </div>
  );
}

export default TabelaFazerRelato;
