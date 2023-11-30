INSERT INTO category(category)
VALUES
    ('Programación'), ('Diseño'), ('Márqueting');
INSERT INTO language(language)
VALUES
    ('English'), ('Español'), ('Català'), ('Deutsch'), ('Italiano');

INSERT INTO user_lab (name, second_name, third_name, email, gender, username)
VALUES
    ('Juan', 'Pérez', 'Gómez', 'juan.perez@example.com', 'Masculino', 'usuario1'),
    ('María', 'González', 'López', 'maria.gonzalez@example.com', 'Femenino', 'usuario2'),
    ('Carlos', 'Sánchez', 'Martínez', 'carlos.sanchez@example.com', 'Masculino', 'usuario3'),
    ('Isabel', 'Fernández', 'Rodríguez', 'isabel.fernandez@example.com', 'Femenino', 'usuario4'),
    ('Manuel', 'López', 'Soto', 'manuel.lopez@example.com', 'Masculino', 'usuario5'),
    ('Laura', 'Ramírez', 'García', 'laura.ramirez@example.com', 'Femenino', 'usuario6'),
    ('Pedro', 'Díaz', 'Torres', 'pedro.diaz@example.com', 'Masculino', 'usuario7'),
    ('Carmen', 'Ruiz', 'Fernández', 'carmen.ruiz@example.com', 'Femenino', 'usuario8'),
    ('Antonio', 'Moreno', 'Sánchez', 'antonio.moreno@example.com', 'Masculino', 'usuario9'),
    ('Sofía', 'Torres', 'López', 'sofia.torres@example.com', 'Femenino', 'usuario10');

INSERT INTO course (id, title, description, publication, last_update, image_url, current_price, availability, category_category, language_language, teacher_id)
VALUES ('4f4e501f-6350-48c9-9f8d-0a4b32b9a1a2', 'Programacion en Python', 'Aprende a programar en Python desde cero.', '2023-02-15', '2023-03-10', 'imagen1.jpg', 99.00, false, 'Programación', 'Español', 'usuario1');
INSERT INTO course (id, title, description, publication, last_update, image_url, current_price, availability, category_category, language_language, teacher_id)
VALUES ('d53b6f99-7f60-4a53-b801-6e4aa190bdcf', 'Introduccion a la IA', 'Descubre los conceptos basicos de la Inteligencia Artificial.', '2023-03-10', '2023-04-05', 'imagen2.jpg', 79.00, true, 'Programación', 'Català', 'usuario1');
INSERT INTO course (id, title, description, publication, last_update, image_url, current_price, availability, category_category, language_language, teacher_id)
VALUES ('70e3ca87-9b86-4827-bc2c-daa8fe26a36f', 'Disenyo Grafico Avanzado', 'Mejora tus habilidades en disenyo grafico.', '2023-04-05', '2023-05-02', 'imagen3.jpg', 129.00, true, 'Diseño', 'Español', 'usuario2');
INSERT INTO course (id, title, description, publication, last_update, image_url, current_price, availability, category_category, language_language, teacher_id)
VALUES ('fff6e217-7851-4d8d-8cc7-73290d0f96ed', 'Marketing Digital', 'Aprende estrategias efectivas de marketing en linea.', '2023-05-02', '2023-06-20', 'imagen4.jpg', 89.00, true, 'Márqueting', 'English', 'usuario3');
INSERT INTO course (id, title, description, publication, last_update, image_url, current_price, availability, category_category, language_language, teacher_id)
VALUES ('c0805b8c-1f4c-45c7-9776-bbcf6e1a1a7d', 'Desarrollo Web Full Stack', 'Conviertete en un desarrollador web completo.', '2023-06-20', '2023-07-15', 'imagen5.jpg', 149.00, true, 'Programación', 'English', 'usuario1');

INSERT INTO lesson (title, description, duration, videourl, num_order, course_id)
VALUES ('Principios de la IA', 'Lección introductoria a la IA.', 30, 'video_intro_IA.mp4', 1, 'd53b6f99-7f60-4a53-b801-6e4aa190bdcf');
INSERT INTO lesson (title, description, duration, videourl, num_order, course_id)
VALUES ('Intermedio de la IA', 'Lección de continuación a la IA.', 30, 'video_medio_IA2.mp4', 2, 'd53b6f99-7f60-4a53-b801-6e4aa190bdcf');
INSERT INTO lesson (title, description, duration, videourl, num_order, course_id)
VALUES ('Avanzada de la IA', 'Última lección de la IA.', 30, 'video_final_IA2.mp4', 3, 'd53b6f99-7f60-4a53-b801-6e4aa190bdcf');

INSERT INTO role(name) VALUES ('ROLE_STUDENT');
INSERT INTO role(name) VALUES ('ROLE_TEACHER');
INSERT INTO role(name) VALUES ('ROLE_ADMIN');

INSERT INTO user_security (email, username, password)
VALUES                                                          --password123
    ('juan.perez@example.com', 'usuario1', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('maria.gonzalez@example.com', 'usuario2', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('carlos.sanchez@example.com', 'usuario3', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('isabel.fernandez@example.com', 'usuario4', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('manuel.lopez@example.com', 'usuario5', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('laura.ramirez@example.com', 'usuario6', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('pedro.diaz@example.com', 'usuario7', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('carmen.ruiz@example.com', 'usuario8', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('antonio.moreno@example.com', 'usuario9', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('sofia.torres@example.com', 'usuario10', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2'),
    ('admin@example.com', 'admin', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');

INSERT INTO user_roles (USER_ID, ROLE_ID)
VALUES
    (1, 2), (2, 2), (3, 2),         --Teachers
    (4, 1), (5, 1), (6, 1),         --Students
    (7, 1), (8, 1), (9, 1), (10, 1),
    (11, 3);                        --Admin

INSERT INTO review (id, title, contents, satisfaction, date)
VALUES
    (101, 'Excelente Curso de Marketing Digital', 'Muy buen curso, aprendí mucho.', 4, CURRENT_DATE),
    (102, 'Increíble curso de Diseño Gráfico Avanzado', 'El curso de Diseño Gráfico Avanzado superó mis expectativas.', 4, CURRENT_DATE),
    (103, 'Desarrollo Web Full Stack', 'Buen contenido, pero podría mejorar en algunos aspectos.', 2, CURRENT_DATE),
    (104, 'Marketing Digital', 'El curso de Marketing Digital es excelente, lo recomiendo.', 3, CURRENT_DATE),
    (105, 'Curso Intermedio de Desarrollo Web Full Stack', 'Buen curso, aunque algunas secciones podrían ser más detalladas.', 1, CURRENT_DATE),
    (106, 'Curso Avanzado de Diseño Gráfico', 'Me encantó el curso, muy bien explicado.', 5, CURRENT_DATE),
    (107, 'Curso de Marketing Digital', 'Este curso de Machine Learning es excepcional.', 4, CURRENT_DATE);

INSERT INTO enrol (user_username, course_id, date, price, review_id)
VALUES
    ('usuario5', 'fff6e217-7851-4d8d-8cc7-73290d0f96ed', CURRENT_DATE, 99.00, 101),
    ('usuario5', '70e3ca87-9b86-4827-bc2c-daa8fe26a36f', CURRENT_DATE, 89.00, 102),
    ('usuario6', 'c0805b8c-1f4c-45c7-9776-bbcf6e1a1a7d', CURRENT_DATE, 149.00, 103),
    ('usuario7', 'fff6e217-7851-4d8d-8cc7-73290d0f96ed', CURRENT_DATE, 129.00, 104),
    ('usuario8', 'c0805b8c-1f4c-45c7-9776-bbcf6e1a1a7d', CURRENT_DATE, 149.00, 105),
    ('usuario9', '70e3ca87-9b86-4827-bc2c-daa8fe26a36f', CURRENT_DATE, 99.00, 106),
    ('usuario10', 'fff6e217-7851-4d8d-8cc7-73290d0f96ed', CURRENT_DATE, 89.00, 107);