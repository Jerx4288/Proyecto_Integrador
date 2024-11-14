package com.integrador.proyecto_integrador.model.service;

import com.integrador.proyecto_integrador.model.TipoEnvio;

public interface ITipoEnvioService {
    public TipoEnvio buscarTipoEnvioPorNombre(String nombre);  // Cambiar el retorno de String a TipoEnvio
}