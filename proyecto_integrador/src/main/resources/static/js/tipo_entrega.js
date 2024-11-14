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

    let fechaCompra = new Date();

    // Formatear la fecha como 'yyyy-MM-dd HH:mm:ss'
    let formattedDate = fechaCompra.getFullYear() + '-' + 
                        (fechaCompra.getMonth() + 1).toString().padStart(2, '0') + '-' + 
                        fechaCompra.getDate().toString().padStart(2, '0') + ' ' + 
                        fechaCompra.getHours().toString().padStart(2, '0') + ':' + 
                        fechaCompra.getMinutes().toString().padStart(2, '0') + ':' + 
                        fechaCompra.getSeconds().toString().padStart(2, '0');

    // Asignar el valor al campo hidden
    document.getElementById('fecha_compra').value = formattedDate;

    
    let metodoPago = document.querySelector('input[name="metodo_pago"]:checked')?.value;
    let tipoEntrega = document.querySelector('input[name="entrega"]:checked')?.value;
    let direccion = document.getElementById('direccion').value;
    let cantidadTotal = document.querySelector('.cantidades span').textContent;
    let productosData = [];
    let dedicatoria = document.querySelector('input[name="dedicatoria"]:checked')?.value === 'si' 
                        ? document.getElementById('dedicatoria_texto').value 
                        : 'no incluye'; 

    document.querySelectorAll('.detalle-torta').forEach(function(torta) {
        let id = torta.querySelector('span[name="id_tc"]').textContent; 
        productosData.push(id);
    });

    console.log("Productos: ", productosData);
    console.log("Método de Pago: ", metodoPago);
    console.log("Tipo de Entrega: ", tipoEntrega);
    console.log("Dirección: ", direccion);
    console.log("Total: ", document.getElementById('totalCompra').textContent);
    console.log("Cantidad Total: ", cantidadTotal);
    console.log("Dedicatoria: ", dedicatoria);

    // Asignar los valores a los campos hidden
    document.getElementById('productos').value = productosData.join(',');
    document.getElementById('metodo_pago').value = metodoPago;
    document.getElementById('entrega').value = tipoEntrega;
    document.getElementById('cantidad').value = cantidadTotal;
    document.getElementById('total_input').value = document.getElementById('totalCompra').textContent;
    document.getElementById('dedicatoria_input').value = dedicatoria;
    
    if (tipoEntrega === 'delivery') {
        document.getElementById('direccion_input').value = direccion;
    } else {
        document.getElementById('direccion_input').value = 'No'; // Asegurarse de que el campo esté vacío si no es delivery
    }

    document.getElementById('form-finalizar-compra').submit();
    
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

// Funcionalidad para cerrar el menú flotante
document.getElementById('cerrar-tarjeta').addEventListener('click', function () {
    document.getElementById('tarjeta-info').style.display = 'none';
});

document.getElementById('continuar-pago').addEventListener('click', function () {
    document.getElementById('tarjeta-info').style.display = 'none';
    document.body.classList.remove('modal-active'); // Eliminar la clase para restaurar el fondo
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
