-- phpMyAdmin SQL Dump
-- version 2.7.0-pl2
-- http://www.phpmyadmin.net
--
-- Servidor: oraclepr.uco.es
-- Tiempo de generación: 21-11-2020 a las 14:59:32
-- Versión del servidor: 5.1.73
-- Versión de PHP: 5.3.3
--
-- Base de datos: `i82nafea`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PostFlash`
--

DROP TABLE IF EXISTS `PostFlash`;
CREATE TABLE IF NOT EXISTS `PostFlash` (
  `id_flash` int(10) NOT NULL,
  `title` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `body` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `createdAt` datetime NOT NULL,
  `publishedAt` datetime NOT NULL,
  `unavailableAfter` datetime NOT NULL,
  `owner` int(10) NOT NULL,
  `sentTo` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `type` enum('flash') COLLATE utf8_spanish_ci NOT NULL,
  `state` enum('edited','waiting','published','deleted') COLLATE utf8_spanish_ci NOT NULL,
  `topics` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_flash`),
  KEY `FK_PostFlash_owner` (`owner`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `PostFlash`
--

INSERT INTO `PostFlash` VALUES (4, 'MEDIA MARKT Black FRIDAY 2020', '¿Buscas lo último en tecnología e informática? Entonces ¡no te pierdas las ofertas del Black Friday y Cyber Monday 2020 de Media Markt! Encuentra todos los descuentos en el catálogo de ofertas Media Markt actual: Media Markt - Black Friday vigente a parti', '2020-11-19 14:56:39', '2020-11-24 14:56:43', '2020-12-28 14:56:48', 4, '', 'flash', 'waiting', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PostGeneral`
--

DROP TABLE IF EXISTS `PostGeneral`;
CREATE TABLE IF NOT EXISTS `PostGeneral` (
  `id_general` int(10) NOT NULL,
  `title` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `body` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `createdAt` datetime NOT NULL,
  `publishedAt` datetime NOT NULL,
  `unavailableAfter` datetime NOT NULL,
  `owner` int(10) NOT NULL,
  `sentTo` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `type` enum('general') COLLATE utf8_spanish_ci NOT NULL,
  `state` enum('edited','waiting','published','deleted') COLLATE utf8_spanish_ci NOT NULL,
  `topics` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_general`),
  KEY `FK_PostGeneral_owner` (`owner`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `PostGeneral`
--

INSERT INTO `PostGeneral` VALUES (1, 'Cosmopoética, Poetas del Mundo en Córdoba', 'Cada año, en otoño, tiene lugar en Córdoba el Festival Internacional de Poesía Cosmopoética, un evento cultural que propone un amplio programa diseñado para atraer a un público diverso, en el que no falta la música, las conversaciones con narradores, los ', '2020-11-18 14:47:29', '2020-11-21 14:47:43', '2021-11-21 14:47:48', 2, '', 'general', 'published', NULL, 0);
INSERT INTO `PostGeneral` VALUES (5, 'Fecha de entrega de práctica 2', 'Estimados estudiantes,  como sabéis, la próxima semana se inicia la práctica 3. El primer día (próximo lunes/martes) será de explicación (servlets y problema de prácticas).   Dadas las circunstancias que estamos viviendo, donde la presencialidad se está c', '2020-11-09 14:58:21', '2020-11-10 14:58:25', '2020-12-13 14:58:29', 3, '', 'general', 'published', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PostTargeted`
--

DROP TABLE IF EXISTS `PostTargeted`;
CREATE TABLE IF NOT EXISTS `PostTargeted` (
  `id_targeted` int(10) NOT NULL,
  `title` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `body` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `createdAt` datetime NOT NULL,
  `publishedAt` datetime NOT NULL,
  `unavailableAfter` datetime NOT NULL,
  `owner` int(10) NOT NULL,
  `sentTo` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `type` enum('targeted') COLLATE utf8_spanish_ci NOT NULL,
  `state` enum('edited','waiting','published','deleted') COLLATE utf8_spanish_ci NOT NULL,
  `topics` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_targeted`),
  KEY `FK_PostTrageted_owner` (`owner`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `PostTargeted`
--

INSERT INTO `PostTargeted` VALUES (2, 'Convocatoria ERASMUS+ 2021-2022', 'La Universidad de Córdoba convoca PLAZAS para estancias de estudios de Grado y Máster en universidades europeas para el curso académico 2021/2022 en el marco de los acuerdos bilaterales firmados entre la Universidad de Córdoba y sus universidades socias.', '2020-11-10 14:50:40', '2020-11-21 14:50:48', '2020-12-07 14:50:55', 3, '1,2,4', 'targeted', 'published', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PostTargetedTargets`
--

DROP TABLE IF EXISTS `PostTargetedTargets`;
CREATE TABLE IF NOT EXISTS `PostTargetedTargets` (
  `id_targeted` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  PRIMARY KEY (`id_targeted`,`id_user`),
  KEY `FK_PostTargetedTragets_id_user` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `PostTargetedTargets`
--

INSERT INTO `PostTargetedTargets` VALUES (2, 1);
INSERT INTO `PostTargetedTargets` VALUES (2, 2);
INSERT INTO `PostTargetedTargets` VALUES (2, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PostThemed`
--

DROP TABLE IF EXISTS `PostThemed`;
CREATE TABLE IF NOT EXISTS `PostThemed` (
  `id_themed` int(10) NOT NULL,
  `title` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `body` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `createdAt` datetime NOT NULL,
  `publishedAt` datetime NOT NULL,
  `unavailableAfter` datetime NOT NULL,
  `owner` int(10) NOT NULL,
  `sentTo` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `type` enum('themed') COLLATE utf8_spanish_ci NOT NULL,
  `state` enum('edited','waiting','published','deleted') COLLATE utf8_spanish_ci NOT NULL,
  `topics` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_themed`),
  KEY `FK_PostThemed_owner` (`owner`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `PostThemed`
--

INSERT INTO `PostThemed` VALUES (3, 'El Covid suma otros 11 muertos en Córdoba en una jornada con 235 positivos', 'La pandemia de coronavirus sigue avanzando en la provincia de Córdoba, con unos datos que este viernes son buenos y malos a la vez. La mala noticia es que se incrementa de manera notable el número de víctimas mortales en la provincia. En total, y según lo', '2020-11-19 14:53:35', '2020-11-19 14:53:38', '2021-01-04 14:53:43', 5, '', 'themed', 'published', '11,14', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PostThemedTopic`
--

DROP TABLE IF EXISTS `PostThemedTopic`;
CREATE TABLE IF NOT EXISTS `PostThemedTopic` (
  `id_themed` int(10) NOT NULL,
  `id_topic` int(10) NOT NULL,
  PRIMARY KEY (`id_themed`,`id_topic`),
  KEY `FK_PostThemedTopic_id_topic` (`id_topic`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `PostThemedTopic`
--

INSERT INTO `PostThemedTopic` VALUES (3, 11);
INSERT INTO `PostThemedTopic` VALUES (3, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Topic`
--

DROP TABLE IF EXISTS `Topic`;
CREATE TABLE IF NOT EXISTS `Topic` (
  `id_topic` int(10) NOT NULL,
  `topic` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id_topic`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `Topic`
--

INSERT INTO `Topic` VALUES (1, 'java');
INSERT INTO `Topic` VALUES (2, 'deportes');
INSERT INTO `Topic` VALUES (3, 'historia');
INSERT INTO `Topic` VALUES (4, 'política');
INSERT INTO `Topic` VALUES (5, 'salud');
INSERT INTO `Topic` VALUES (6, 'ciencia');
INSERT INTO `Topic` VALUES (7, 'programación_web');
INSERT INTO `Topic` VALUES (8, 'psicología');
INSERT INTO `Topic` VALUES (9, 'gastronomía');
INSERT INTO `Topic` VALUES (10, 'curiosidades');
INSERT INTO `Topic` VALUES (11, 'covid-19');
INSERT INTO `Topic` VALUES (12, 'educación');
INSERT INTO `Topic` VALUES (13, 'cultura');
INSERT INTO `Topic` VALUES (14, 'actualidad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `User`
--

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `id_user` int(10) NOT NULL,
  `firstName` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `lastName` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `birthday` date NOT NULL,
  `interests` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `User`
--

INSERT INTO `User` VALUES (1, 'Diego', 'Rodríguez Riera', 'i62rorid@uco.es', '1998-02-07', '1,5,14', 'admin', 0);
INSERT INTO `User` VALUES (2, 'Ana', 'Navajas Fernández', 'i82nafea@uco.es', '2000-02-19', '1,2,3,7,11,12', 'admin', 0);
INSERT INTO `User` VALUES (3, 'José Raúl', 'Romero Salguero', 'jrromero@uco.es', '1972-11-22', '1,4,5,13', 'user', 0);
INSERT INTO `User` VALUES (4, 'Carlos', 'Revuelto Quero', 'i82requc@uco.es', '2000-12-15', '4,5,9,10,11', 'user', 0);
INSERT INTO `User` VALUES (5, 'Aurora', 'Ramírez Quesada', 'i72raqua@uco.es', '1980-11-22', '7,8,9,10,11,13,14', 'user', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UserTopic`
--

DROP TABLE IF EXISTS `UserTopic`;
CREATE TABLE IF NOT EXISTS `UserTopic` (
  `id_user` int(10) NOT NULL,
  `id_topic` int(10) NOT NULL,
  PRIMARY KEY (`id_user`,`id_topic`),
  KEY `FK_UserTopic_id_topic` (`id_topic`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- 
-- Volcar la base de datos para la tabla `UserTopic`
-- 

INSERT INTO `UserTopic` VALUES (1, 1);
INSERT INTO `UserTopic` VALUES (1, 5);
INSERT INTO `UserTopic` VALUES (1, 14);
INSERT INTO `UserTopic` VALUES (2, 1);
INSERT INTO `UserTopic` VALUES (2, 2);
INSERT INTO `UserTopic` VALUES (2, 3);
INSERT INTO `UserTopic` VALUES (2, 7);
INSERT INTO `UserTopic` VALUES (2, 11);
INSERT INTO `UserTopic` VALUES (2, 12);
INSERT INTO `UserTopic` VALUES (3, 1);
INSERT INTO `UserTopic` VALUES (3, 4);
INSERT INTO `UserTopic` VALUES (3, 5);
INSERT INTO `UserTopic` VALUES (3, 13);
INSERT INTO `UserTopic` VALUES (4, 4);
INSERT INTO `UserTopic` VALUES (4, 5);
INSERT INTO `UserTopic` VALUES (4, 9);
INSERT INTO `UserTopic` VALUES (4, 10);
INSERT INTO `UserTopic` VALUES (4, 11);
INSERT INTO `UserTopic` VALUES (5, 7);
INSERT INTO `UserTopic` VALUES (5, 8);
INSERT INTO `UserTopic` VALUES (5, 9);
INSERT INTO `UserTopic` VALUES (5, 10);
INSERT INTO `UserTopic` VALUES (5, 11);
INSERT INTO `UserTopic` VALUES (5, 13);
INSERT INTO `UserTopic` VALUES (5, 14);
