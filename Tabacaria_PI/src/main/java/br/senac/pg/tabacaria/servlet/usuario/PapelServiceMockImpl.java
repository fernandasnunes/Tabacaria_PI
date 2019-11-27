package br.senac.pg.tabacaria.servlet.usuario;

import java.util.LinkedHashMap;
import java.util.Map;

public class PapelServiceMockImpl implements PapelService {

    private static Map<String, Papel> mapPapeis;

    private static boolean initialized = false;

    public PapelServiceMockImpl() {
        synchronized (PapelServiceMockImpl.class) {
            if (!initialized) {
                init();
                initialized = true;
            }
        }
    }

    private void init() {
        mapPapeis = new LinkedHashMap<>();
        mapPapeis.put("GERENTE", new Papel("GERENTE"));
        mapPapeis.put("FUNCIONARIO", new Papel("FUNCIONARIO"));
    }

    @Override
    public Papel findByNome(String nomePapel) {
        return mapPapeis.get(nomePapel);
    }
}
