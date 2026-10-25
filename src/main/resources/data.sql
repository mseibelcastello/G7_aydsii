INSERT IGNORE INTO clientes (dni, nombre, apellido, email, telefono)
VALUES ('30111222', 'Ana', 'Pérez', 'ana@mail.com', '3794000000'),
        ('22333444', 'Juan', 'Pérez', 'juan@mail.com', '3794000000');

INSERT IGNORE INTO productos (codigo, nombre, descripcion, categoria, precio_pack, unidades_pack, stock_fisico, stock_reservado, stock_minimo, estado)
VALUES (1, 'Vaso PET 250cc', 'Pack 10 un.', 'Vasos', 4500, 10, 100, 0, 20, 'DISPONIBLE');

INSERT IGNORE INTO pedidos (id, fecha, estado, modo_entrega, direccion_entrega, valor_total, cliente_dni)
VALUES (1, NOW(), 'PAGADO', 'RETIRO_LOCAL', NULL, 9000, '30111222'),
       (2, NOW(), 'CANCELADO', 'ENVIO', 'Calle 123', 4500, '30111222');

UPDATE pedidos SET motivo_cancelacion = 'Pedido duplicado' WHERE id = 2;

INSERT IGNORE INTO detalle_pedido (id, pedido_id, producto_codigo, cantidad, precio_unitario, descuento_aplicado)
VALUES (1, 1, 1, 2, 4500, 0),
       (2, 2, 1, 1, 4500, 0);

INSERT IGNORE INTO historial_estado (id, fecha_hora, estado, pedido_id)
VALUES (1, NOW(), 'ACEPTADO', 1),
       (2, NOW(), 'PAGADO', 1),
       (3, NOW(), 'ACEPTADO', 2),
       (4, NOW(), 'CANCELADO', 2);