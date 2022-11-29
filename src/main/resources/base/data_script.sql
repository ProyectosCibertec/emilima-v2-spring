USE `emilima`;

DELETE FROM `user_role`;
DELETE FROM `request_state`;
DELETE FROM `file`;
DELETE FROM `hierarchical_dependency`;
DELETE FROM `document_type`;
DELETE FROM `organic_unit`;

INSERT INTO `user_role`(`name`, `description`) VALUES ("Administrador", "Usuario con permisos globales.");
INSERT INTO `user_role`(`name`, `description`) VALUES ("Unidad orgánica", "Usuario con capacidad de registrar solicitudes de documentación.");
INSERT INTO `user_role`(`name`, `description`) VALUES ("Técnico", "Usuario con capacidad de administrar solicitudes y documentos.");
INSERT INTO `user_role`(`name`, `description`) VALUES ("Secretario general", "Usuario con capacidad de autorizar solicitudes y administrar las entidades.");

INSERT INTO `request_state`(`name`) VALUES ("PENDIENTE");
INSERT INTO `request_state`(`name`) VALUES ("VALIDADA");
INSERT INTO `request_state`(`name`) VALUES ("AUTORIZADA");
INSERT INTO `request_state`(`name`) VALUES ("ATENDIDA");

INSERT INTO `file`(`id`, `filename`) VALUES ("dca3a58c-ef10-11ec-8ea0-0242ac120002", "ejemplo.pdf");
INSERT INTO `file`(`id`, `filename`) VALUES ("e2d96144-ef10-11ec-8ea0-0242ac120002", "ejemplo.pdf");
INSERT INTO `file`(`id`, `filename`) VALUES ("eb535816-ef10-11ec-8ea0-0242ac120002", "ejemplo.pdf");
INSERT INTO `file`(`id`, `filename`) VALUES ("f0783f8c-ef10-11ec-8ea0-0242ac120002", "ejemplo.pdf");
INSERT INTO `file`(`id`, `filename`) VALUES ("c4042c2a-f106-11ec-8ea0-0242ac120002", "user-photo-default.png");

INSERT INTO `hierarchical_dependency`(`name`) VALUES ("DIRECTORIO");
INSERT INTO `hierarchical_dependency`(`name`) VALUES ("GERENCIA GENERAL");
INSERT INTO `hierarchical_dependency`(`name`) VALUES ("ÓRGANO DE CONTROL INSTITUCIONAL");
INSERT INTO `hierarchical_dependency`(`name`) VALUES ("GERENCIA DE ASUNTOS LEGALES");
INSERT INTO `hierarchical_dependency`(`name`) VALUES ("GERENCIA DE ATENCIÓN AL CIUDADANO, COMUNICACIONES Y TECNOLOGÍA DE LA INFORMACIÓN");
INSERT INTO `hierarchical_dependency`(`name`) VALUES ("GERENCIA DE ADMINISTRACIÓN Y FINANZAS");

INSERT INTO `document_type`(`name`) VALUES ("Resolución");
INSERT INTO `document_type`(`name`) VALUES ("Acta");
INSERT INTO `document_type`(`name`) VALUES ("Memorando");
INSERT INTO `document_type`(`name`) VALUES ("Informe");
INSERT INTO `document_type`(`name`) VALUES ("Plan de Trabajo");
INSERT INTO `document_type`(`name`) VALUES ("Directiva");

INSERT INTO `organic_unit`(`name`) VALUES ("GERENCIA GENERAL");
INSERT INTO `organic_unit`(`name`) VALUES ("ÓRGANO DE CONTROL INSTITUCIONAL");
INSERT INTO `organic_unit`(`name`) VALUES ("GERENCIA DE ASUNTOS LEGALES");
INSERT INTO `organic_unit`(`name`) VALUES ("GERENCIA DE PLANIFICACIÓN, PRESUPUESTO Y MODERNIZACIÓN");
INSERT INTO `organic_unit`(`name`) VALUES ("GERENCIA DE ATENCIÓN AL CIUDADANO, COMUNICACIONES Y TECNOLOGÍA DE LA INFORMACIÓN");
INSERT INTO `organic_unit`(`name`) VALUES ("GERENCIA DE ADMINISTRACIÓN Y FINANZAS");

INSERT INTO `user_position`(`name`, `organic_unit_id`, `hierarchical_dependency_id`) VALUES ("GERENTE GENERAL", 1, 1);
INSERT INTO `user_position`(`name`, `organic_unit_id`, `hierarchical_dependency_id`) VALUES ("GERENTE DE ÓRGANO DE CONTROL INSTITUCIONAL", 1, 1);
INSERT INTO `user_position`(`name`, `organic_unit_id`, `hierarchical_dependency_id`) VALUES ("GERENTE DE ASUNTOS LEGALES", 1, 1);
INSERT INTO `user_position`(`name`, `organic_unit_id`, `hierarchical_dependency_id`) VALUES ("GERENTE DE PLANIFICACIÓN, PRESUPUESTO Y MODERNIZACIÓN", 1, 1);
INSERT INTO `user_position`(`name`, `organic_unit_id`, `hierarchical_dependency_id`) VALUES ("GERENTE DE ATENCIÓN AL CIUDADANO, COMUNICACIONES Y TECNOLOGÍA DE LA INFORMACIÓN", 1, 1);
INSERT INTO `user_position`(`name`, `organic_unit_id`, `hierarchical_dependency_id`) VALUES ("GERENTE DE ADMINISTRACIÓN Y FINANZAS", 1, 1);

SELECT * FROM `documental_serie`;

INSERT INTO `user`(`username`, `password`, `email`, `role_id`, `position_id`) VALUES ("admin", "admin", "admin@emilima.com.pe", 1, 1);
INSERT INTO `user`(`username`, `password`, `email`, `role_id`, `position_id`) VALUES ("admin1", "admin", "admin@emilima.com.pe", 1, 1);
INSERT INTO `user`(`username`, `password`, `email`, `role_id`, `position_id`) VALUES ("user", "admin", "admin@emilima.com.pe", 1, 1);

INSERT INTO `document_request`(`name`, `description`, `creation_date`, `state_id`, `user_id`, `organic_unit_id`) VALUES ("Solicitud 1", "Descripción de la solicitud 1", "2000-11-11", 1, "admin", 1);
INSERT INTO `document_request`(`name`, `description`, `creation_date`, `state_id`, `user_id`, `organic_unit_id`) VALUES ("Solicitud 2", "Descripción de la solicitud 2", "2000-11-11", 1, "admin", 1);
INSERT INTO `document_request`(`name`, `description`, `creation_date`, `state_id`, `user_id`, `organic_unit_id`) VALUES ("Solicitud 3", "Descripción de la solicitud 3", "2000-11-11", 1, "admin", 1);

INSERT INTO `document`(`name`, `description`, `upload_date`, `creation_date`, `file_id`, `document_type_id`, `document_serie_id`, `document_request_id`) VALUES ("Documento 1", "Descripción documento 1", "2022-12-12", "2022-12-12", "dca3a58c-ef10-11ec-8ea0-0242ac120002", 1, "asdf", 17);
INSERT INTO `document`(`name`, `description`, `upload_date`, `creation_date`, `file_id`, `document_type_id`, `document_serie_id`, `document_request_id`) VALUES ("Documento 2", "Descripción documento 2", "2022-12-12", "2022-12-12", "dca3a58c-ef10-11ec-8ea0-0242ac120002", 1, "asdf", 18);
INSERT INTO `document`(`name`, `description`, `upload_date`, `creation_date`, `file_id`, `document_type_id`, `document_serie_id`, `document_request_id`) VALUES ("Documento 3", "Descripción documento 3", "2022-12-12", "2022-12-12", "dca3a58c-ef10-11ec-8ea0-0242ac120002", 1, "asdf", 17);
INSERT INTO `document`(`name`, `description`, `upload_date`, `creation_date`, `file_id`, `document_type_id`, `document_serie_id`, `document_request_id`) VALUES ("Documento 4", "Descripción documento 4", "2022-12-12", "2022-12-12", "dca3a58c-ef10-11ec-8ea0-0242ac120002", 1, "asdf", 18);

SELECT * FROM `user_role`;
SELECT * FROM `request_state`;
SELECT * FROM `file`;
SELECT * FROM `hierarchical_dependency`;
SELECT * FROM `document_type`;
SELECT * FROM `documental_serie`;
SELECT * FROM `document_request`;
SELECT * FROM `organic_unit`;
SELECT * FROM `document`;