-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-04-2024 a las 05:42:27
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `authors`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `author`
--

CREATE TABLE `author` (
  `idAuthor` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `generoLiterario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `author`
--

INSERT INTO `author` (`idAuthor`, `nombre`, `telefono`, `fechaNacimiento`, `generoLiterario`) VALUES
(4, 'Alvaro Ventura', '6018-1813', '1989-03-20', 1),
(5, 'Claudia Lars', '6018-1813', '1989-03-22', 1),
(6, 'Mario Vargas', '6018-1813', '1989-03-22', 1),
(7, 'Gabriela Mistral', '6018-1813', '1989-03-22', 2),
(8, 'Gabriel Garcia', '6018-1813', '1989-03-21', 2),
(9, 'George Orwell', '6018-1818', '1989-03-22', 5),
(10, 'Virginia Woolf', '6018-1813', '1989-03-22', 6),
(11, 'Miguel de Cervantes', '7005-6018', '1990-07-29', 4),
(12, 'Johns Green', '6018-1813', '2024-05-24', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`idAuthor`),
  ADD KEY `generoLiterario` (`generoLiterario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `author`
--
ALTER TABLE `author`
  MODIFY `idAuthor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `author`
--
ALTER TABLE `author`
  ADD CONSTRAINT `FK_literarygenre` FOREIGN KEY (`generoLiterario`) REFERENCES `literarygenre` (`id_genero`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
