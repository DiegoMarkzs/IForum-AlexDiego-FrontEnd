import styles from './Header.module.css';

function Header({ onListarDenuncias }) {
  return (
    <header className={styles.header}>
      <h2 className={styles.logo}>IForum</h2>
      <nav className={styles.navegacao}>
        <a href="#">Home</a>
        <a href="#" onClick={onListarDenuncias}>Listar denúncias</a>
        <button className={styles.botaoSairContaPopup}>Sair da conta</button>
      </nav>
    </header>
  );
}

export default Header;
