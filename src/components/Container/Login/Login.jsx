import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styles from './Login.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse } from '@fortawesome/free-solid-svg-icons';

function Login() {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, senha }),
      });

      if (!response.ok) {
        alert('Dados inválidos!!!!');
        return;
      }

      const user = await response.json();
      localStorage.setItem('user', JSON.stringify(user));
      

      if (user.tipoUsuario === 'COORDENADOR') {
        navigate('/coordenador', { state: { user } });
      } else if (user.tipoUsuario === 'ALUNO' || user.tipoUsuario === 'FUNCIONARIO') {
        navigate('/cliente', { state: { user } });
      } else {
        alert('Tipo de usuário não reconhecido');
      }
    } catch (error) {
      console.error('Erro ao fazer login:', error);
      alert('Erro ao conectar com o servidor');
    }
  };

  return (
    <div className={styles.wrapper}>
      <button
        className={styles.iconVoltar}
        onClick={() => navigate('/')}
        aria-label="Voltar para home"
      >
        <FontAwesomeIcon icon={faHouse} />
      </button>

      <h2 className={styles.title}>Login</h2>

      <form onSubmit={handleSubmit} className={styles.form}>
        <div className={styles.formGroup}>
          <label htmlFor="email">Email:</label>
          <input
            id="email"
            type="email"
            value={email}
            onChange={e => setEmail(e.target.value)}
            required
            placeholder="Digite seu email"
          />
        </div>

        <div className={styles.formGroup}>
          <label htmlFor="senha">Senha:</label>
          <input
            id="senha"
            type="password"
            value={senha}
            onChange={e => setSenha(e.target.value)}
            required
            placeholder="Digite sua senha"
          />
        </div>

        <button type="submit" className={styles.botaoLogin}>
          Entrar
        </button>
      </form>

      <p className={styles.textoCadastro}>
        Ainda não possui uma conta?{' '}
        <Link to="/criar-conta" className={styles.linkCriarConta}>
          Criar conta
        </Link>
      </p>
    </div>
  );
}

export default Login;
