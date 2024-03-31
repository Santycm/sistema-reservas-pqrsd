use ConjuntoResidencial

CREATE TABLE tblTorres(
	NTorre int primary key
)

CREATE TABLE tblApartamentos(
	NApartamento int PRIMARY KEY,
	NTorre int,
	FOREIGN KEY (NTorre) REFERENCES tblTorres(NTorre)
)
/*NOTA: Las tablas torres y apartamentos están preestablecidos desde una de las historias de usuario*/

CREATE TABLE tblResidentes(
	IDResidente int primary key,
	NombreR varchar(30),
	ApellidoR varchar(30)
)

CREATE TABLE tblHorarios(
IDHorario int primary key,
Inicio time,
Fin time,
)

CREATE TABLE tblRZonaHumeda(
	IDReserva int identity primary key,
	IDResidente int,
	NApartamento int,
	NTorre int,
	Fecha date,
	IDHorario int,
	FOREIGN KEY(IDResidente) REFERENCES tblResidentes(IDResidente),
	FOREIGN KEY(IDHorario) REFERENCES tblHorarios(IDHorario),
	FOREIGN KEY(NApartamento) REFERENCES tblApartamentos(NApartamento),
	FOREIGN KEY(NTorre) REFERENCES tblTorres(NTorre)
)

/*PROCEDURE PARA VALIDAR HORARIOS DISPONIBLES*/
GO
CREATE PROCEDURE sp_DescartarHorarios
@Fecha date
AS
Select tblHorarios.IDHorario from tblHorarios inner join tblRZonaHumeda on tblHorarios.IDHorario = tblRZonaHumeda.IDHorario where tblRZonaHumeda.Fecha in(@Fecha)

EXEC sp_DescartarHorarios '2023-10-05'

/*PROCEDURE PARA INGRESO DE RESERVAS*/
GO
CREATE PROCEDURE sp_IngresoReservas
@IDResidente int,
@NApartamento int,
@NTorre int,
@Fecha date,
@IDHorario int
AS
INSERT INTO tblRZonaHumeda
VALUES(@IDResidente, @NApartamento, @NTorre, @Fecha, @IDHorario)

/*PROCEDURE PARA MOSTRAR LA LISTA DE RESERVAS*/
GO
CREATE PROCEDURE sp_MostrarReservas
AS
SELECT tblResidentes.NombreR, tblRZonaHumeda.Fecha, tblHorarios.Inicio, tblHorarios.Fin from tblResidentes inner join tblRZonaHumeda on tblResidentes.IDResidente = tblRZonaHumeda.IDResidente inner join tblHorarios on tblHorarios.IDHorario = tblRZonaHumeda.IDHorario


--DATOS PREESTABLECIDOS--
/*INGRESO DE APARTAMENTOS Y TORRES(HISTORIA DE USUARIO APARTE)*/
INSERT INTO tblTorres
	VALUES(1),(2)

INSERT INTO tblApartamentos
	VALUES(1215, 1),(1216, 1), (1217, 1), (1219, 1), (1220, 2)

/*INGRESO DE DATOS DE RESIDENTES (HISTORIA DE USUARIO APARTE)*/
INSERT INTO tblResidentes
VALUES(70456789, 'Juanito', 'Perez'), (71456725, 'Manuel', 'Gómez')

/*INGRESO DE HORARIOS PREESTABLECIDOS*/
INSERT INTO tblHorarios
VALUES
/*UNA HORA*/
(1, '9:00', '9:59'),
(2, '10:00', '10:59'),
(3, '11:00', '11:59'),
(4, '12:00', '12:59'),
(5, '13:00', '13:59'),
(6, '14:00', '14:59'),
(7, '15:00', '15:59'),
(8, '16:00', '16:59'),

/*BLOQUES 2 HORAS*/
(9, '9:00', '10:59'),
(10, '11:00', '12:59'),
(11, '13:00', '14:59'),
(12, '15:00', '16:59')



