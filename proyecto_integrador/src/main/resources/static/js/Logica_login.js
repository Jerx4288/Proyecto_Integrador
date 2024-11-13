const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');
const showPasswordCheckbox = document.getElementById('showPassword');
const password1 = document.getElementById('password_registro');


const updateContainerAndTitle = (isRegister) => {
    container.classList.toggle("active", isRegister);
    document.title = isRegister ? "Pabluki's Bakery | Crear Cuenta" : "Pabluki's Bakery | Iniciar Sesión";
};

registerBtn.addEventListener('click', () => updateContainerAndTitle(true));
loginBtn.addEventListener('click', () => updateContainerAndTitle(false));

showPasswordCheckbox.addEventListener('change', () => {
    const type = showPasswordCheckbox.checked ? 'text' : 'password';
    password1.type = type;
});


