DROP DATABASE IF EXISTS `Bibliotheque`;

CREATE DATABASE`Bibliotheque`;
use bibliotheque;

CREATE TABLE `Admins` (
    `admin_id` INT AUTO_INCREMENT,
    `nom` VARCHAR(15) NOT NULL,
    `prenom` VARCHAR(15) NOT NULL,
    `login` VARCHAR(20) NOT NULL UNIQUE,
    `password` VARCHAR(20) NOT NULL,
    CONSTRAINT admin_pk PRIMARY KEY (`admin_id`)
)  ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `Etudiants` (
    `etudiant_id` INT AUTO_INCREMENT,
    `nom` VARCHAR(15) NOT NULL,
    `prenom` VARCHAR(15) NOT NULL,
    `login` VARCHAR(20) NOT NULL UNIQUE,
    `password` VARCHAR(20) NOT NULL,
    `filiere_id` INT NOT NULL,
    CONSTRAINT etudiant_pk PRIMARY KEY (`etudiant_id`)
)  ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `Filieres` (
    `filiere_id` INT AUTO_INCREMENT,
    `filiere` VARCHAR(30) NOT NULL,
    `description` VARCHAR(200),
    CONSTRAINT filiere_pk PRIMARY KEY (`filiere_id`)
)  ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `Livres` (
    `livre_id` INT AUTO_INCREMENT,
    `titre` VARCHAR(30) NOT NULL,
    `langue_id` INT NOT NULL,
    `nmbr_page` INT,
    `nmbr_copies` INT DEFAULT 1,
    `categorie_id` INT NOT NULL,
    CONSTRAINT livre_pk PRIMARY KEY (`livre_id`)
)  ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;


CREATE TABLE `Emprunts` (
    `etudiant_id` INT NOT NULL,
    `livre_id` INT NOT NULL,
    `date_emprint` DATE DEFAULT (CURRENT_DATE),
	unique (`etudiant_id`, `livre_id`) -- a student can borrow only one copy of a certain book
)  ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `Categories` (
    `categorie_id` INT AUTO_INCREMENT,
    `categorie` VARCHAR(30) NOT NULL UNIQUE,
    CONSTRAINT categorie_pk PRIMARY KEY (`categorie_id`)
)  ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `Auteur_livre_Connections` (
    `livre_id` INT NOT NULL,
    `auteur_id` INT NOT NULL,
    UNIQUE (`livre_id` , `auteur_id`)
)  ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `Auteurs` (
    `auteur_id` INT AUTO_INCREMENT,
    `nom` VARCHAR(15) NOT NULL,
    `prenom` VARCHAR(15) NOT NULL,
    `nationalite` VARCHAR(60) NOT NULL,
    CONSTRAINT auteur_pk PRIMARY KEY (`auteur_id`)
)  ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `Langues` (
    `langue_id` INT AUTO_INCREMENT,
    `langue` VARCHAR(30) NOT NULL UNIQUE,
    CONSTRAINT langue_pk PRIMARY KEY (`langue_id`)
)  ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

-- ##############################################################################
-- 								Foreign Keys
-- ##############################################################################

ALTER TABLE `Etudiants`
ADD CONSTRAINT etudiant_filiere_fk FOREIGN KEY (`filiere_id`)
			   REFERENCES `Filieres` (`filiere_id`);

ALTER TABLE `Emprunts`
ADD CONSTRAINT emprunt_etudiant_fk FOREIGN KEY (`etudiant_id`)
			   REFERENCES `Etudiants` (`etudiant_id`);

ALTER TABLE `Emprunts`
ADD CONSTRAINT emprunt_livre_fk FOREIGN KEY (`livre_id`)
			   REFERENCES `Livres` (`livre_id`);

ALTER TABLE `Livres`
ADD CONSTRAINT livre_categorie_fk FOREIGN KEY (`categorie_id`)
			   REFERENCES `Categories` (`categorie_id`);

ALTER TABLE `Livres`
ADD CONSTRAINT livre_langue_fk FOREIGN KEY (`langue_id`)
			   REFERENCES `Langues` (`langue_id`);

ALTER TABLE `Auteur_livre_Connections`
ADD CONSTRAINT alc_livre_fk FOREIGN KEY (`livre_id`)
			   REFERENCES `Livres` (`livre_id`);

ALTER TABLE `Auteur_livre_Connections`
ADD CONSTRAINT alc_auteur_fk FOREIGN KEY (`auteur_id`)
			   REFERENCES `Auteurs` (`auteur_id`);


-- ##############################################################################
-- 									Views
-- ##############################################################################

CREATE OR REPLACE VIEW livre_nombre_Emprunts AS
    SELECT 
        li.livre_id, COUNT(*) AS 'nmbr_copies_empruntes'
    FROM
        livres AS li
            INNER JOIN
        Emprunts AS e ON li.livre_id = e.livre_id
    GROUP BY livre_id
;

CREATE OR REPLACE VIEW Livres_Infos_Complets AS
    SELECT 
        li.livre_id,
        li.titre,
        c.categorie,
        la.langue,
        li.nmbr_page,
        li.nmbr_copies
    FROM
        livres AS li
            INNER JOIN
        categories AS c ON li.categorie_id = c.categorie_id
            INNER JOIN
        langues AS la ON li.langue_id = la.langue_id
;

CREATE OR REPLACE VIEW Livres_emprinte_Infos_Complets AS
    SELECT 
        li.livre_id,
        li.titre,
        c.categorie,
        la.langue,
        li.nmbr_page,
        li.nmbr_copies,
        lnr.nmbr_copies_empruntes
    FROM
        livres AS li
            INNER JOIN
        categories AS c ON li.categorie_id = c.categorie_id
            INNER JOIN
        langues AS la ON li.langue_id = la.langue_id
            INNER JOIN
        livre_nombre_Emprunts AS lnr ON li.livre_id = lnr.livre_id
;


create or replace view Etudiants_Infos_Complets AS
    SELECT 
        e.etudiant_id,
        e.nom,
        e.prenom,
        e.login,
        e.password,
        f.filiere
    FROM
        etudiants as e
        inner join
        filieres as f
        on e.filiere_id = f.filiere_id
;

-- ##############################################################################
-- 								default admin
-- ##############################################################################
INSERT INTO Admins (`nom`, `prenom`, `login`, `password`)
VALUES ('admin', 'admin', 'admin', 'admin');

-- ##############################################################################
-- 								Filiere
-- ##############################################################################
INSERT INTO filieres (`filiere`, `description`)
VALUES ('sma', 'Science Math Appliquer');

INSERT INTO filieres (`filiere`, `description`)
VALUES ('smi', 'Science Math Informatique');

INSERT INTO filieres (`filiere`, `description`)
VALUES ('smp', 'Science Math Physique');

-- ##############################################################################
-- 								Categories
-- ##############################################################################
INSERT INTO Categories (`categorie`)
VALUES ('reference');
INSERT INTO Categories (`categorie`)
VALUES ('manuel');
INSERT INTO Categories (`categorie`)
VALUES ('Biographie');
INSERT INTO Categories (`categorie`)
VALUES ('poche');
INSERT INTO Categories (`categorie`)
VALUES ('programmation');

-- ##############################################################################
-- 								Langues
-- ##############################################################################
insert into `Langues` (`langue`)
values ('français');
insert into `Langues` (`langue`)
values ('english');
insert into `Langues` (`langue`)
values ('arabic');
insert into `Langues` (`langue`)
values ('espaniol');
select * from langues;
-- ##############################################################################
-- 								Livres
-- ##############################################################################
INSERT INTO livres (`titre`, `langue_id`, `nmbr_page`, `nmbr_copies`, `categorie_id`)
VALUES ('C++ for the Rest of Us', 3, 500, 10, 6);
select * from livres;
select * from livres_infos_complets;