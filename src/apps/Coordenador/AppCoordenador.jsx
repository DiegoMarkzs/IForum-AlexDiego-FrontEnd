import Header from '../../components/Header/Coordenador';
import ListarRelatos from '../../components/Container/Coordenador/ListarRelatos-COORDENADOR/TabelaListarRelatos';
import RelatoAnalise from '../../components/Container/Coordenador/AnalisarRelato-COORDENADOR/RelatoAnalise';

import { useState } from 'react';

function AppCoordenador() {
  const [mostrarListarRelatos, setMostrarListarRelatos] = useState(false);
  const [relatoSelecionado, setRelatoSelecionado] = useState(null);

  const abrirListarRelatos = () => {
    setMostrarListarRelatos(true);
    setRelatoSelecionado(null);
  };

  const fecharTodos = () => {
    setMostrarListarRelatos(false);
    setRelatoSelecionado(null);
  };

  const handleRelatoClick = (relato) => {
    setRelatoSelecionado(relato);
    setMostrarListarRelatos(false);
  };

  return (
    <>
      <Header 
        onListarDenuncias={abrirListarRelatos} 
        onFazerRelato={() => window.alert('Gerar PDF')} 
      />

      {mostrarListarRelatos && (
        <ListarRelatos 
          onClose={fecharTodos} 
          onRelatoClick={handleRelatoClick} 
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

export default AppCoordenador;
