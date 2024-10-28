
 // Función para obtener parámetros de la URL
 function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

// Cargar datos del producto según el ID
const productoId = getQueryParam('id');
const productos = {
    torta_clasica_1: {
        nombre: "Carrot Cake",
        imagen: "/img/imagenes_tienda/carrot_cake_tienda.jpeg",
        descripcion: "Un pastel suave y esponjoso, hecho con zanahorias frescas y especias, cubierto con un delicioso glaseado de queso crema.",
        formulario: [
            { label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [

                    {value: '15 porciones', label: 'De 15 porciones', price: 160},
                    {value: '25 porciones', label: 'De 25 porciones', price: 250}
                ] 
            },
            {  label: "Redacte una breve dedicatoria:",
                type: "textarea",
                name: "dedicatoria",
                rows: 2,
                cols: 9 }
        ]
    },
    torta_clasica_2: {
        nombre: "Red Velvet",
        imagen: "/img/imagenes_tienda/red_velvet_tienda.jpeg",
        descripcion: "Deliciosa combinación de esponjosidad y suavidad, con su característico color rojo y la cremosa cobertura de queso crema.",
        formulario: [
            { label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [

                    {value: '15 porciones', label: 'De 15 porciones', price: 160},
                    {value: '25 porciones', label: 'De 25 porciones', price: 250}
                ] 
            },
            {  label: "Redacte una breve dedicatoria:",
                type: "textarea",
                name: "dedicatoria",
                rows: 2,
                cols: 9 }
        ]
    },
    torta_clasica_3: {
        nombre: "Tres Leches",
        imagen: "/img/imagenes_tienda/torta_tres_leches_tienda.jpeg",
        descripcion: "Bizcocho esponjoso empapado en una mezcla de leche condensada, evaporada y crema."
    },
    torta_clasica_4: {
        nombre: "Torta de Chocolate",
        imagen: "/img/imagenes_tienda/torta_chocolate_tienda.jpeg",
        descripcion: "Intensamente chocolatoso y suave. Cada bocado está lleno de un rico sabor a cacao, complementado con una cobertura cremosa que la hace irresistible"
    },
    torta_clasica_5: {
        nombre: "Torta de Café",
        imagen: "/img/imagenes_tienda/torta_cafe_tienda.jpeg",
        descripcion: "Suave y esponjosa, con un toque aromático de café que deleita el paladar. Perfecta para los amantes del café, con una cobertura cremosa y equilibrada."
    },
    torta_clasica_6: {
        nombre: "Torta Napolitana",
        imagen: "/img/imagenes_tienda/torta_napolitana_tienda.jpeg",
        descripcion: "Tres capas irresistibles de vainilla, chocolate y fresa en un solo postre. Esponjosa y llena de sabor, perfecta para quienes no pueden elegir solo uno."
    },
    torta_clasica_7: {
        nombre: "Blue Velvet",
        imagen: "/img/imagenes_tienda/blue_velvet_tienda.jpeg",
        descripcion: "Una sorprendente variación del clásico, esponjosa y de vibrante color azul. Con un sutil toque de cacao y cobertura de queso crema, es perfecta para cualquier ocasión especial."
    },
    torta_clasica_8: {
        nombre: "Selva Negra",
        imagen: "/img/imagenes_tienda/selva_negra_tienda.jpeg",
        descripcion: "Un delicioso bizcocho de chocolate negro, intercalado con crema batida y cerezas. Un postre indulgente que combina sabores intensos y frescura en cada bocado."
    },
    torta_clasica_9: {
        nombre: "Mill Hojas",
        imagen: "/img/imagenes_tienda/millhojas_torta_tienda.jpeg",
        descripcion: "Delicadas capas de masa crujiente, alternando con suave crema pastelera y un toque de dulce de leche. Cada bocado ofrece una textura ligera y un equilibrio perfecto entre lo crujiente y lo cremoso."
    },
    torta_especial_1:
    {
        nombre: "Torta de Halloween",
        imagen: "/img/imagenes_tienda/2_torta_especial.jpg",
        descripcion: "Ideal para celebrar un Halloween inolvidable, esta torta con temática espeluznante transformará tu mesa en un festín de sustos y sabores, encantando a grandes y pequeños con su decoración única y deliciosa."
    },
    torta_especial_2:
    {
        nombre: "Torta Safari",
        imagen: "/img/imagenes_tienda/1_torta_especial.jpg",
        descripcion: "Ideal para sorprender en el cumpleaños de los más pequeños, esta torta con temática safari lleva la diversión de la selva a tu mesa, creando una experiencia única y deliciosa que encantará a todos los niños."
    },
    torta_especial_3:
    {
        nombre: "Torta de Cumpleaños: Modelo 1",
        imagen: "/img/imagenes_tienda/3_torta_especial.jpg",
        descripcion: "Ideal para celebrar un cumpleaños con estilo, esta torta diseñada especialmente para hombres combina elegancia y sabor, convirtiendo cualquier ocasión en un momento inolvidable con su decoración sofisticada y su toque irresistible."
    },
    torta_especial_4:
    {
        nombre: "Torta de Cumpleaños: Modelo 2",
        imagen: "/img/imagenes_tienda/4_torta_especial.jpg",
        descripcion: "Perfecta para un cumpleaños memorable, esta torta para hombres destaca por su diseño moderno y audaz, ofreciendo un equilibrio entre estética y sabor que hará de la celebración un evento único y lleno de personalidad."
    },
    torta_especial_5:
    {
        nombre: "Torta de Cumpleaños: Modelo 3",
        imagen: "/img/imagenes_tienda/5_torta_especial.jpg",
        descripcion: "Ideal para un cumpleaños lleno de encanto, esta elegante torta para mujeres combina detalles delicados con un sabor exquisito, añadiendo un toque especial y femenino que hará de la celebración un momento inolvidable."
    },
    torta_especial_6:
    {
        nombre: "Torta de Cumpleaños: Modelo 4",
        imagen: "/img/imagenes_tienda/6_torta_especial.jpg",
        descripcion: "Perfecta para celebrar a una mujer especial, esta hermosa torta destaca por su estilo sofisticado y sabores irresistibles, aportando un toque de dulzura y elegancia que hará brillar cualquier cumpleaños."
    },
    torta_especial_7:
    {
        nombre: "Torta de Micky Mouse",
        imagen: "/img/imagenes_tienda/7_torta_especial.jpg",
        descripcion: "Una opción encantadora para los pequeños fans de Disney, esta torta inspirada en Mickey Mouse llena de color y alegría será el centro de atención en cualquier cumpleaños, combinando su divertida temática con un sabor que todos adorarán."
    },
    torta_especial_8:
    {
        nombre: "Torta de Barbie",
        imagen: "/img/imagenes_tienda/8_torta_especial.jpg",
        descripcion: "Perfecta para una fiesta de ensueño, esta hermosa torta con temática de Barbie brilla con detalles elegantes y colores vibrantes, creando un mundo de fantasía que hará de cualquier cumpleaños una celebración mágica y deliciosa."
    },
    torta_especial_9:
    {
        nombre: "Torta Peppa Pig",
        imagen: "/img/imagenes_tienda/9_torta_especial.jpg",
        descripcion: "Ideal para los más pequeños, esta divertida torta inspirada en Peppa Pig trae todo el encanto y la ternura del personaje favorito de los niños, haciendo de cualquier cumpleaños una celebración llena de risas, color y sabor."
    },
    torta_especial_10:
    {
        nombre: "Torta de Bluey",
        imagen: "/img/imagenes_tienda/10_torta_especial.jpg",
        descripcion: "Perfecta para una celebración llena de aventuras, esta colorida torta con temática de Bluey captura la energía y el espíritu juguetón de la famosa perrita, convirtiendo cualquier cumpleaños en un evento alegre y lleno de diversión."
    },
    torta_especial_11:
    {
        nombre: "Torta de Intensamente",
        imagen: "/img/imagenes_tienda/11_torta_especial.jpg",
        descripcion: "Ideal para una fiesta llena de emociones, esta vibrante torta inspirada en Intensamente trae a la vida a tus personajes favoritos, combinando colores brillantes y sabores deliciosos para crear una experiencia única que hará de cualquier cumpleaños un recuerdo inolvidable."
    },
    torta_especial_12:
    {
        nombre: "Torta de Frozen",
        imagen: "/img/imagenes_tienda/12_torta_especial.jpg",
        descripcion: "Perfecta para una celebración mágica, esta hermosa torta inspirada en Frozen te transporta al reino de hielo con sus detalles elegantes y colores gélidos, ofreciendo una experiencia encantadora que hará que cualquier cumpleaños brille con la magia de Elsa y Anna."
    },
    velas_1:
    {
        nombre: "Velas: modelo 1",
        imagen: "/img/imagenes_tienda/1_velas.jpg",
        descripcion: "Velas de colores pastel con un diseño ondulado y moderno, perfectas para agregar un toque único y elegante a cualquier celebración."
    },
    velas_2:
    {
        nombre: "Velas: modelo 2",
        imagen: "/img/imagenes_tienda/2_velas.jpg",
        descripcion: "Velas clásicas de rayas en colores brillantes, ideales para darle un toque festivo a cualquier pastel de cumpleaños. Cada paquete incluye 24 unidades de 6.3 cm de altura."
    },
    velas_3:
    {
        nombre: "Velas: modelo 3",
        imagen: "/img/imagenes_tienda/3_velas.jpg",
        descripcion: "Velas de cumpleaños en color rosa brillante, perfectas para añadir un toque femenino y elegante a cualquier celebración. El paquete contiene 20 velas de 8.8 cm de altura."
    },
    velas_4:
    {
        nombre: "Velas: modelo 4",
        imagen: "/img/imagenes_tienda/4_velas.jpg",
        descripcion: "Ilumina tu pastel con nuestras velas Happy Birthday, un detalle colorido y único para sorprender en cualquier celebración."
    },velas_5:
    {
        nombre: "Velas: modelo 5",
        imagen: "/img/imagenes_tienda/5_velas.jpg",
        descripcion: "Celebra cada año con estilo usando nuestras velas de números en tonos pastel. Perfectas para hacer que cada cumpleaños sea aún más especial y memorable."
    },
    velas_6:
    {
        nombre: "Velas: modelo 6",
        imagen: "/img/imagenes_tienda/6_velas.jpg",
        descripcion: "Agrega un toque vibrante a tu fiesta con nuestras velas arcoíris. Ideales para celebrar con color y alegría, estas velas harán que cada pastel brille con una energía festiva y divertida."
    },
};

if (productoId && productos[productoId]) {
    document.getElementById('nombre-producto').innerText = productos[productoId].nombre;
    document.getElementById('imagen-producto').src = productos[productoId].imagen;
    document.getElementById('descripcion-producto').innerText = productos[productoId].descripcion;

    const form = document.querySelector('#des-form form');
    form.innerHTML = '';

    const header = document.createElement('h2');
    header.textContent = 'Realiza tu pedido';
    form.appendChild(header); 

    const priceElement = document.createElement('p');
    priceElement.id = 'precio-producto'; 
    priceElement.textContent = `Precio: $${productos[productoId].precioBase || 0}`; 
    form.appendChild(priceElement); 
    let basePrice = productos[productoId].precioBase || 0;

    productos[productoId].formulario.forEach(field => {
        if (field.type === 'select') {
            const label = document.createElement('label');
            label.textContent = field.label;
            
            const select = document.createElement('select');
            select.name = field.name;

            const placeholderOption = document.createElement('option');
            placeholderOption.value = '';
            placeholderOption.textContent = 'Seleccione una opción:';
            placeholderOption.disabled = true
            placeholderOption.selected = true;
            select.appendChild(placeholderOption);

            field.options.forEach(optionValue => {
                const option = document.createElement('option');
                option.value = optionValue.value;
                option.textContent = optionValue.label;
                select.appendChild(option);
            });
            
            select.addEventListener('change', (e) => {
                const selectedOption = field.options.find(opt => opt.value === e.target.value);
                if (selectedOption) {
                    const updatedPrice = basePrice + selectedOption.price; // Calcular nuevo precio
                    document.getElementById('precio-producto').textContent = `Precio: $${updatedPrice}`;
                }
            });

            form.appendChild(label);
            form.appendChild(select);

        } else if (field.type === 'textarea') {
            const label = document.createElement('label');
            label.textContent = field.label;
            
            const textarea = document.createElement('textarea');
            textarea.name = field.name;
            textarea.rows = field.rows || 4;
            textarea.cols = field.cols || 50;

            form.appendChild(label);
            form.appendChild(textarea);
        } else {
            const label = document.createElement('label');
            label.textContent = field.label;
            
            const input = document.createElement('input');
            input.type = field.type;
            input.name = field.name;

            form.appendChild(label);
            form.appendChild(input);
        }
    });
    const submitButton = document.createElement('button');
    submitButton.type = 'submit';
    submitButton.textContent = 'Añadir al carrito';
    submitButton.classList.add('btn-add-cart');
    form.appendChild(submitButton);

} else {
    document.getElementById('producto-detalle').innerText = "Producto no encontrado.";
}
