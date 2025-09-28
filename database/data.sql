CREATE SEQUENCE SEQ_USER START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_PROJECT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_TASK START WITH 1 INCREMENT BY 1;

INSERT INTO USERS (ID, NAME, EMAIL, ROLE)
VALUES (SEQ_USER.NEXTVAL, 'Juan Pérez', 'juan.perez@example.com', 'Manager');
INSERT INTO USERS (ID, NAME, EMAIL, ROLE)
VALUES (SEQ_USER.NEXTVAL, 'Ana García', 'ana.garcia@example.com', 'Developer');
INSERT INTO USERS (ID, NAME, EMAIL, ROLE)
VALUES (SEQ_USER.NEXTVAL, 'Luis Torres', 'luis.torres@example.com', 'Tester');

INSERT INTO PROJECT (ID, NAME, DESCRIPTION, START_DATE, END_DATE)
VALUES (SEQ_PROJECT.NEXTVAL, 'Sistema de Inventarios', 'Proyecto para controlar inventarios', TO_DATE('2025-10-01', 'YYYY-MM-DD'), TO_DATE('2026-01-15', 'YYYY-MM-DD'));
INSERT INTO PROJECT (ID, NAME, DESCRIPTION, START_DATE, END_DATE)
VALUES (SEQ_PROJECT.NEXTVAL, 'Sistema de Ventas', 'Proyecto para gestionar ventas en línea', TO_DATE('2025-11-10', 'YYYY-MM-DD'), NULL);

INSERT INTO TASK (ID, TITLE, DESCRIPTION, STATUS, PROJECT_ID, ASSIGNED_USER_ID)
VALUES (SEQ_TASK.NEXTVAL, 'Diseñar base de datos', 'Diseñar la estructura inicial de la base de datos', 'Pending', 1, 2);
INSERT INTO TASK (ID, TITLE, DESCRIPTION, STATUS, PROJECT_ID, ASSIGNED_USER_ID)
VALUES (SEQ_TASK.NEXTVAL, 'Implementar backend', 'Desarrollar el backend con Java y Oracle', 'In Progress', 1, 2);
INSERT INTO TASK (ID, TITLE, DESCRIPTION, STATUS, PROJECT_ID, ASSIGNED_USER_ID)
VALUES (SEQ_TASK.NEXTVAL, 'Pruebas iniciales', 'Realizar pruebas básicas de funcionalidad', 'Pending', 1, 3);