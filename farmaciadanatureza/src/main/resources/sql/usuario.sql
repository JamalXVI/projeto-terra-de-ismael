#####################################################
#https://www.dailycred.com/article/bcrypt-calculator#
#####################################################
#################POPULAR USUÁRIO#####################
#####################################################
USE FARMACIA_DA_NATUREZA;
INSERT INTO AUTORIDADE(AUT_AUT) VALUES ('ROLE_USUARIO');
INSERT INTO AUTORIDADE(AUT_AUT) VALUES ('ROLE_ADMIN');
INSERT INTO PESSOA(nom_pes, sob_pes, cpf_pes) VALUES ('Henrique', 'Arantes Tiraboschi',
 '233.257.678-93');
INSERT INTO USUARIO(atv_usr, sen_usr, usr_usr, id_pes) VALUES(1,
 "$2a$04$UIeR96XNyDwQHJE4G2DWquYMZmopzZ0z5o5f1bc2KA.YgFhGU7/aW", "admin", 1);
INSERT INTO USUARIO_AUTORIDADE(ID_USR, ID_AUT) VALUES (1,1);
INSERT INTO USUARIO_AUTORIDADE(ID_USR, ID_AUT) VALUES (1,2);
UPDATE PESSOA SET ID_USR = 1 WHERE ID_PES = 1;
