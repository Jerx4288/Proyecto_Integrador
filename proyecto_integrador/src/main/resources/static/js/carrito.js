function mostrarCarrito(event) {
    event.preventDefault();
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const carritoTableBody = document.querySelector('#lista-carrito tbody');
    const contenidoCarrito = document.getElementById('contenido_carrito');

    carritoTableBody.innerHTML = '';

    if (carrito.length === 0) {
        contenidoCarrito.style.display = 'none';
        return;
    }

    contenidoCarrito.style.display = 'block';

    let precioTotalCarrito = 0;

    carrito.forEach((producto, index) => {
        if (!producto.nombre || !producto.precioTotal) {
            console.error('Producto mal definido:', producto);
            return;
        }

        const fila = document.createElement('tr');

        const imagenCell = document.createElement('td');
        const img = document.createElement('img');
        img.src = producto.imagen || 'ruta/default/imagen.jpg';
        img.alt = producto.nombre;
        img.style.width = '45px';
        imagenCell.appendChild(img);

        const nombreCell = document.createElement('td');
        nombreCell.textContent = producto.nombre;

        const precioCell = document.createElement('td');
        precioCell.textContent = `S/${producto.precioTotal.toFixed(2)}`;

        const cantidadCell = document.createElement('td');
        cantidadCell.textContent = producto.cantidad || 1;

        const eliminarCell = document.createElement('td');
        const eliminarButton = document.createElement('button');
        eliminarButton.textContent = 'Eliminar';
        eliminarButton.classList.add('btn-eliminar');

        eliminarButton.onclick = () => eliminarProductoDelCarrito(index);

        eliminarCell.appendChild(eliminarButton);

        fila.appendChild(imagenCell);
        fila.appendChild(nombreCell);
        fila.appendChild(precioCell);
        fila.appendChild(cantidadCell);
        fila.appendChild(eliminarCell);

        carritoTableBody.appendChild(fila);

        precioTotalCarrito += producto.precioTotal;
    });

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

    const botonExistente = document.querySelector('.btn-ver-carrito');
    if (!botonExistente) {
        const verCarritoButton = document.createElement('button');
        verCarritoButton.textContent = 'Ver carrito completo';
        verCarritoButton.classList.add('btn-ver-carrito');
        verCarritoButton.onclick = () => {
            enviarDatosCarrito(carrito); 
        };

        const contenidoCarritoFooter = document.createElement('div');
        contenidoCarritoFooter.classList.add('footer-carrito');
        contenidoCarritoFooter.appendChild(verCarritoButton);

        contenidoCarrito.appendChild(contenidoCarritoFooter);
    }
}



function toggleCarrito(event) {
    event.preventDefault(); 
    const contenidoCarrito = document.getElementById("contenido_carrito");

    if (contenidoCarrito.style.display === "none" || !contenidoCarrito.style.display) 
    {
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
    let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

    if (index >= 0 && index < carrito.length) {
        carrito.splice(index, 1); 
        localStorage.setItem('carrito', JSON.stringify(carrito)); 
        mostrarCarrito(new Event('click')); 
    } else {
        console.error('Índice fuera de rango. No se puede eliminar el producto.');
    }
}




function enviarDatosCarrito(carrito) {
    console.log('Carrito antes de enviar:', carrito);
    fetch('/boleta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ productos: carrito }) 
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/boleta'; 
        } else {
            console.error('Error al procesar los datos del carrito.');
        }
    })
    .catch(error => {
        console.error('Error al enviar los datos del carrito:', error);
    });
}


