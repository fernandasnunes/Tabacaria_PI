/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.usuario;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author fernando.tsuda
 */
public class UsuarioSistemaServiceMockImpl implements UsuarioSistemaService {

    private static Map<String, UsuarioSistema> mapUsuarios;

    private static boolean initialized = false;

    private PapelService papelService;

    public UsuarioSistemaServiceMockImpl() {
        synchronized (UsuarioSistemaServiceMockImpl.class) {
            if (!initialized) {
                papelService = new PapelServiceMockImpl();
                init();
                initialized = true;
            }
        }
    }

    private void init() {
        mapUsuarios = new LinkedHashMap<>();
        mapUsuarios.put("raffael", new UsuarioSistema("raffael", "Raffael Moraes", "123",1,
                Arrays.asList(papelService.findByNome("GERENTE"))));
         mapUsuarios.put("rafael", new UsuarioSistema("rafael", "Rafael Dini", "123",2,
                Arrays.asList(papelService.findByNome("FUNCIONARIO"))));
    }

    @Override
    public UsuarioSistema findByUsername(String username) {
        return mapUsuarios.get(username);
    }
}
