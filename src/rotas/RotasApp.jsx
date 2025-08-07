import { BrowserRouter, Routes, Route } from 'react-router-dom';
import AppCliente from '../apps/Cliente/AppCliente';
import AppCoordenador from '../apps/Coordenador/AppCoordenador';
import AppVisitante from '../apps/Visitante/AppVisitante';
import AppLogin from '../apps/Login/AppLogin';
import AppCriarConta from '../apps/CriarConta/AppCriarConta';
import AppEditarPerfil from '../apps/EditarPerfil/AppEditarPerfil';

function RotasApp() {
  return (
    <BrowserRouter basename="/DiegoMarkzs/IForum">
      <Routes>
        <Route path="/" element={<AppVisitante />} />
        <Route path="/cliente" element={<AppCliente />} />
        <Route path="/coordenador" element={<AppCoordenador />} />
        <Route path="/login" element={<AppLogin />} />
        <Route path="/criar-conta" element={<AppCriarConta />} />
        <Route path="/editar-perfil" element={<AppEditarPerfil />} />
        
      </Routes>
    </BrowserRouter>
  );
}

export default RotasApp;
