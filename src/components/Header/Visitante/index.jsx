import { useNavigate } from 'react-router-dom';
import styles from './Header.module.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

function Header({ onListarDenuncias, onFazerRelato }) {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate('/login');
  };

  return (
    <header className={styles.header}>
      <div className={styles.logoContainer}>
        <h2 className={styles.logo}>
          <span className={styles.logoVerde}>IF</span>orum
         
        </h2>
  
       
      </div>
      <nav className={styles.navegacao}>
        <a href="#" onClick={onListarDenuncias}>Listar relatos</a>
        <a href="#" onClick={onFazerRelato}>Fazer relato</a>
        <button 
          className={styles.botaoFazerLoginPopup} 
          onClick={handleLoginClick}
          type="button"
        >
          Fazer Login
        </button>
      </nav>
    </header>
  );
}

export default Header;
