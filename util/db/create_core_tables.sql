--da fare politiche di cascade
--aggiungere vincoli ad esempio industryid solo numerici

CREATE TABLE Users (
	username VARCHAR(30) PRIMARY KEY,
	password VARCHAR(40) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	nome VARCHAR(20),
	cognome VARCHAR(20),
	ruolo VARCHAR(10)
);

CREATE TABLE Libro (
	industryid VARCHAR(30) PRIMARY KEY,
	titolo TEXT NOT NULL,
	descrizione TEXT,
	imgurl TEXT,
	categoria TEXT
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
	user_username VARCHAR(30) REFERENCES Users(username),
	condizioni INTEGER CHECK(condizioni>=1 AND condizioni<=5),
	voto INTEGER CHECK(voto>=1 AND voto<=10),
	recensione TEXT,
	PRIMARY KEY(libro_industryid,user_username)
);

CREATE TABLE PosGeograficaFake (
	user_username VARCHAR(30) REFERENCES Users(username) PRIMARY KEY,
	x REAL NOT NULL,
	y REAL NOT NULL
);

CREATE TABLE LoginRecord (
	user_username VARCHAR(30) REFERENCES Users(username),
	logintime TIMESTAMP,
	provincia CHAR(2),
	PRIMARY KEY(user_username,logintime)
);

CREATE TABLE Ricerche (
	ricercaid SERIAL PRIMARY KEY,
	ricercachiave TEXT
);