delete from SIDEEFFECTS;
delete from VACCINES;
delete from user_authority;
delete from USER;
delete from AUTHORITY;



INSERT INTO VACCINES(ID,researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (1,'BNT162b2', 'Comirnaty', 'mRNA-based vaccine',2,13000000);
INSERT INTO VACCINES(ID,researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (2,'mRNA1273', 'Moderna  Vaccine', 'mRNA-based vaccine', 2, 50000000);
INSERT INTO VACCINES(ID,researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (3,'AZD1222', 'Vaccine AstraZeneca', 'Adenovirus vaccine', 2, 30000000);
INSERT INTO VACCINES(ID,researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (4,'SputnikV', 'Sputnik V', 'Recombinant adenovirus vaccine (rAd26 and rAd5)', 2, 10000000);
INSERT INTO VACCINES(ID,researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (5,'JNJ', 'Vaccine Janssen ', 'Non-replicating viral vector', 1, 100000000);
INSERT INTO VACCINES(ID,researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (6,'CoronaVac', 'CoronaVac', 'Inactivated vaccine (formalin with alum adjuvant)', 2, 802000000);
INSERT INTO VACCINES(ID,researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (7,'BBIBPCorV', 'BBIBP-CorV', 'Inactivated vaccine', 2, 100000000);
INSERT INTO VACCINES(ID,researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (8,'EpiVacCorona', 'EpiVacCorona', 'Peptide vaccine',2,20000000);
INSERT INTO VACCINES(ID,researchName,manufacturerName,type,numberOfShots,availableDoses) VALUES (9,'Covaxin', 'Covaxin', 'Inactivated vaccine',5,2000000);
INSERT INTO SIDEEFFECTS(ID,vaccineID,description,longDescription,frequency) VALUES (1,1,'Crvenilo','Crvenilo,natečenost i bol na ruci gdje je primjenjeno cjepivo',15);
INSERT INTO SIDEEFFECTS(ID,vaccineID,description,longDescription,frequency) VALUES (2,2,'Crvenilo tip2','Crvenilo,natečenost i bol na ruci gdje je primjenjeno cjepivo',26);
INSERT INTO SIDEEFFECTS(ID,vaccineID,description,longDescription,frequency) VALUES (3,3,'Temperatura','temperatura i drhatavica',15);
INSERT INTO SIDEEFFECTS(ID,vaccineID,description,longDescription,frequency) VALUES (4,4,'Crvenilo tip3','Crvenilo,natečenost i bol na ruci gdje je primjenjeno cjepivo',49);
INSERT INTO SIDEEFFECTS(ID,vaccineID,description,longDescription,frequency) VALUES (5,5,'Glavobolja','Glavobolja,umor,bolovi u mišičima,temperatura i drhatavica',42);
INSERT INTO SIDEEFFECTS(ID,vaccineID,description,longDescription,frequency) VALUES (6,6,'Crvenilo tip4','Crvenilo,natečenost i bol na ruci gdje je primjenjeno cjepivo',21);
INSERT INTO SIDEEFFECTS(ID,vaccineID,description,longDescription,frequency) VALUES (7,7,'Glavobolja tip2','Glavobolja,umor,bolovi u mišičima,temperatura i drhatavica',14);
INSERT INTO SIDEEFFECTS(ID,vaccineID,description,longDescription,frequency) VALUES (8,8,'Crvenilo tip5','Crvenilo,natečenost i bol na ruci gdje je primjenjeno cjepivo',24);
INSERT INTO SIDEEFFECTS(ID,vaccineID,description,longDescription,frequency) VALUES (9,9,'Glavobolja tip3','Glavobolja,umor,bolovi u mišičima,temperatura i drhatavica',42);
INSERT INTO AUTHORITY(id,name)VALUES(1,'ROLE_ADMIN');
INSERT INTO AUTHORITY(id,name)VALUES(2,'ROLE_USER');
INSERT INTO AUTHORITY(id,name)VALUES(3,'ROLE_CREATOR');
INSERT INTO USER(id,username,password,first_name,last_name) VALUES (1,'admin','$2y$12$y0gfO9IK3UocBHdHy/QL7.23faf6B6lLX5hNV4t4uUEeQFZtfujHu','admin','admin');
INSERT INTO USER(id,username,password,first_name,last_name) VALUES (2,'user','$2y$12$3iDZAWd60vF6vyWAJqscB.a7wItqeIvYBJM9Am1R6/ZpxzPvG75Uy','user','user');
INSERT INTO USER(id,username,password,first_name,last_name) VALUES (3,'creator','$2y$12$ut.BwxA/yO1muaAWaaLzUukLwvhD5B9EfpmJIM.UTSCke6B9JAMeS','creator','creator');
INSERT INTO user_authority(user_id,authority_id) values (1,1);
INSERT INTO user_authority(user_id,authority_id) values (2,2);
INSERT INTO user_authority(user_id,authority_id) values (3,3);