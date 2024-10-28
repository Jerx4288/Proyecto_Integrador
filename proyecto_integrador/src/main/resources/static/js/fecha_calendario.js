const hoy = new Date().toISOString().split('T')[0];
document.getElementById('fecha').setAttribute('min', hoy);