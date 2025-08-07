import Header from '../../components/Header/Visitante';
import TabelaFazerRelato from '../../components/Container/Visitante/FazerRelato-VISITANTE/TabelaFazerRelato';

import { useState } from 'react';

function AppVisitante() {
 
  const [mostrarFormulario, setFazerRelato] = useState(false);


 

  const abrirListarRelatos = () => {
  
    alert("Faça login para realizar essa ação!");
   
  };

  const abrirFazerRelato = () => {
    setFazerRelato(true);
  };

  const fecharFazerRelato = () => {
    setFazerRelato(false);
  };

 
  return (
    <>
      <Header onListarDenuncias={abrirListarRelatos} onFazerRelato={abrirFazerRelato} />     
      {mostrarFormulario && <TabelaFazerRelato onClose={fecharFazerRelato} />}

    </>
  );
}

export default AppVisitante;
