document.querySelectorAll('input[name="dedicatoria"]').forEach(function(radio) {
    radio.addEventListener('change', function() {
        const dedicatoriaArea = document.getElementById('dedicatoria_area');
        const dedicatoriaInput = document.getElementById('dedicatoria_input');
        
        if (document.getElementById('dedicatoria_si').checked) {
            dedicatoriaArea.style.display = 'flex';  
            dedicatoriaInput.value = document.getElementById('dedicatoria_texto').value;
        } else {
            dedicatoriaArea.style.display = 'none'; 
            dedicatoriaInput.value = ''; 
        }
    });
});

if (document.getElementById('dedicatoria_si').checked) {
    document.getElementById('dedicatoria_area').style.display = 'block';
} else {
    document.getElementById('dedicatoria_area').style.display = 'none';
}

document.addEventListener("DOMContentLoaded", function() {
    const dedicatoriaRadios = document.querySelectorAll('input[id^="dedicatoria_"]'); 
    const dedicatoriaArea = document.getElementById('dedicatoria_area'); 

    dedicatoriaRadios.forEach(radio => {
        radio.addEventListener('change', function() {
            if (this.value === 'si') {
                dedicatoriaArea.style.display = 'flex'; 
            } else {
                dedicatoriaArea.style.display = 'none'; 
            }
        });
    });

    const entregaRadios = document.querySelectorAll('input[id^="entrega_"]')
    const direccionContainer = document.getElementById('direccion_container'); 

    entregaRadios.forEach(radio => {
        radio.addEventListener('change', function() {
            if (this.value === 'delivery') {
                direccionContainer.style.display = 'block'; 
            } else {
                direccionContainer.style.display = 'none';
            }
        });
    });


    const tortasIds = [];

    const tortasClasicasIds = document.querySelectorAll('input[id^="id_torta_clasica_"]');
    tortasClasicasIds.forEach(input => {
        tortasIds.push(input.value); 
    });

    const tortasEspecialesIds = document.querySelectorAll('input[id^="id_torta_especial_"]');
    tortasEspecialesIds.forEach(input => {
        tortasIds.push(input.value); 
    });

    console.log('IDs seleccionadas:', tortasIds); 

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






document.querySelectorAll('input[name="metodoPago"]').forEach(function (radio) {
    radio.addEventListener('change', function () {
        var metodoPago = document.querySelector('input[name="metodoPago"]:checked').value;

        if (metodoPago === 'tarjeta') {
            document.getElementById('tarjeta-info').style.display = 'block';
        } else {
            document.getElementById('tarjeta-info').style.display = 'none';
        }
    });
});

function cerrarTarjeta() {
    document.getElementById('tarjeta-info').style.display = 'none';
}



document.getElementById('form-finalizar-compra').addEventListener('submit', function (event) {
    event.preventDefault();  

    if (document.querySelector('input[name="metodo_pago"]:checked').value === 'tarjeta') {
        var tarjetaNumero = document.getElementById('numero-tarjeta').value;
        var tarjetaFecha = document.getElementById('fecha-expiracion').value;
        var tarjetaCVV = document.getElementById('cvv').value;

        document.getElementById('metodo_pago').value = 'tarjeta';
        document.getElementById('numero_tarjeta_input').value = tarjetaNumero;
        document.getElementById('fecha_expiracion_input').value = tarjetaFecha;
        document.getElementById('cvv_input').value = tarjetaCVV;


    }
});

