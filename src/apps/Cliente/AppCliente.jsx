import Header from '../../components/Header/Cliente';
import TabelaFazerRelato from '../../components/Container/Cliente/FazerRelato-CLIENTE/TabelaFazerRelato';
import ListarRelatos from '../../components/Container/Cliente/ListarRelatos-CLIENTE/TabelaListarRelatos';
import RelatoAnalise from '../../components/Container/Cliente/AnalisarRelato-CLIENTE/RelatoAnalise';

import { useState } from 'react';

function AppCliente() {
  const [mostrarListarRelatos, setMostrarListarRelatos] = useState(false);
  const [mostrarFormulario, setMostrarFormulario] = useState(false);
  const [relatoSelecionado, setRelatoSelecionado] = useState(null);

  const abrirListarRelatos = () => {
    setMostrarListarRelatos(true);
    setMostrarFormulario(false);
    setRelatoSelecionado(null);
  };

  const abrirFormulario = () => {
    setMostrarFormulario(true);
    setMostrarListarRelatos(false);
    setRelatoSelecionado(null);
  };

  const fecharTodos = () => {
    setMostrarListarRelatos(false);
    setMostrarFormulario(false);
    setRelatoSelecionado(null);
  };

  const handleRelatoClick = (relato) => {
    setRelatoSelecionado(relato);
    setMostrarFormulario(false);
    setMostrarListarRelatos(false);
  };

  return (
    <>
      <Header 
        onListarDenuncias={abrirListarRelatos} 
        onFazerRelato={abrirFormulario} 
      />

      {mostrarListarRelatos && (
        <ListarRelatos 
          onClose={fecharTodos} 
          onRelatoClick={handleRelatoClick} 
        />
      )}

      {mostrarFormulario && (
        <TabelaFazerRelato 
          onClose={fecharTodos} 
        />
      )}

      {relatoSelecionado && (
        <RelatoAnalise 
          relato={relatoSelecionado} 
          onClose={fecharTodos} 
        />
      )}
    </>
  );
}

export default AppCliente;
