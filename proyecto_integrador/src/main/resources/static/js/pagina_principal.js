
document.addEventListener('DOMContentLoaded', () => {

    let indice = 0;
    const diapositivas = document.querySelectorAll('.slide');
    const totalDiapo = diapositivas.length;
    let intervaloDiapo = setInterval(siguiente, 8000);

    function mostrar(index) {
        const offset = index * - 100;
        document.querySelector('.diapo').style.transform = `translateX(${offset}%)`;
    }

    function siguiente() {
        indice = (indice + 1) % totalDiapo;
        mostrar(indice);
    }

    function anterior() {
        indice = (indice - 1 + totalDiapo) % totalDiapo;
        mostrar(indice);
    }

    function resetear() {
        clearInterval(intervaloDiapo);
        intervaloDiapo = setInterval(siguiente, 3000);
    }

    document.querySelector('.atras').addEventListener('click', () => {
        anterior();
        resetear();
    });

    document.querySelector('.siguiente').addEventListener('click', () => {
        siguiente();
        resetear();
    });

    mostrar(indice);
});
