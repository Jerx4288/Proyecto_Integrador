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

document.addEventListener("DOMContentLoaded", function() {

    // 1. Mostrar/Ocultar el área de dedicatoria
    const dedicatoriaRadios = document.querySelectorAll('input[id^="dedicatoria_"]'); // Selecciona todos los radios de dedicatoria por su id
    const dedicatoriaArea = document.getElementById('dedicatoria_area'); // Selecciona el área de dedicatoria

    dedicatoriaRadios.forEach(radio => {
        radio.addEventListener('change', function() {
            if (this.value === 'si') {
                dedicatoriaArea.style.display = 'block'; // Muestra la dedicatoria
            } else {
                dedicatoriaArea.style.display = 'none'; // Oculta la dedicatoria
            }
        });
    });

    // 2. Mostrar/Ocultar la dirección de entrega
    const entregaRadios = document.querySelectorAll('input[id^="entrega_"]'); // Selecciona todos los radios de entrega por su id
    const direccionContainer = document.getElementById('direccion_container'); // Selecciona el contenedor de dirección

    entregaRadios.forEach(radio => {
        radio.addEventListener('change', function() {
            if (this.value === 'delivery') {
                direccionContainer.style.display = 'block'; // Muestra la dirección
            } else {
                direccionContainer.style.display = 'none'; // Oculta la dirección
            }
        });
    });

    // 3. (Opcional) Recoger las IDs de las tortas seleccionadas
    // Si necesitas manejar alguna lógica para recolectar las IDs de las tortas seleccionadas
    const tortasIds = [];

    // Recolectar las IDs de las tortas de la forma que prefieras.
    // Aquí te doy un ejemplo donde recolectas las IDs de los inputs ocultos
    const tortasClasicasIds = document.querySelectorAll('input[id^="id_torta_clasica_"]');
    tortasClasicasIds.forEach(input => {
        tortasIds.push(input.value); // Recolecta los valores de las tortas clásicas
    });

    const tortasEspecialesIds = document.querySelectorAll('input[id^="id_torta_especial_"]');
    tortasEspecialesIds.forEach(input => {
        tortasIds.push(input.value); // Recolecta los valores de las tortas especiales
    });

    console.log('IDs seleccionadas:', tortasIds); // Imprime las IDs en la consola si necesitas verificarlas

});

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






document.querySelectorAll('input[name="metodo_pago"]').forEach(function (radio) {
    radio.addEventListener('change', function () {
        var metodoPago = document.querySelector('input[name="metodo_pago"]:checked').value;

        if (metodoPago === 'tarjeta') {
            // Mostrar el formulario de pago con tarjeta
            document.getElementById('tarjeta-info').style.display = 'block';
        } else {
            // Ocultar el formulario de pago con tarjeta
            document.getElementById('tarjeta-info').style.display = 'none';
        }
    });
});


// Asegurarse de que los campos de tarjeta se mantengan cuando se haga la finalización
document.getElementById('form-finalizar-compra').addEventListener('submit', function () {
    if (document.querySelector('input[name="metodo_pago"]:checked').value === 'tarjeta') {
        // Guardar los datos de tarjeta si el pago es con tarjeta
        var tarjetaNumero = document.getElementById('numero_tarjeta').value;
        var tarjetaFecha = document.getElementById('fecha_expiracion').value;
        var tarjetaCVV = document.getElementById('cvv').value;

        document.getElementById('metodo_pago').value = 'tarjeta';
        document.getElementById('numero_tarjeta_input').value = tarjetaNumero;
        document.getElementById('fecha_expiracion_input').value = tarjetaFecha;
        document.getElementById('cvv_input').value = tarjetaCVV;
    }
});
