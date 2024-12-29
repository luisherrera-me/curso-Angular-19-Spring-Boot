-- Insertar datos en la tabla 'rol'
INSERT INTO rol (name, description, logo, created_at, updated_at) VALUES
('ADMIN', 'Role with full system permissions', '/images/admin_logo.png', '2024-12-01 08:00:00', '2024-12-01 08:00:00'),
('USER', 'Role with read-only access', '/images/viewer_logo.png', '2024-12-01 08:30:00', '2024-12-01 08:30:00');

-- Insertar datos en la tabla 'user'
INSERT INTO "users" (id_rol, name, photo , address, email, password, created_at, updated_at) VALUES
(1, 'administrador', 'https://cdn-icons-png.flaticon.com/512/149/149071.png', 'kr24E # 8-11', 'admin@admin.com', '$2a$10$pYhWOZFGKZbEK5se6lHUSuXJTbmI4khkjGWR3R5UNZCba1KZInbHS', '2024-12-01 09:00:00', '2024-12-01 09:00:00'),
(1, 'Luis Herrera', 'https://cdn-icons-png.flaticon.com/512/149/149071.png', 'kr54a # 1-87', 'luis@user.com', '$2a$10$KaLYMMVkZG83w.LS/O3pYOVGSuLCp8EBbLeOgaLqKEYHsdOdgX3xW', '2024-12-01 09:10:00', '2024-12-01 09:10:00');

