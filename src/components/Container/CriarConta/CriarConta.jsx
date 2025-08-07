import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styles from './CriarConta.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse } from '@fortawesome/free-solid-svg-icons';

function CriarConta() {
  const [email, setEmail] = useState('');
  const [nome, setNome] = useState('');
  const [sobrenome, setSobrenome] = useState('');
  const [nascimento, setNascimento] = useState('');
  const [senha, setSenha] = useState('');
  const [confirmarSenha, setConfirmarSenha] = useState('');

  const [tipoUsuario, setTipoUsuario] = useState('ALUNO');
  const [curso, setCurso] = useState('');
  const [setor, setSetor] = useState('');
  const [temUsuarios, setTemUsuarios] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://localhost:8080/api')
      .then(res => res.json())
      .then(data => {
        if (Array.isArray(data) && data.length === 0) {
          setTemUsuarios(false);
          setTipoUsuario('COORDENADOR');
        } else {
          setTemUsuarios(true);
          setTipoUsuario('ALUNO');
        }
      })
      .catch(err => {
        console.error('Erro ao buscar usuários:', err);
        setTemUsuarios(true);
      });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (senha !== confirmarSenha) {
      alert('As senhas precisam ser iguais rapa!.');
      return;
    }

    const usuario = {
      email,
      nome,
      sobrenome,
      nascimento,
      senha,
      tipoUsuario,
      curso: tipoUsuario === 'ALUNO' ? curso : undefined,
      setor: tipoUsuario === 'FUNCIONARIO' ? setor : undefined,
    };

    fetch('http://localhost:8080/api/save', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(usuario)
    })
      .then(res => {
        if (!res.ok) throw new Error('Erro ao criar conta');
        return res.json();
      })
      .then(() => {
        alert(`Conta criada, bem vindo ${nome} ${sobrenome}!`);
        navigate('/login');
      })
      .catch(err => {
        console.error(err);
        alert('Falha ao criar a conta.');
      });
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

      <h2 className={styles.title}>Criar Conta</h2>

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

        <div className={styles.grid2}>
          <div className={styles.formGroup}>
            <label htmlFor="nome">Nome:</label>
            <input
              id="nome"
              type="text"
              value={nome}
              onChange={e => setNome(e.target.value)}
              required
              placeholder="Digite seu nome"
            />
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="sobrenome">Sobrenome:</label>
            <input
              id="sobrenome"
              type="text"
              value={sobrenome}
              onChange={e => setSobrenome(e.target.value)}
              required
              placeholder="Digite seu sobrenome"
            />
          </div>
        </div>

        {temUsuarios ? (
          <div className={styles.formGroup}>
            <label htmlFor="tipoUsuario">Tipo de usuário:</label>
            <select
              id="tipoUsuario"
              value={tipoUsuario}
              onChange={e => setTipoUsuario(e.target.value)}
              required
            >
              <option value="ALUNO">Aluno</option>
              <option value="FUNCIONARIO">Funcionário</option>
              {/* O coordenador não ta aqui porque ele ja é selecionado 
                  automaticamente caso não tenha nenhum usuario no sistema */}
            </select>
          </div>
        ) : (
          <div className={styles.formGroup}>
            <label>Tipo de usuário:</label>
            <input type="text" value="Coordenador" readOnly />
          </div>
        )}

        {/* Campos específicos do aluno */}
        {tipoUsuario === 'ALUNO' && (
          <div className={styles.formGroup}>
            <label htmlFor="curso">Curso:</label>
            <input
              id="curso"
              type="text"
              value={curso}
              onChange={e => setCurso(e.target.value)}
              required
              placeholder="Digite seu curso"
            />
          </div>
        )}

        {/* Campos específicos do funcionario */}
        {tipoUsuario === 'FUNCIONARIO' && (
          <div className={styles.formGroup}>
            <label htmlFor="setor">Setor:</label>
            <input
              id="setor"
              type="text"
              value={setor}
              onChange={e => setSetor(e.target.value)}
              required
              placeholder="Digite seu setor"
            />
          </div>
        )}

        <div className={styles.grid2}>
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

          <div className={styles.formGroup}>
            <label htmlFor="confirmarSenha">Confirmar Senha:</label>
            <input
              id="confirmarSenha"
              type="password"
              value={confirmarSenha}
              onChange={e => setConfirmarSenha(e.target.value)}
              required
              placeholder="Confirme sua senha"
            />
          </div>
        </div>

        <div className={`${styles.formGroup} ${styles.nascimentoGroup}`}>
          <label htmlFor="nascimento">Data de Nascimento:</label>
          <input
            id="nascimento"
            type="date"
            value={nascimento}
            onChange={e => setNascimento(e.target.value)}
            required
          />
        </div>

        <button type="submit" className={styles.botaoLogin}>
          Criar Conta
        </button>
      </form>

      <p className={styles.textoCadastro}>
        Já possui uma conta?{' '}
        <Link to="/login" className={styles.linkCriarConta}>
          Fazer login
        </Link>
      </p>
    </div>
  );
}

export default CriarConta;
