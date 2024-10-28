
function cambiarContenido(tipo) {
    const titulo = document.getElementById('titulo');
    const productContainer = document.getElementById('productContainer');
    if (tipo === 'clasicas') {
        titulo.innerText = "Tortas Clásicas";
        productContainer.innerHTML = `
            <div class="product">
                <img src="/img/imagenes_tienda/carrot_cake_tienda.jpeg" alt="product_1">
                <div class="cont_product">
                    <h3>Carrot Cake</h3>
                    <p>Un pastel suave y esponjoso, hecho con zanahorias frescas y especias, cubierto con un delicioso glaseado de queso crema.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_clasica_1'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/red_velvet_tienda.jpeg" alt="product_2">
                <div class="cont_product">
                    <h3>Red Velvet</h3>
                    <p>Deliciosa combinación de esponjosidad y suavidad, con su característico color rojo y la cremosa cobertura de queso crema.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_clasica_2'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/torta_tres_leches_tienda.jpeg" alt="product_1">
                <div class="cont_product">
                    <h3>Tres Leches</h3>
                    <p>Bizcocho esponjoso empapado en una mezcla de leche condensada, evaporada y crema.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_clasica_3'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/torta_chocolate_tienda.jpeg" alt="product_1">
                <div class="cont_product">
                    <h3>Torta de Chocolate</h3>
                    <p>Intensamente chocolatoso y suave. Cada bocado está lleno de un rico sabor a cacao, complementado con una cobertura cremosa que la hace irresistible</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_clasica_4'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/torta_cafe_tienda.jpeg" alt="product_1">
                <div class="cont_product">
                    <h3>Torta de Café</h3>
                    <p>Suave y esponjosa, con un toque aromático de café que deleita el paladar. Perfecta para los amantes del café, con una cobertura cremosa y equilibrada.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_clasica_5'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/torta_napolitana_tienda.jpeg" alt="product_1">
                <div class="cont_product">
                    <h3>Torta Napolitana</h3>
                    <p>Tres capas irresistibles de vainilla, chocolate y fresa en un solo postre. Esponjosa y llena de sabor, perfecta para quienes no pueden elegir solo uno.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_clasica_6'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/blue_velvet_tienda.jpeg" alt="product_1">
                <div class="cont_product">
                    <h3>Blue Velvet</h3>
                    <p>Una sorprendente variación del clásico, esponjosa y de vibrante color azul. Con un sutil toque de cacao y cobertura de queso crema, es perfecta para cualquier ocasión especial.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_clasica_7'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/selva_negra_tienda.jpeg" alt="product_1">
                <div class="cont_product">
                    <h3>Selva Negra</h3>
                    <p>Un delicioso bizcocho de chocolate negro, intercalado con crema batida y cerezas. Un postre indulgente que combina sabores intensos y frescura en cada bocado.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_clasica_8'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/millhojas_torta_tienda.jpeg" alt="product_1">
                <div class="cont_product">
                    <h3>Mill Hojas</h3>
                    <p>Delicadas capas de masa crujiente, alternando con suave crema pastelera y un toque de dulce de leche. Cada bocado ofrece una textura ligera y un equilibrio perfecto entre lo crujiente y lo cremoso.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_clasica_9'">Ver más</button>
                </div>  
            </div>
            
        `;
    } else if (tipo === 'especiales') {
        titulo.innerText = "Tortas Especiales";
        productContainer.innerHTML = 
        `
            <div class="product">
                <img src="/img/imagenes_tienda/2_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta de Halloween</h3>
                    <p>Ideal para celebrar un Halloween inolvidable, esta torta con temática espeluznante transformará tu mesa en un festín de sustos y sabores, encantando a grandes y pequeños con su decoración única y deliciosa.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_1'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/1_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta Safari</h3>
                    <p>Ideal para sorprender en el cumpleaños de los más pequeños, esta torta con temática safari lleva la diversión de la selva a tu mesa, creando una experiencia única y deliciosa que encantará a todos los niños.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_2'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/3_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta de Cumpleaños 1</h3>
                    <p>Ideal para celebrar un cumpleaños con estilo, esta torta diseñada especialmente para hombres combina elegancia y sabor, convirtiendo cualquier ocasión en un momento inolvidable con su decoración sofisticada y su toque irresistible.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_3'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/4_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta de Cumpleaños 2</h3>
                    <p>Perfecta para un cumpleaños memorable, esta torta para hombres destaca por su diseño moderno y audaz, ofreciendo un equilibrio entre estética y sabor que hará de la celebración un evento único y lleno de personalidad.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_4'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/5_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta de Cumpleaños 3</h3>
                    <p>Ideal para un cumpleaños lleno de encanto, esta elegante torta para mujeres combina detalles delicados con un sabor exquisito, añadiendo un toque especial y femenino que hará de la celebración un momento inolvidable.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_5'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/6_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta de Cumpleaños 4</h3>
                    <p>Perfecta para celebrar a una mujer especial, esta hermosa torta destaca por su estilo sofisticado y sabores irresistibles, aportando un toque de dulzura y elegancia que hará brillar cualquier cumpleaños.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_6'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/7_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta Micky Mouse</h3>
                    <p>Una opción encantadora para los pequeños fans de Disney, esta torta inspirada en Mickey Mouse llena de color y alegría será el centro de atención en cualquier cumpleaños, combinando su divertida temática con un sabor que todos adorarán.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_7'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/8_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta Barbie</h3>
                    <p>Perfecta para una fiesta de ensueño, esta hermosa torta con temática de Barbie brilla con detalles elegantes y colores vibrantes, creando un mundo de fantasía que hará de cualquier cumpleaños una celebración mágica y deliciosa.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_8'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/9_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta Peppa Pig</h3>
                    <p>Ideal para los más pequeños, esta divertida torta inspirada en Peppa Pig trae todo el encanto y la ternura del personaje favorito de los niños, haciendo de cualquier cumpleaños una celebración llena de risas, color y sabor.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_9'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/10_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta Bluey</h3>
                    <p>Perfecta para una celebración llena de aventuras, esta colorida torta con temática de Bluey captura la energía y el espíritu juguetón de la famosa perrita, convirtiendo cualquier cumpleaños en un evento alegre y lleno de diversión</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_10'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/11_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta de Intensamente</h3>
                    <p>Ideal para una fiesta llena de emociones, esta vibrante torta inspirada en Intensamente trae a la vida a tus personajes favoritos, combinando sabores deliciosos para crear una experiencia única que hará de cualquier cumpleaños un recuerdo inolvidable.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_11'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/12_torta_especial.jpg" alt="product_especial">
                <div class="cont_product">
                    <h3>Torta de Frozen</h3>
                    <p>Perfecta para una celebración mágica, esta hermosa torta inspirada en Frozen te transporta al reino de hielo con sus detalles elegantes y colores gélidos, ofreciendo una experiencia encantadora que hará que cualquier cumpleaños brille con la magia de Elsa y Anna</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=torta_especial_12'">Ver más</button>
                </div>
            </div>
        `;
    } else if (tipo === 'velas') {
        titulo.innerText = "Velas";
        productContainer.innerHTML = 
        `
            <div class="product">
                <img src="/img/imagenes_tienda/1_velas.jpg" alt="vela">
                <div class="cont_product">
                    <h3>Velas: modelo 1</h3>
                    <p>Velas de colores pastel con un diseño ondulado y moderno, perfectas para agregar un toque único y elegante a cualquier celebración </p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=velas_1'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/2_velas.jpg" alt="vela">
                <div class="cont_product">
                    <h3>Velas: modelo 2</h3>
                    <p>Velas clásicas de rayas en colores brillantes, ideales para darle un toque festivo a cualquier pastel de cumpleaños. Cada paquete incluye 24 unidades de 6.3 cm de altura.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=velas_2'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/3_velas.jpg" alt="vela">
                <div class="cont_product">
                    <h3>Velas: modelo 3</h3>
                    <p>Velas de cumpleaños en color rosa brillante, perfectas para añadir un toque femenino y elegante a cualquier celebración. El paquete contiene 20 velas de 8.8 cm de altura.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=velas_3'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/4_velas.jpg" alt="vela">
                <div class="cont_product">
                    <h3>Velas: modelo 4</h3>
                    <p>Ilumina tu pastel con nuestras velas "Happy Birthday", un detalle colorido y único para sorprender en cualquier celebración.</p>
                   <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=velas_4'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/5_velas.jpg" alt="vela">
                <div class="cont_product">
                    <h3>Velas: modelo 5</h3>
                    <p>Celebra cada año con estilo usando nuestras velas de números en tonos pastel. Perfectas para hacer que cada cumpleaños sea aún más especial y memorable.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=velas_5'">Ver más</button>
                </div>
            </div>
            <div class="product">
                <img src="/img/imagenes_tienda/6_velas.jpg" alt="vela">
                <div class="cont_product">
                    <h3>Velas: modelo 6</h3>
                    <p>Agrega un toque vibrante a tu fiesta con nuestras velas arcoíris. Ideales para celebrar con color y alegría, estas velas harán que cada pastel brille con una energía festiva y divertida.</p>
                    <button class="btn-ver-mas" onclick="location.href='/productodetalle/?id=velas_6'">Ver más</button>
                </div>
            </div>
        `;
    }
}

cambiarContenido('clasicas');