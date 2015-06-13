--da fare politiche di cascade

INSERT INTO Autore
VALUES ('asimov'), ('manzoni'), ('alighieri');

INSERT INTO Libro
VALUES  ('1234567891010','Divina Commedia nello spazio'),
		('1234567891012','Divina Commedia'),
		('1234567891011','Divina Commedia a Como');
		
INSERT INTO ScrittoDa
VALUES  ('1234567891011', 'alighieri'),
		('1234567891011', 'manzoni'),
		('1234567891010', 'alighieri'),
		('1234567891010', 'asimov'),
		('1234567891012', 'alighieri');