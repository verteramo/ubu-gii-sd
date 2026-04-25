-- Rol 'ADMIN'
INSERT INTO role (id, role_name, show_on_create) VALUES (1, 'ADMIN', 1);

-- Usuario de prueba
INSERT INTO user (id, username, password, nombre_usuario, email, user_role_id, fecha_ultimo_acceso) VALUES (1, 'admin', '$2a$10$dUwn3Rm0fVIpBQvzWt90D.pyrSTlyaOnKaRGcWNYHc6u9vEtVHGMq', 'Administrador', 'admin@mail.com', 1, NOW());

REPLACE INTO user_seq (next_val) VALUES (2);
