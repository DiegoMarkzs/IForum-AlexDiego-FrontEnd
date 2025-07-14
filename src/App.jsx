import './App.css';
import Header from './components/header';
import Container from './components/Container';
import { useState } from 'react';

function App() {
  const [mostrarContainer, setMostrarContainer] = useState(false);

  const abrirContainer = () => setMostrarContainer(true);

  return (
    <>
      <Header onListarDenuncias={abrirContainer} />
      {mostrarContainer && <Container onClose={() => setMostrarContainer(false)} />}
    </>
  );
}

export default App;
