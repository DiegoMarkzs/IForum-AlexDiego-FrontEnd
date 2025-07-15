import './App.css';
import Header from './components/header';
import Container from './components/Container/TabelaListarRelatos';
import TabelaFazerRelato from './components/Container/TabelaFazerRelato';
import RelatoAnalise from './components/Container/RelatoAnalise';
import { useState } from 'react';

function App() {
  const [mostrarContainer, setMostrarContainer] = useState(false);
  const [mostrarFormulario, setMostrarFormulario] = useState(false);
  const [relatoSelecionado, setRelatoSelecionado] = useState(null);

  const abrirContainer = () => {
    setMostrarContainer(true);
    setMostrarFormulario(false);
    setRelatoSelecionado(null);
  };

  const abrirFormulario = () => {
    setMostrarFormulario(true);
    setMostrarContainer(false);
    setRelatoSelecionado(null);
  };

  const fecharTodos = () => {
    setMostrarContainer(false);
    setMostrarFormulario(false);
    setRelatoSelecionado(null);
  };

  const handleRelatoClick = (relato) => {
    setRelatoSelecionado(relato);
    setMostrarContainer(false);
    setMostrarFormulario(false);
  };

  return (
    <>
      <Header onListarDenuncias={abrirContainer} onFazerRelato={abrirFormulario} />

      {mostrarContainer && (
        <Container onClose={fecharTodos} onRelatoClick={handleRelatoClick} />
      )}

      {mostrarFormulario && <TabelaFazerRelato onClose={fecharTodos} />}

      {relatoSelecionado && (
        <RelatoAnalise
          relato={relatoSelecionado}
          onClose={fecharTodos}
        />
      )}
    </>
  );
}

export default App;
