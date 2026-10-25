package com.aydsii.demo.service;

import com.aydsii.demo.exception.*;
import com.aydsii.demo.model.dto.PromocionRequestDTO;
import com.aydsii.demo.model.dto.PromocionRespuestaDTO;
import com.aydsii.demo.model.tablas.*;
import com.aydsii.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PromocionService {

    private final PromocionRepository promocionRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioInternoRepository usuarioRepository;

    public PromocionService(PromocionRepository promocionRepository,
            ProductoRepository productoRepository,
            UsuarioInternoRepository usuarioRepository) {
        this.promocionRepository = promocionRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public PromocionRespuestaDTO cargarPromocion(PromocionRequestDTO dto) {

        // RN-13: la fecha de fin no puede ser anterior a la de inicio
        if (dto.fechaFin().isBefore(dto.fechaInicio())) {
            throw new PromocionInvalidaException("La fecha de fin debe ser posterior o igual a la de inicio");
        }

        // RN-12: el porcentaje no puede superar el 100%
        if (dto.tipo() == TipoPromocion.PORCENTAJE && dto.valor() > 100) {
            throw new PromocionInvalidaException("El descuento porcentual no puede superar el 100%");
        }

        // una promoción por cantidad necesita su cantidad mínima
        if (dto.tipo() == TipoPromocion.POR_CANTIDAD
                && (dto.cantidadMinima() == null || dto.cantidadMinima() <= 0)) {
            throw new PromocionInvalidaException("La promoción por cantidad requiere una cantidad mínima mayor a 0");
        }

        // el vendedor tiene que existir
        UsuarioInterno usuario = usuarioRepository.findById(dto.usuarioDni())
                .orElseThrow(() -> new UsuarioNoEncontradoException("El vendedor no existe"));

        // todos los productos tienen que existir
        List<Integer> codigos = dto.productos().stream().distinct().toList();
        List<Producto> productos = productoRepository.findAllById(codigos);
        if (productos.size() != codigos.size()) {
            throw new ProductoNoEncontradoException("Alguno de los productos indicados no existe en el catálogo");
        }

        // RN-14 / FE-02: ningún producto puede tener otra promoción en el mismo período
        List<Promocion> superpuestas = promocionRepository.findSuperpuestas(codigos, dto.fechaInicio(), dto.fechaFin());
        if (!superpuestas.isEmpty()) {
            Set<Integer> enConflicto = superpuestas.stream()
                    .flatMap(p -> p.getProductos().stream())
                    .map(Producto::getCodigo)
                    .filter(codigos::contains)
                    .collect(Collectors.toSet());
            throw new PromocionSuperpuestaException(
                    "Los productos " + enConflicto + " ya tienen otra promoción vigente en ese período");
        }

        // todo OK: se arma la entidad y se guarda
        Promocion promocion = new Promocion();
        promocion.setNombre(dto.nombre());
        promocion.setTipo(dto.tipo());
        promocion.setValor(dto.valor());
        promocion.setCantidadMinima(dto.tipo() == TipoPromocion.POR_CANTIDAD ? dto.cantidadMinima() : null);
        promocion.setFechaInicio(dto.fechaInicio());
        promocion.setFechaFin(dto.fechaFin());
        // RN-15: hasta la fecha de inicio queda Programada; si ya empezó, Vigente
        promocion.setEstado(dto.fechaInicio().isAfter(LocalDate.now()) ? "PROGRAMADA" : "VIGENTE");
        promocion.setUsuario(usuario);
        promocion.setProductos(new HashSet<>(productos));

        Promocion guardada = promocionRepository.save(promocion);

        return new PromocionRespuestaDTO(
                guardada.getId(), guardada.getNombre(), guardada.getTipo().name(), guardada.getValor(),
                guardada.getCantidadMinima(), guardada.getFechaInicio(), guardada.getFechaFin(),
                guardada.getEstado(), codigos);
    }
}