INSERT into role(id , nom) values
(1, 'ROLE_USER'),
(2,'ROLE_REDACTEUR'),
(3,'ROLE_ADMIN');


INSERT INTO utilisateur (id,nom,prenom,mdp) VALUES
(1,"Keibler","Eric","Jesus"),
(2,"Codinera","Sarah","Jesus"),
(3,"Codi","J","$2a$10$M3TUgqBvGcNVajOnvVpri.ZwzMhwt1iM1eoHB2XVbje/a.NGwD2NG"),
(4,"Tom","S","$2a$10$IkcSz.JoAnsC9Hp9Sh7B/evGp4Yn1b7vEkwm8sU5lrZ95c7kkx.4e");
Insert into role_utilisateur (utilisateur_id, role_id) values
(1,1),
(1,3),
(2,1),
(2,2),
(3,1),
(4,1);


INSERT INTO marque (id,nom) VALUES
(1,"DELL"),
(2,"BIC");

INSERT INTO materiel (id,nom,code, id_marque) VALUES
(1,"Ecran","ECRANDELL001",1),
(2,"Claier","CLAVIERDELL",1),
(3,"Marqueur rouge", "MARQUEURROUGE",2);

INSERT INTO specificite (id,nom) VALUES
(1,'A cadenacer'),
(2,'fragile');

INSERT INTO materiel_specificite (materiel_id, specificite_id) VALUES
(1, 1),
(1, 2),
(2, 2);

INSERT INTO reservation (date,materiel_id, emprunteur_id) VALUES
('2022-02-01', 1 , 2 ),
('2022-02-02', 2 , 1 );

