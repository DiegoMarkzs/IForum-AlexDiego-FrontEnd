import { useNavigate } from 'react-router-dom'; 
import jsPDF from 'jspdf';
import styles from './Header.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';

function Header({ onListarDenuncias }) {
  const navigate = useNavigate();

  const gerarPDF = async () => {
    try {
      const response = await fetch('http://localhost:8080/relatos/estatisticas');
      const stats = await response.json();

      const doc = new jsPDF();
      doc.setFontSize(16);
      doc.text("Relatório de Relatos - IFórum", 20, 20);
      doc.setFontSize(12);

      doc.text(`Total de relatos: ${stats.totalRelatos}`, 20, 40);
      doc.text(`Relatos pendentes: ${stats.pendentes}`, 20, 50);
      doc.text(`Relatos aceitos: ${stats.aceitos}`, 20, 60);
      doc.text(`Relatos recusados: ${stats.recusados}`, 20, 70);
      doc.text(`Sugestões feitas: ${stats.sugestoes}`, 20, 80);
      doc.text(`Denúncias feitas: ${stats.denuncias}`, 20, 90);

      doc.save("relatorio-relatos.pdf");
    } catch (error) {
      console.error("Erro ao gerar PDF:", error);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    navigate('/');
  };

  const irParaPerfil = () => {
    navigate('/editar-perfil');
  };

  return (
    <header className={styles.header}>
      <div className={styles.logoContainer}>
        <h2 className={styles.logo}>
          <span className={styles.logoVerde}>IF</span>orum
        </h2>
        <button
          className={styles.iconePerfilButton}
          onClick={irParaPerfil}
          aria-label="Perfil"
          type="button"
          title="Editar Perfil"
        >
          <FontAwesomeIcon icon={faUser} className={styles.iconePerfil} />
        </button>
      </div>
      <nav className={styles.navegacao}>
        <a href="#" onClick={(e) => { e.preventDefault(); onListarDenuncias(); }}>Listar relatos</a>
        <a href="#" onClick={(e) => { e.preventDefault(); gerarPDF(); }}>Gerar PDF</a>
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
