document.querySelectorAll('input[name="dedicatoria"]').forEach(function(radio) {
    radio.addEventListener('change', function() {
        const dedicatoriaArea = document.getElementById('dedicatoria_area');
        const dedicatoriaInput = document.getElementById('dedicatoria_input');
        
        if (document.getElementById('dedicatoria_si').checked) {
            dedicatoriaArea.style.display = 'flex';  // Mostrar el textarea
            dedicatoriaInput.value = document.getElementById('dedicatoria_texto').value;
        } else {
            dedicatoriaArea.style.display = 'none';  // Ocultar el textarea
            dedicatoriaInput.value = ''; // Limpiar el valor del campo
        }
    });
});

// Asegúrate de que el estado inicial esté bien configurado al cargar la página
if (document.getElementById('dedicatoria_si').checked) {
    document.getElementById('dedicatoria_area').style.display = 'block';
} else {
    document.getElementById('dedicatoria_area').style.display = 'none';
}


const textarea = document.getElementById('dedicatoria_texto');
const contador = document.getElementById('caracteres_contador');

textarea.addEventListener('input', function() {
    textarea.value = textarea.value.replace(/[^a-zA-Z0-9\s]/g, '');

    const caracteresRestantes = 150 - textarea.value.length;
    contador.textContent = `${caracteresRestantes} caracteres restantes`;
});




//Entrega 
document.querySelectorAll('input[name="entrega"]').forEach(function (radio) {
    radio.addEventListener('change', function () {
        var direccionContainer = document.querySelector('.direccion-container');
        
        if (this.value === 'delivery') {
            direccionContainer.style.display = 'block';
        } else {
            direccionContainer.style.display = 'none';
        }
    });
});



document.getElementById('finalizar-compra').addEventListener('click', function(e) {
    e.preventDefault(); 


    let metodoPago = document.querySelector('input[name="metodo_pago"]:checked')?.value;
    let tipoEntrega = document.querySelector('input[name="entrega"]:checked')?.value;
    let direccion = document.getElementById('direccion').value;
    let productos = [];

    let productosData = [];
    document.querySelectorAll('.detalle-torta').forEach(function(torta) {
        let nombre = torta.querySelector('span[name="nombre_tc"]').textContent;
        let precio = torta.querySelector('span[name="precio_tc"]').textContent;
        productosData.push({ nombre, precio });
    });

    // Asignar los valores a los campos hidden
    document.getElementById('productos').value = JSON.stringify(productosData);
    document.getElementById('metodo_pago').value = metodoPago;
    document.getElementById('entrega').value = tipoEntrega;
    document.getElementById('direccion_input').value = direccion;
    document.getElementById('total_input').value = document.getElementById('totalCompra').textContent;

    // Enviar el formulario
    document.getElementById('form-finalizar-compra').submit();
    
});
