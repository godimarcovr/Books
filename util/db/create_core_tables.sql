--da fare politiche di cascade

CREATE TABLE Users (
	username VARCHAR(20) PRIMARY KEY,
	password VARCHAR(50) NOT NULL,
	email VARCHAR(30) NOT NULL
);

CREATE TABLE Libro (
	isbn VARCHAR(13) CHECK(char_length(isbn)>12) PRIMARY KEY,
	titolo VARCHAR(50)
);

CREATE TABLE Autore (
	nome VARCHAR(30) PRIMARY KEY
);

CREATE TABLE ScrittoDa (
	libro_isbn VARCHAR(13) REFERENCES Libro(isbn),
	autore_nome VARCHAR(30) REFERENCES Autore(nome)
);

CREATE TABLE LibroPosseduto (
	libro_isbn VARCHAR(13) REFERENCES Libro(isbn),
	user_username VARCHAR(20) REFERENCES Users(username)
);

CREATE TABLE PosGeograficaFake (
	user_username VARCHAR(20) REFERENCES Users(username) PRIMARY KEY,
	x REAL NOT NULL,
	y REAL NOT NULL
);