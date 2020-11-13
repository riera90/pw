-- phpMyAdmin SQL Dump
-- version 2.7.0-pl2
-- http://www.phpmyadmin.net
-- 
-- Servidor: oraclepr.uco.es
-- Tiempo de generación: 13-11-2020 a las 21:45:22
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
  `interests` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `isDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- 
-- Volcar la base de datos para la tabla `User`
-- 


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


