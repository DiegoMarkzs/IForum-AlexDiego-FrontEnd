import { useState, useEffect } from 'react';
import styles from './MenuEditarPerfil.module.css';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse } from '@fortawesome/free-solid-svg-icons';

function MenuEditarPerfil() {
  const navigate = useNavigate();
  const [userData, setUserData] = useState({
    nome: '',
    sobrenome: '',
    nascimento: '',
    setor: '',
    curso: '',
    tipo: '', 
  });

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user) {
      alert('Você precisa estar logado!');
      navigate('/login');
      return;
    }

    let tipoUsuario = 'outro';
    if (user.tipoUsuario) {
      tipoUsuario = user.tipoUsuario.toLowerCase();
    } else {
      tipoUsuario = user.setor ? 'funcionario' : user.curso ? 'aluno' : 'outro';
    }

    setUserData({
      nome: user.nome || '',
      sobrenome: user.sobrenome || '',
      nascimento: user.nascimento || '',
      setor: user.setor || '',
      curso: user.curso || '',
      tipo: tipoUsuario,
    });
  }, [navigate]);

  const handleChange = (e) => {
    setUserData({ ...userData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const user = JSON.parse(localStorage.getItem('user'));

    try {
      const response = await fetch(`http://localhost:8080/api/${user.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData),
      });

      if (!response.ok) throw new Error('Erro ao atualizar dados');

      alert('Dados atualizados com sucesso!');
      alert('Faça login novamente!');
      navigate('/');
    } catch (error) {
      console.error(error);
      alert('Falha ao atualizar os dados.');
    }
  };

  const handleDelete = async () => {
    const confirm = window.confirm('Você tem certeza que deseja excluir sua conta?');
    if (!confirm) return;

    const user = JSON.parse(localStorage.getItem('user'));

    try {
      const response = await fetch(`http://localhost:8080/api/${user.id}`, {
        method: 'DELETE',
      });

      if (!response.ok) throw new Error('Erro ao excluir a conta');

      alert('Conta excluída com sucesso.');
      localStorage.removeItem('user');
      localStorage.removeItem('token');
      navigate('/');
    } catch (error) {
      console.error(error);
      alert('Falha ao excluir a conta.');
    }
  };

  return (
    <div className={styles.container}>
      <button
        className={styles.iconVoltar}
        onClick={() => navigate(-1)}
        aria-label="Voltar"
      >
        <FontAwesomeIcon icon={faHouse} />
      </button>

      <h2 className={styles.title}>Editar Perfil</h2>

      <form className={styles.form} onSubmit={handleSubmit}>
        <label>Nome:</label>
        <input
          type="text"
          name="nome"
          value={userData.nome}
          onChange={handleChange}
          required
        />

        <label>Sobrenome:</label>
        <input
          type="text"
          name="sobrenome"
          value={userData.sobrenome}
          onChange={handleChange}
          required
        />

        <label>Data de Nascimento:</label>
        <input
          type="date"
          name="nascimento"
          value={userData.nascimento}
          onChange={handleChange}
          required
        />

        {userData.tipo === 'funcionario' && (
          <>
            <label>Setor:</label>
            <input
              type="text"
              name="setor"
              value={userData.setor}
              onChange={handleChange}
            />
          </>
        )}

        {userData.tipo === 'aluno' && (
          <>
            <label>Curso:</label>
            <input
              type="text"
              name="curso"
              value={userData.curso}
              onChange={handleChange}
            />
          </>
        )}

        <button type="submit" className={styles.botaoSalvar}>
          Salvar Alterações
        </button>
      </form>

 
      {userData.tipo !== 'coordenador' && (
        <button
          type="button"
          className={styles.botaoExcluir}
          onClick={handleDelete}
        >
          Excluir Conta
        </button>
      )}
    </div>
  );
}

export default MenuEditarPerfil;
