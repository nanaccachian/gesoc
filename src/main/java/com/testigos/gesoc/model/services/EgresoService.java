package com.testigos.gesoc.model.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.entidades.Entidad;
import com.testigos.gesoc.persistence.DAOEgreso;

import org.springframework.stereotype.Service;

@Service
public class EgresoService {

    private DAOEgreso repo = new DAOEgreso();

    public void persist(List<Egreso> egresos) {
        egresos.stream().forEach(e -> repo.persist(e));
    }

    public void persist(Egreso egreso) {
        repo.persist(egreso);
    }

    public Egreso find(int egreso) {
        return repo.find(egreso);
    }

    public List<Egreso> findAll() {
        return repo.findAll();
    }

    public List<Egreso> findAll(Entidad entidad) {
        return repo.findAll().stream().filter(e -> e.getComprador().getId() == entidad.getId())
                .collect(Collectors.toList());
    }

    public List<Egreso> getEgresosNoJustificados(Entidad entidad) {
        return findAllConProveedoreItems(entidad).stream().filter(eg -> eg.getIngresoAsociado() == null)
                .collect(Collectors.toList());
    }

    public void update(List<Egreso> egresos) {
        for (Egreso e : egresos)
            repo.update(e);
    }

    // public List<Egreso> findAllConProveedor() {
    // return repo.findAllConProveedor();
    // }

    public List<Egreso> findAllConProveedor(Entidad entidad) {
        return repo.findAllConProveedor().stream().filter(e -> e.getComprador().getId() == entidad.getId())
                .collect(Collectors.toList());
    }

    public List<Egreso> findAllConProveedoreItems() {
        return repo.findAllConProveedoreItems();
    }

    public List<Egreso> findAllConProveedoreItems(Entidad entidad) {
        return repo.findAllConProveedoreItems().stream().filter(e -> e.getComprador().getId() == entidad.getId())
                .collect(Collectors.toList());
    }

    public Double montoActual(Entidad entidad) {
        return repo.findAllConItems().stream().filter(e -> e.getComprador().getId() == entidad.getId())
                .mapToDouble(Egreso::valorTotal).sum();
    }

    public Double montoMes(Entidad entidad) {
        return repo.findAllConItems().stream()
                .filter(e -> e.getFechaOperacion().getMonthValue() == LocalDate.now().getMonthValue()
                        && e.getComprador().getId() == entidad.getId())
                .mapToDouble(Egreso::valorTotal).sum();
    }

    public Double montoAnio(Entidad entidad) {
        return repo.findAllConItems().stream().filter(e -> e.getFechaOperacion().getYear() == LocalDate.now().getYear()
                && e.getComprador().getId() == entidad.getId()).mapToDouble(Egreso::valorTotal).sum();
    }

    public Egreso findUltimoEgreso(Entidad entidad) {
        return repo.findAllConProveedoreItems().stream().reduce((first, second) -> second)
                .filter(e -> e.getComprador().getId() == entidad.getId()).orElse(null);
    }

    public List<Egreso> findAllSinDocumentoComercial(Entidad entidad) {
        return repo.findAllConDocumentoComercial().stream()
                .filter(e -> e.getComprador().getId() == entidad.getId() && e.getDocumento() == null)
                .collect(Collectors.toList());
    }
}
