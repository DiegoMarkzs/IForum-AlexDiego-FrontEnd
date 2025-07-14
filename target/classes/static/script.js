const botaoListarDenuncias = document.getElementById('listarDenuncias');
const iconClose = document.querySelector('.icon-close');
const wrapper = document.querySelector('.wrapper');

botaoListarDenuncias.addEventListener('click', ()=> {
   
    wrapper.classList.add('active-popup')
    
})

iconClose.addEventListener('click', ()=> {
   
    wrapper.classList.remove('active-popup')
    
})



