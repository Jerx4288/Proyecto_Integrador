function mostrarCarrito(event) {
    event.preventDefault();

    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const carritoTableBody = document.querySelector('#lista-carrito tbody');
    const contenidoCarrito = document.getElementById('contenido_carrito');

    // Limpiar la tabla
    carritoTableBody.innerHTML = '';

    // Si el carrito está vacío, ocultar la tabla
    if (carrito.length === 0) {
        contenidoCarrito.style.display = 'none';
        return;
    }

    contenidoCarrito.style.display = 'block';

    // Variable para calcular el total del carrito
    let precioTotalCarrito = 0;

    // Iterar sobre los productos del carrito y agregar una fila a la tabla
    carrito.forEach(producto => {
        const fila = document.createElement('tr');

        // Imagen
        const imagenCell = document.createElement('td');
        const img = document.createElement('img');
        img.src = producto.imagen || 'ruta/default/imagen.jpg'; // Imagen predeterminada si no existe
        img.alt = producto.nombre;
        img.style.width = '45px';
        imagenCell.appendChild(img);

        // Nombre
        const nombreCell = document.createElement('td');
        nombreCell.textContent = producto.nombre;

        // Precio
        const precioCell = document.createElement('td');
        precioCell.textContent = `S/${producto.precioTotal}`;

        // Cantidad
        const cantidadCell = document.createElement('td');
        cantidadCell.textContent = producto.cantidad || 1;

        // Botón para eliminar producto
        const eliminarCell = document.createElement('td');
        const eliminarButton = document.createElement('button');
        eliminarButton.textContent = 'Eliminar';
        eliminarButton.classList.add('btn-eliminar');
        eliminarButton.onclick = () => eliminarProductoDelCarrito(producto.id);
        eliminarCell.appendChild(eliminarButton);

        // Agregar las celdas a la fila
        fila.appendChild(imagenCell);
        fila.appendChild(nombreCell);
        fila.appendChild(precioCell);
        fila.appendChild(cantidadCell);
        fila.appendChild(eliminarCell);

        // Agregar la fila a la tabla
        carritoTableBody.appendChild(fila);

        // Sumar el precio total del carrito
        precioTotalCarrito += producto.precioTotal * (producto.cantidad || 1);
    });

    // Mostrar el total en la tabla
    const totalRow = document.querySelector('#total-carrito');
    if (!totalRow) {
        const totalFila = document.createElement('tr');
        totalFila.id = 'total-carrito';
        totalFila.innerHTML = `  
            <td colspan="4">Total</td>
            <td>S/${precioTotalCarrito.toFixed(2)}</td>
        `;
        carritoTableBody.appendChild(totalFila);
    } else {
        totalRow.children[1].textContent = `S/${precioTotalCarrito.toFixed(2)}`;
    }

    // Verificar si el botón "Ver carrito completo" ya existe antes de agregarlo
    const botonExistente = document.querySelector('.btn-ver-carrito');
    if (!botonExistente) {
        // Agregar el botón "Ver carrito completo"
        const verCarritoButton = document.createElement('button');
        verCarritoButton.textContent = 'Ver carrito completo';
        verCarritoButton.classList.add('btn-ver-carrito');
        verCarritoButton.onclick = () => {
            enviarDatosCarrito(carrito); // Llamar a la función para enviar el carrito al servidor
        };

        // Agregar el botón al final del contenido del carrito
        const contenidoCarritoFooter = document.createElement('div');
        contenidoCarritoFooter.classList.add('footer-carrito');
        contenidoCarritoFooter.appendChild(verCarritoButton);

        contenidoCarrito.appendChild(contenidoCarritoFooter);
    }
}




function toggleCarrito(event) {
    event.preventDefault(); 
    const contenidoCarrito = document.getElementById("contenido_carrito");

    if (contenidoCarrito.style.display === "none" || !contenidoCarrito.style.display) {
        contenidoCarrito.style.display = "block";
        mostrarCarrito(event);  // Actualiza el carrito al mostrarse
        document.addEventListener("click", closeOnClickOutside);
    } else {
        contenidoCarrito.style.display = "none";
        document.removeEventListener("click", closeOnClickOutside);
    }
}



function closeOnClickOutside(event) {
    const contenidoCarrito = document.getElementById("contenido_carrito");
    const carrito = document.getElementById("carrito");

    if (!contenidoCarrito.contains(event.target) && !carrito.contains(event.target)) {
        contenidoCarrito.style.display = "none";
        document.removeEventListener("click", closeOnClickOutside);
    }
}   



function eliminarProductoDelCarrito(index) {
    // Recuperar el carrito del localStorage
    let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

    // Eliminar el producto en el índice correspondiente
    carrito.splice(index, 1);

    // Guardar el carrito actualizado en el localStorage
    localStorage.setItem('carrito', JSON.stringify(carrito));

    // Actualizar el carrito visible
    mostrarCarrito(new Event('click'));
}

function vaciarCarrito() {
    // Elimina el carrito del localStorage
    localStorage.removeItem('carrito');

    // Opcional: Actualiza la interfaz de usuario para reflejar que el carrito está vacío
    console.log("El carrito ha sido vaciado.");
    // Aquí puedes actualizar el DOM o mostrar un mensaje al usuario, si lo deseas.
    document.getElementById('mensaje-carrito').textContent = "El carrito está vacío.";
}
function enviarDatosCarrito(carrito) {
    console.log('Carrito antes de enviar:', carrito);
    fetch('/boleta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ productos: carrito }) // Enviamos los productos como JSON
    })
    .then(response => {
        // Si la respuesta es exitosa, redirigir al usuario a /boleta
        if (response.ok) {
            window.location.href = '/boleta';  // Realizar la redirección manual
        } else {
            console.error('Error al procesar los datos del carrito.');
        }
    })
    .catch(error => {
        console.error('Error al enviar los datos del carrito:', error);
    });
}

