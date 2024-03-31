CREATE DATABASE ConjuntoResidencial
use ConjuntoResidencial


CREATE TABLE tblTorres(
	NTorre int primary key
)

CREATE TABLE tblApartamentos(
	NApartamento int PRIMARY KEY,
	NTorre int,
	FOREIGN KEY (NTorre) REFERENCES tblTorres(NTorre)
)

/*Las tablas torres y apartamentos están preestablecidos desde una de las historias de usuario*/

CREATE TABLE tblDatosPQRSD(
	NumeroID varchar(20) primary key,
	TipoID varchar(20),
	Nombres varchar(50),
	Apellidos varchar(50),
	Correo varchar(30),
	Telefono varchar(13),
)

CREATE TABLE tblArchivosPQRSD(
IDArchivo int identity primary key,
Archivo VARBINARY(MAX)
)

CREATE TABLE tblEstadoPQRSD(
IDEstado int primary key,
Estado varchar(15),
)

CREATE TABLE tblPQRSD(
	IDRegistroPQRSD int identity primary key,
	Fecha datetime,
	NApartamento int,
	NTorre int,
	TipoPQRSD varchar(18),
	NumeroID varchar(20),
	Asunto varchar(30),
	Mensaje varchar(500),
	MRespuesta varchar(20),
	IDArchivo int,
	IDEstado int,
	FOREIGN KEY (NumeroID) REFERENCES tblDatosPQRSD(NumeroID),
	FOREIGN KEY (NApartamento) REFERENCES tblApartamentos(NApartamento),
	FOREIGN KEY (NTorre) REFERENCES tblTorres(NTorre),
	FOREIGN KEY (IDArchivo) REFERENCES tblArchivosPQRSD(IDArchivo),
	FOREIGN KEY (IDEstado) REFERENCES tblEstadoPQRSD(IDEstado)
)

CREATE TABLE tblComentarios(
IDRegistroPQRSD int primary key,
Fecha datetime,
IDR int,
NombreR varchar(30),
Comentario varchar(500)
FOREIGN KEY(IDRegistroPQRSD) REFERENCES tblPQRSD(IDRegistroPQRSD)
)

CREATE TABLE tblArchivosRespuesta(
IDArchivoR int identity primary key,
Archivo VARBINARY(MAX)
)

CREATE TABLE tblRespuestas(
IDRegistroPQRSD int primary key,
Fecha datetime,
IDR int,
NombreR varchar(30),
Respuesta varchar(500),
IDArchivoR int
FOREIGN KEY(IDArchivoR) REFERENCES tblArchivosRespuesta(IDArchivoR)
)

/*INGRESO DE PQRSD*/
GO
CREATE PROCEDURE sp_IngresoPQRS
	@NApartamento int,
	@NTorre int,
	@TipoPQRSD varchar(18),
	@NumeroID varchar(20),
	@Asunto varchar(30),
	@Mensaje varchar(500),
	@MRespuesta varchar(20)
AS
declare @IDArchivo int
SELECT TOP 1 @IDArchivo = IDArchivo FROM tblArchivosPQRSD ORDER BY IDArchivo DESC
INSERT INTO tblPQRSD 
VALUES (SYSDATETIME(), @NApartamento, @NTorre, @TipoPQRSD, @NumeroID, @Asunto, @Mensaje, @MRespuesta, @IDArchivo, 1)

/*INGRESO DE RESPUESTA*/
GO
CREATE PROCEDURE sp_IngresoRespuesta
	@IDPQRSD int,
	@IDR int,
	@NombreR varchar(30),
	@Respuesta varchar(500)
AS
	declare @IDArchivoR int
	SELECT TOP 1 @IDArchivoR = IDArchivoR FROM tblArchivosRespuesta ORDER BY IDArchivoR DESC
	INSERT INTO tblRespuestas
	VALUES (@IDPQRSD, SYSDATETIME(), @IDR, @NombreR, @Respuesta, @IDArchivoR)

/*ACTUALIZACION DE ESTADO DE PQRSD*/
GO
CREATE TRIGGER tr_CambioEstado
ON tblRespuestas
FOR INSERT
AS
DECLARE @IDPQRSD INT
BEGIN
	SELECT @IDPQRSD = IDRegistroPQRSD FROM inserted
	UPDATE tblPQRSD SET IDEstado = 2 WHERE IDRegistroPQRSD = @IDPQRSD
END

/*EXTRACCION DE INFO PQRSD*/
GO
CREATE PROCEDURE sp_ListadoPQRSD
AS
SELECT 
	IDRegistroPQRSD AS 'N° RADICADO',
	Fecha AS 'FECHA', 
	TipoPQRSD AS 'TIPO DE PQRSD', 
	NumeroID AS 'N° IDENTIFICACIÓN', 
	Asunto AS 'ASUNTO', 
	IDArchivo 'CODIGO ARCHIVO', 
	tblEstadoPQRSD.Estado AS 'ESTADO'
FROM tblPQRSD INNER JOIN tblEstadoPQRSD ON tblPQRSD.IDEstado = tblEstadoPQRSD.IDEstado


--DATOS PREESTABLECIDOS--
/*ESTABLECER ESTADOS DE PQRSD*/
INSERT INTO tblEstadoPQRSD
VALUES(1, 'En proceso'), (2, 'Finalizada')

/*VALOR UNICO DE ARCHIVO INEXISTENTE*/
--Insercion de 0 como archivo inexistente
SET IDENTITY_INSERT tblArchivosPQRSD ON;
INSERT INTO tblArchivosPQRSD (IDArchivo)
VALUES (0)
SET IDENTITY_INSERT tblArchivosPQRSD OFF;
--Insercion de 0 como archivo inexistente
SET IDENTITY_INSERT tblArchivosRespuesta ON;
INSERT INTO tblArchivosRespuesta (IDArchivoR)
VALUES (0)
SET IDENTITY_INSERT tblArchivosRespuesta OFF;


/*INGRESO DE APARTAMENTOS Y TORRES(HISTORIA DE USUARIO APARTE)*/
INSERT INTO tblTorres
	VALUES(1),(2)

INSERT INTO tblApartamentos
	VALUES(1215, 1),(1216, 1), (1217, 1), (1219, 1), (1220, 2)

	
--REVISION DE TABLAS--
SELECT * FROM tblApartamentos
SELECT * FROM tblTorres
SELECT * FROM tblArchivosPQRSD
SELECT * FROM tblDatosPQRSD
SELECT * FROM tblPQRSD
SELECT * FROM tblComentarios
SELECT * FROM tblArchivosRespuesta
SELECT * FROM tblRespuestas



