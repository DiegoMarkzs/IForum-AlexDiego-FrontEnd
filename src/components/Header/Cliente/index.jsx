import { useNavigate } from 'react-router-dom';
import styles from './Header.module.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

function Header({ onListarDenuncias, onFazerRelato }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    navigate('/');
  };

  const handlePerfilClick = () => {
    navigate('/editar-perfil');
  };

  return (
    <header className={styles.header}>
      <div className={styles.logoContainer}>
        <h2 className={styles.logo}>
          <span className={styles.logoVerde}>IF</span>orum
        </h2>
        <i
          className={`fa-solid fa-user ${styles.iconePerfil}`}
          onClick={handlePerfilClick}
          style={{ cursor: 'pointer' }}
        ></i>
      </div>
      <nav className={styles.navegacao}>
        <a href="#" onClick={onListarDenuncias}>Listar relatos</a>
        <a href="#" onClick={onFazerRelato}>Fazer relato</a>
        <button
          className={styles.botaoSairContaPopup}
          onClick={handleLogout}
          type="button"
        >
          Sair da conta
        </button>
      </nav>
    </header>
  );
}

export default Header;
