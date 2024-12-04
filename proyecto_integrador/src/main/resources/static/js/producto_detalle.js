
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

                    {value: '15 porciones', label: 'De 15 porciones', price: 100},
                    {value: '25 porciones', label: 'De 25 porciones', price: 160}
                ] 
            },
            
        ]
    },
    torta_clasica_3: {
        nombre: "Tres Leches",
        imagen: "/img/imagenes_tienda/torta_tres_leches_tienda.jpeg",
        descripcion: "Bizcocho esponjoso empapado en una mezcla de leche condensada, evaporada y crema.",
        formulario: [
            { label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    {value: '15 porciones', label: 'De 15 porciones', price: 120},
                    {value: '25 porciones', label: 'De 25 porciones', price: 180}
                ] 
            },
            
        ]
    },
    torta_clasica_4: {
        nombre: "Torta de Chocolate",
        imagen: "/img/imagenes_tienda/torta_chocolate_tienda.jpeg",
        descripcion: "Intensamente chocolatoso y suave. Cada bocado está lleno de un rico sabor a cacao, complementado con una cobertura cremosa que la hace irresistible",
        formulario: [
            { label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    {value: '15 porciones', label: 'De 15 porciones', price: 90},
                    {value: '25 porciones', label: 'De 25 porciones', price: 130}
                ] 
            },
            
        ]
    },
    torta_clasica_5: {
        nombre: "Torta de Café",
        imagen: "/img/imagenes_tienda/torta_cafe_tienda.jpeg",
        descripcion: "Suave y esponjosa, con un toque aromático de café que deleita el paladar. Perfecta para los amantes del café, con una cobertura cremosa y equilibrada.",
        formulario: [
            { label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    {value: '15 porciones', label: 'De 15 porciones', price: 120},
                    {value: '25 porciones', label: 'De 25 porciones', price: 180}
                ] 
            },
            
        ]
    },
    torta_clasica_6: {
        nombre: "Torta Napolitana",
        imagen: "/img/imagenes_tienda/torta_napolitana_tienda.jpeg",
        descripcion: "Tres capas irresistibles de vainilla, chocolate y fresa en un solo postre. Esponjosa y llena de sabor, perfecta para quienes no pueden elegir solo uno.",
        formulario: [
            { label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    {value: '15 porciones', label: 'De 15 porciones', price: 70},
                    {value: '25 porciones', label: 'De 25 porciones', price: 100}
                ] 
            },
            
        ]
    },
    torta_clasica_7: {
        nombre: "Blue Velvet",
        imagen: "/img/imagenes_tienda/blue_velvet_tienda.jpeg",
        descripcion: "Una sorprendente variación del clásico, esponjosa y de vibrante color azul. Con un sutil toque de cacao y cobertura de queso crema, es perfecta para cualquier ocasión especial.",
        formulario: [
            { label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    {value: '15 porciones', label: 'De 15 porciones', price: 120},
                    {value: '25 porciones', label: 'De 25 porciones', price: 180}
                ] 
            },
            
        ]
    },
    torta_clasica_8: {
        nombre: "Selva Negra",
        imagen: "/img/imagenes_tienda/selva_negra_tienda.jpeg",
        descripcion: "Un delicioso bizcocho de chocolate negro, intercalado con crema batida y cerezas. Un postre indulgente que combina sabores intensos y frescura en cada bocado.",
        formulario: [
            { label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    {value: '15 porciones', label: 'De 15 porciones', price: 90},
                    {value: '25 porciones', label: 'De 25 porciones', price: 120}
                ] 
            },
            
        ]
    },
    torta_clasica_9: {
        nombre: "Mill Hojas",
        imagen: "/img/imagenes_tienda/millhojas_torta_tienda.jpeg",
        descripcion: "Delicadas capas de masa crujiente, alternando con suave crema pastelera y un toque de dulce de leche. Cada bocado ofrece una textura ligera y un equilibrio perfecto entre lo crujiente y lo cremoso.",
        formulario: [
            { label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    {value: '15 porciones', label: 'De 15 porciones', price: 80},
                    {value: '25 porciones', label: 'De 25 porciones', price: 110}
                ] 
            },
            
        ]
    },
    torta_especial_1: {
        nombre: "Torta de Halloween",
        imagen: "/img/imagenes_tienda/2_torta_especial.jpg",
        descripcion: "Ideal para celebrar un Halloween inolvidable, esta torta con temática espeluznante transformará tu mesa en un festín de sustos y sabores, encantando a grandes y pequeños con su decoración única y deliciosa.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '20 porciones', label: 'De 20 porciones', price: 140 },
                    { value: '30 porciones', label: 'De 30 porciones', price: 220 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 15},
                    { value: 'vainilla', label: 'Vainilla', price:0 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 20 },
                    { value: 'Fudge', label: 'Fudge', price:30 }
                ]
            },
            
        ]
    },    
    torta_especial_2:
    {
        nombre: "Torta Safari",
        imagen: "/img/imagenes_tienda/1_torta_especial.jpg",
        descripcion: "Ideal para sorprender en el cumpleaños de los más pequeños, esta torta con temática safari lleva la diversión de la selva a tu mesa, creando una experiencia única y deliciosa que encantará a todos los niños.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '40 porciones', label: 'De 40 porciones', price: 200 },
                    { value: '55 porciones', label: 'De 55 porciones', price: 350 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            },
            
        ]
    },
    torta_especial_3:
    {
        nombre: "Torta de Cumpleaños: Modelo 1",
        imagen: "/img/imagenes_tienda/3_torta_especial.jpg",
        descripcion: "Ideal para celebrar un cumpleaños con estilo, esta torta diseñada especialmente para hombres combina elegancia y sabor, convirtiendo cualquier ocasión en un momento inolvidable con su decoración sofisticada y su toque irresistible.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '40 porciones', label: 'De 40 porciones', price: 250 },
                    { value: '55 porciones', label: 'De 55 porciones', price: 440 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            },
            
        ]
    },
    torta_especial_4:
    {
        nombre: "Torta de Cumpleaños: Modelo 2",
        imagen: "/img/imagenes_tienda/4_torta_especial.jpg",
        descripcion: "Perfecta para un cumpleaños memorable, esta torta para hombres destaca por su diseño moderno y audaz, ofreciendo un equilibrio entre estética y sabor que hará de la celebración un evento único y lleno de personalidad.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '30 porciones', label: 'De 30 porciones', price: 180 },
                    { value: '40 porciones', label: 'De 45 porciones', price: 250 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            }
        ]
    },
    torta_especial_5:
    {
        nombre: "Torta de Cumpleaños: Modelo 3",
        imagen: "/img/imagenes_tienda/5_torta_especial.jpg",
        descripcion: "Ideal para un cumpleaños lleno de encanto, esta elegante torta para mujeres combina detalles delicados con un sabor exquisito, añadiendo un toque especial y femenino que hará de la celebración un momento inolvidable.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '30 porciones', label: 'De 30 porciones', price: 200 },
                    { value: '45 porciones', label: 'De 45 porciones', price: 320 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            }
        ]
    },
    torta_especial_6:
    {
        nombre: "Torta de Cumpleaños: Modelo 4",
        imagen: "/img/imagenes_tienda/6_torta_especial.jpg",
        descripcion: "Perfecta para celebrar a una mujer especial, esta hermosa torta destaca por su estilo sofisticado y sabores irresistibles, aportando un toque de dulzura y elegancia que hará brillar cualquier cumpleaños.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '20 porciones', label: 'De 20 porciones', price: 160 },
                    { value: '30 porciones', label: 'De 30 porciones', price: 220 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            }
        ]
    },
    torta_especial_7:
    {
        nombre: "Torta de Micky Mouse",
        imagen: "/img/imagenes_tienda/7_torta_especial.jpg",
        descripcion: "Una opción encantadora para los pequeños fans de Disney, esta torta inspirada en Mickey Mouse llena de color y alegría será el centro de atención en cualquier cumpleaños, combinando su divertida temática con un sabor que todos adorarán.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '40 porciones', label: 'De 40 porciones', price: 200 },
                    { value: '55 porciones', label: 'De 55 porciones', price: 350 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            }
        ]
    },
    torta_especial_8:
    {
        nombre: "Torta de Barbie",
        imagen: "/img/imagenes_tienda/8_torta_especial.jpg",
        descripcion: "Perfecta para una fiesta de ensueño, esta hermosa torta con temática de Barbie brilla con detalles elegantes y colores vibrantes, creando un mundo de fantasía que hará de cualquier cumpleaños una celebración mágica y deliciosa.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '30 porciones', label: 'De 30 porciones', price: 200 },
                    { value: '40 porciones', label: 'De 40 porciones', price: 250 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            }
        ]
    },
    torta_especial_9:
    {
        nombre: "Torta Peppa Pig",
        imagen: "/img/imagenes_tienda/9_torta_especial.jpg",
        descripcion: "Ideal para los más pequeños, esta divertida torta inspirada en Peppa Pig trae todo el encanto y la ternura del personaje favorito de los niños, haciendo de cualquier cumpleaños una celebración llena de risas, color y sabor.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '20 porciones', label: 'De 20 porciones', price: 180 },
                    { value: '30 porciones', label: 'De 30 porciones', price: 240 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            }
        ]
    },
    torta_especial_10:
    {
        nombre: "Torta de Bluey",
        imagen: "/img/imagenes_tienda/10_torta_especial.jpg",
        descripcion: "Perfecta para una celebración llena de aventuras, esta colorida torta con temática de Bluey captura la energía y el espíritu juguetón de la famosa perrita, convirtiendo cualquier cumpleaños en un evento alegre y lleno de diversión.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '25 porciones', label: 'De 25 porciones', price: 160 },
                    { value: '35 porciones', label: 'De 35 porciones', price: 240 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            }
        ]     
    },
    torta_especial_11:
    {
        nombre: "Torta de Intensamente",
        imagen: "/img/imagenes_tienda/11_torta_especial.jpg",
        descripcion: "Ideal para una fiesta llena de emociones, esta vibrante torta inspirada en Intensamente trae a la vida a tus personajes favoritos, combinando colores brillantes y sabores deliciosos para crear una experiencia única que hará de cualquier cumpleaños un recuerdo inolvidable.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '20 porciones', label: 'De 20 porciones', price: 150 },
                    { value: '30 porciones', label: 'De 30 porciones', price: 250 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            }
        ]
    },
    torta_especial_12:
    {
        nombre: "Torta de Frozen",
        imagen: "/img/imagenes_tienda/12_torta_especial.jpg",
        descripcion: "Perfecta para una celebración mágica, esta hermosa torta inspirada en Frozen te transporta al reino de hielo con sus detalles elegantes y colores gélidos, ofreciendo una experiencia encantadora que hará que cualquier cumpleaños brille con la magia de Elsa y Anna.",
        formulario: [
            {
                label: "Seleccione el tamaño:",
                type: "select",
                name: "tamanio",
                options: [
                    { value: '30 porciones', label: 'De 30 porciones', price: 200 },
                    { value: '40 porciones', label: 'De 40 porciones', price: 280 }
                ]
            },
            {
                label: "Tipo de cake:",
                type: "select",
                name: "tipo",
                options: [
                    { value: 'chocolate', label: 'Chocolate', price: 20},
                    { value: 'vainilla', label: 'Vainilla', price:15 }
                ]
            },
            {
                label: "Relleno de cake:",
                type: "select",
                name: "relleno",
                options: [
                    { value: 'Manjar', label: 'Manjar Blanco', price: 35 },
                    { value: 'Fudge', label: 'Fudge', price:50 }
                ]
            }
        ]
    },
    velas_1:
    {
        nombre: "Velas: modelo 1",
        imagen: "/img/imagenes_tienda/1_velas.jpg",
        descripcion: "Velas de colores pastel con un diseño ondulado y moderno, perfectas para agregar un toque único y elegante a cualquier celebración.",
        formulario: [
            {
                label: "Seleccione la cantidad:",
                type: "select",
                name: "cantidad",
                options: [
                    { value: '1', label: '1', price: 10 },
                    { value: '2', label: '2', price: 20 },
                    { value: '3', label: '3', price: 30 },
                    { value: '4', label: '4', price: 40 },
                    { value: '5', label: '5', price: 50 },
                ]
            }
        ]
    },
    velas_2:
    {
        nombre: "Velas: modelo 2",
        imagen: "/img/imagenes_tienda/2_velas.jpg",
        descripcion: "Velas clásicas de rayas en colores brillantes, ideales para darle un toque festivo a cualquier pastel de cumpleaños. Cada paquete incluye 24 unidades de 6.3 cm de altura.",
        formulario: [
            {
                label: "Seleccione la cantidad:",
                type: "select",
                name: "cantidad",
                options: [
                    { value: '1', label: '1', price: 15 },
                    { value: '2', label: '2', price: 30 },
                    { value: '3', label: '3', price: 45 },
                    { value: '4', label: '4', price: 60 },
                    { value: '5', label: '5', price: 75 },
                ]
            }
        ]
    },
    velas_3:
    {
        nombre: "Velas: modelo 3",
        imagen: "/img/imagenes_tienda/3_velas.jpg",
        descripcion: "Velas de cumpleaños en color rosa brillante, perfectas para añadir un toque femenino y elegante a cualquier celebración. El paquete contiene 20 velas de 8.8 cm de altura.",
        formulario: [
            {
                label: "Seleccione la cantidad:",
                type: "select",
                name: "cantidad",
                options: [
                    { value: '1', label: '1', price: 15 },
                    { value: '2', label: '2', price: 30 },
                    { value: '3', label: '3', price: 45 },
                    { value: '4', label: '4', price: 60 },
                    { value: '5', label: '5', price: 75 },
                ]
            }
        ]
    },
    velas_4:
    {
        nombre: "Velas: modelo 4",
        imagen: "/img/imagenes_tienda/4_velas.jpg",
        descripcion: "Ilumina tu pastel con nuestras velas Happy Birthday, un detalle colorido y único para sorprender en cualquier celebración.",
        formulario: [
            {
                label: "Seleccione la cantidad:",
                type: "select",
                name: "cantidad",
                options: [
                    { value: '1', label: '1', price: 10 },
                    { value: '2', label: '2', price: 20 },
                    { value: '3', label: '3', price: 30 },
                    { value: '4', label: '4', price: 40 },
                    { value: '5', label: '5', price: 50 },
                ]
            }
        ]
    },
    velas_5:
    {
        nombre: "Velas: modelo 5",
        imagen: "/img/imagenes_tienda/5_velas.jpg",
        descripcion: "Celebra cada año con estilo usando nuestras velas de números en tonos pastel. Perfectas para hacer que cada cumpleaños sea aún más especial y memorable.",
        formulario: [
            {
                label: "Seleccione la cantidad:",
                type: "select",
                name: "cantidad",
                options: [
                    { value: '1', label: '1', price: 10 },
                    { value: '2', label: '2', price: 20 },
                    { value: '3', label: '3', price: 30 },
                    { value: '4', label: '4', price: 40 },
                    { value: '5', label: '5', price: 50 },
                ]
            }
        ]
    },
    velas_6:
    {
        nombre: "Velas: modelo 6",
        imagen: "/img/imagenes_tienda/6_velas.jpg",
        descripcion: "Agrega un toque vibrante a tu fiesta con nuestras velas arcoíris. Ideales para celebrar con color y alegría, estas velas harán que cada pastel brille con una energía festiva y divertida.",
        formulario: [
            {
                label: "Seleccione la cantidad:",
                type: "select",
                name: "cantidad",
                options: [
                    { value: '1', label: '1', price: 10 },
                    { value: '2', label: '2', price: 20 },
                    { value: '3', label: '3', price: 30 },
                    { value: '4', label: '4', price: 40 },
                    { value: '5', label: '5', price: 50 },
                ]
            }
        ]
    },
};


let precioBase = 0;
let precioTotal = 0;
let carrito = [];

// Suponiendo que productos es un objeto con los productos y sus detalles
if (productoId && productos[productoId]) {
    // Llenamos los datos del producto
    document.getElementById('nombre-producto').innerText = productos[productoId].nombre;
    document.getElementById('imagen-producto').src = productos[productoId].imagen;
    document.getElementById('descripcion-producto').innerText = productos[productoId].descripcion;

    // Llamamos al formulario
    const form = document.querySelector('#des-form form');
    form.innerHTML = ''; // Limpiamos el formulario previo

    // Creamos un encabezado dentro del formulario
    const header = document.createElement('h2');
    header.textContent = 'Realiza tu pedido';
    form.appendChild(header);

    // Creamos un p para el precio que se actualizará poco a poco y lo asignamos al formulario
    const priceElement = document.createElement('p');
    priceElement.id = 'precio-producto';
    priceElement.textContent = `Precio: S/${precioTotal}`;
    form.appendChild(priceElement);

    // Recorrer el formulario y generar los campos
    productos[productoId].formulario.forEach(field => {
        if (field.type === 'select') {
            // Crear un label
            const label = document.createElement('label');
            label.textContent = field.label;

            // Crear un select
            const select = document.createElement('select');
            select.name = field.name;
            select.required = true;  // Hacerlo obligatorio

            // Opción por defecto
            const placeholderOption = document.createElement('option');
            placeholderOption.value = '';
            placeholderOption.textContent = 'Seleccione una opción:';
            placeholderOption.disabled = true;
            placeholderOption.selected = true;
            select.appendChild(placeholderOption);

            // Agregar las opciones
            field.options.forEach(option => {
                const optionElement = document.createElement('option');
                optionElement.value = option.value;
                optionElement.textContent = option.label;
                optionElement.dataset.price = option.price;  // Almacenamos el precio en el atributo 'data-price'
                select.appendChild(optionElement);
            });

            // Evento change para actualizar el precio
            select.addEventListener('change', (e) => {
                const selectedOption = e.target.selectedOptions[0];

                // Si el select es para tamaño, se actualiza el precio base
                if (field.name === 'tamanio') {
                    precioBase = parseFloat(selectedOption.dataset.price) || 0;
                    precioTotal = precioBase;  // Reiniciar el precio total con el precio base
                }

                if (field.name === 'tipo' || field.name === 'relleno' || field.name === 'cantidad') {
                    const precioAdicional = parseFloat(selectedOption.dataset.price) || 0;
                    precioTotal = precioBase + precioAdicional; // Sumar al precio base
                }

                // Actualizar el precio final
                document.getElementById('precio-producto').textContent = `Precio: S/${precioTotal}`;
            });

            form.appendChild(label);
            form.appendChild(select);

        }
    });

    // Botón para añadir al carrito
    const submitButton = document.createElement('button');
    submitButton.type = 'submit';
    submitButton.textContent = 'Añadir al carrito';
    submitButton.classList.add('btn-add-cart');
    form.appendChild(submitButton);

    form.addEventListener('submit', (event) => {
        event.preventDefault();

        // Capturar los valores seleccionados
        const nombreProducto = productos[productoId].nombre;
        const imagenProducto = productos[productoId].imagen;
        const tamanio = form.querySelector('select[name="tamanio"]') ? form.querySelector('select[name="tamanio"]').value : '';
        const tipo = form.querySelector('select[name="tipo"]') ? form.querySelector('select[name="tipo"]').value : '';
        const relleno = form.querySelector('select[name="relleno"]') ? form.querySelector('select[name="relleno"]').value : '';
        const cantidad = form.querySelector('select[name="cantidad"]') ? form.querySelector('select[name="cantidad"]').value : '';

        // Crear objeto del producto para añadir al carrito
        const productoEnCarrito = {
            nombre: nombreProducto,
            imagen: imagenProducto,
            tamanio: tamanio,
            tipo: tipo,
            relleno: relleno,
            cantidad: cantidad,
            precioTotal: precioTotal
        };

        // Recuperar el carrito del localStorage o inicializarlo vacío
        let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

        // Agregar el producto al carrito
        carrito.push(productoEnCarrito);

        // Guardar el carrito actualizado en el localStorage
        localStorage.setItem('carrito', JSON.stringify(carrito));
        
        System.out.println(carrito);
    });

} else {
    alert("Producto no disponible o no encontrado.");
    document.getElementById('producto-detalle').innerText = "Producto no encontrado.";
}
