--da fare politiche di cascade
--aggiungere vincoli ad esempio industryid solo numerici

CREATE TABLE Users (
	username VARCHAR(20) PRIMARY KEY,
	password VARCHAR(50) NOT NULL,
	email VARCHAR(30) NOT NULL,
	nome VARCHAR(20),
	cognome VARCHAR(20)
);

CREATE TABLE Libro (
	industryid VARCHAR(30) PRIMARY KEY,
	titolo TEXT NOT NULL,
	descrizione TEXT,
	imgurl TEXT
);

CREATE TABLE Autore (
	nome VARCHAR(30) PRIMARY KEY
);

CREATE TABLE ScrittoDa (
	libro_industryid TEXT REFERENCES Libro(industryid),
	autore_nome VARCHAR(30) REFERENCES Autore(nome),
	PRIMARY KEY(libro_industryid,autore_nome)
);

CREATE TABLE LibroPosseduto (
	libro_industryid VARCHAR(13) REFERENCES Libro(industryid),
	user_username VARCHAR(20) REFERENCES Users(username)
);

CREATE TABLE PosGeograficaFake (
	user_username VARCHAR(20) REFERENCES Users(username) PRIMARY KEY,
	x REAL NOT NULL,
	y REAL NOT NULL
);